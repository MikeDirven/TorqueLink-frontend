package nl.torquelink.presentation.snackbar.di

import nl.torquelink.presentation.snackbar.controller.DefaultSnackBarController
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import org.koin.dsl.module

val SnackBarModule = module {
    single<SnackBarController> { DefaultSnackBarController }
}