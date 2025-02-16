package nl.torquelink.domain.utils.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.runBlocking
import nl.torquelink.R
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class FirebaseMessagingServiceImpl : FirebaseMessagingService(), KoinComponent {
    private var notificationIdCounter = 0
    private var notificationManager: NotificationManager? = null
    private val preferencesRepository: PreferencesRepository by inject()
    private val authenticationRepository: AuthenticationRepository by inject()

    override fun onNewToken(token: String) {
        Log.d("FCM", "Refreshed token: $token")
        runBlocking {
            if (preferencesRepository.getNotificationToken() != token){
                preferencesRepository.setNotificationToken(token)
                // Sent to server
                authenticationRepository.setNotificationToken(token)
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        notificationManager = notificationManager ?: createNotificationChannel()

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            // Build the notification
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo) // Replace with your icon
                .setContentTitle(it.title)
                .setContentText(it.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // increment id
            notificationIdCounter = notificationIdCounter.inc()
            // Show the notification
            notificationManager!!.notify(notificationIdCounter, builder.build())
        }
    }

    private fun createNotificationChannel(): NotificationManager {
        val name = "Torque link notifications"
        val descriptionText = "Notification channel for torque link notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        return baseContext.getSystemService(NotificationManager::class.java).also {
            it.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "TL_NOTIFICATIONS"
    }
}