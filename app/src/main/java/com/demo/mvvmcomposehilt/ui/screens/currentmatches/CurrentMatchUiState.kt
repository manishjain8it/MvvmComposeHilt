package com.demo.mvvmcomposehilt.ui.screens.currentmatches

import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper

sealed interface CurrentMatchUiState {
    data class Success(val data: List<CurrentMatchMapper> = emptyList()) : CurrentMatchUiState
    data class Error(val error: String) : CurrentMatchUiState
    data object Loading : CurrentMatchUiState
}