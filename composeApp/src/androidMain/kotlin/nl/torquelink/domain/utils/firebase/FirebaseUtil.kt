package nl.torquelink.domain.utils.firebase

import android.content.Context
import android.os.Build
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import org.koin.compose.koinInject

object FirebaseUtil {
    @Composable
    fun InitializeMessaging(
        preferencesRepository: PreferencesRepository = koinInject<PreferencesRepository>(),
        authenticationRepository: AuthenticationRepository = koinInject<AuthenticationRepository>()
    ) {
        LaunchedEffect(Unit) {
            // Update notification token
            FirebaseMessaging.getInstance().token.addOnSuccessListener {
                CoroutineScope(Dispatchers.IO).launch {
                    preferencesRepository.setNotificationToken(it)

                    // Sent to server
                    authenticationRepository.setNotificationToken(it)
                }
            }
        }
    }

    private fun FirebaseCrashlytics.setBaseFields() {
        this.setCustomKey(MANUFACTURER, Build.MANUFACTURER)
        this.setCustomKey(MODEL, Build.MODEL)
        this.setCustomKey(PRODUCT, Build.PRODUCT)
        this.setCustomKey(DEVICE, Build.DEVICE)
        this.setCustomKey(OS, "Android")
        this.setCustomKey(API_LEVEL, Build.VERSION.SDK_INT.toString())
    }


    fun initializeFirebase(context: Context) {
        FirebaseApp.initializeApp(context)
        initializeAndConfigureAnalytics(context)
        initializeAndConfigureCrashlytics()
    }

    private fun initializeAndConfigureAnalytics(context: Context) {
        val analytics = FirebaseAnalytics.getInstance(context)
        analytics.setAnalyticsCollectionEnabled(true)
    }

    private fun initializeAndConfigureCrashlytics() {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setBaseFields()
        crashlytics.isCrashlyticsCollectionEnabled = true
    }
    private const val MANUFACTURER = "Manufacturer"
    private const val MODEL = "Model"
    private const val PRODUCT = "Product"
    private const val DEVICE = "Device"
    private const val OS = "OS"
    private const val API_LEVEL = "API Level"
    private const val USER_ID = "User ID"
    private const val USER_NAME = "User Name"
}