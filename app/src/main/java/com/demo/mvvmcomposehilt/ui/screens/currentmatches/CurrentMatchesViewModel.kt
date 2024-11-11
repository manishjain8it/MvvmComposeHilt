package com.demo.mvvmcomposehilt.ui.screens.currentmatches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.domain.usecase.GetCurrentMatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentMatchesViewModel @Inject constructor(private val getCurrentMatchUseCase: GetCurrentMatchUseCase) : ViewModel() {
    private val _uiState: MutableStateFlow<CurrentMatchUiState> = MutableStateFlow(CurrentMatchUiState.Loading)
    val uiState: StateFlow<CurrentMatchUiState> = _uiState.asStateFlow()

    init {
        getCurrentMatchesData()
    }

    private fun getCurrentMatchesData() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentMatchUseCase.execute().collect {
                when (it) {
                    is Resource.Loading -> {
                        _uiState.value = CurrentMatchUiState.Loading
                    }

                    is Resource.Success -> {
                        _uiState.value = CurrentMatchUiState.Success(data = it.response)
                    }

                    is Resource.Error -> {
                        _uiState.value = CurrentMatchUiState.Error(error = it.exception.toString())
                    }
                }
            }
        }
    }
}

