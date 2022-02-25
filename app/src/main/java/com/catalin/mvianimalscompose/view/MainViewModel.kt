package com.catalin.mvianimalscompose.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catalin.mvianimalscompose.api.AnimalRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: AnimalRepo): ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    var state = mutableStateOf<MainState>(MainState.Idle)
        private set

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { collector ->
                when (collector) {
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }

    private fun fetchAnimals() {
        viewModelScope.launch {
            state.value = MainState.Loading
            state.value = try {
                MainState.Animals(repo.getAnimals())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}





