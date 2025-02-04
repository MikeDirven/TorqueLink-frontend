package nl.torquelink.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

abstract class ReducerViewModel<STATE, EVENTS>(initial: STATE) : ViewModel() {
    abstract fun dispatch(event: EVENTS) : STATE
}