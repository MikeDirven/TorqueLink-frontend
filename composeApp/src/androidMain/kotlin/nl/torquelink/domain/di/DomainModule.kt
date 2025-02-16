package nl.torquelink.domain.di

import com.google.firebase.messaging.FirebaseMessaging
import nl.torquelink.domain.utils.firebase.FirebaseMessagingServiceImpl
import org.koin.dsl.module

val DomainModule = module {
    single { FirebaseMessaging.getInstance() }
    single { FirebaseMessagingServiceImpl() }
}