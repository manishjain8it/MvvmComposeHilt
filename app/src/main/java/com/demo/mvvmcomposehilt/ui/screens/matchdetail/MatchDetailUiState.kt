package com.demo.mvvmcomposehilt.ui.screens.matchdetail

import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper

sealed interface MatchDetailUiState {
    data class Success(val matchDetails: CurrentMatchMapper) : MatchDetailUiState
    data class Error(val error: String) : MatchDetailUiState
    data object Loading : MatchDetailUiState
}