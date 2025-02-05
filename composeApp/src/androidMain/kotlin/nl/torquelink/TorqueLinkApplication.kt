package nl.torquelink

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import nl.torquelink.data.di.DataModule
import nl.torquelink.domain.utils.FirebaseUtil.initializeFirebase
import nl.torquelink.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TorqueLinkApplication : Application(), KoinComponent, Configuration.Provider {
    override val workManagerConfiguration = Configuration.Builder()
        .setMinimumLoggingLevel(Log.DEBUG)
        .build()

    override fun onCreate() {
        super.onCreate()

        initializeFirebase(this)

        startKoin {
            androidContext(this@TorqueLinkApplication)
            androidLogger(Level.DEBUG)
            modules(
                DataModule,
                PresentationModule
            )
        }
    }
}