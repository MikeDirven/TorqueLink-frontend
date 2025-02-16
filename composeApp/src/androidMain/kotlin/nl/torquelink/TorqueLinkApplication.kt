package nl.torquelink

import android.app.Application
import nl.torquelink.data.di.DataModule
import nl.torquelink.domain.di.DomainModule
import nl.torquelink.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TorqueLinkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TorqueLinkApplication)
            modules(
                DataModule,
                PresentationModule,
                DomainModule
            ) // Load all modules
        }
    }
}