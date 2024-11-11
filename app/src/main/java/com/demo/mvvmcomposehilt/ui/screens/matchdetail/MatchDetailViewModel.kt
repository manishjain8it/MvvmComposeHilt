package com.demo.mvvmcomposehilt.ui.screens.matchdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.domain.usecase.GetMatchInfoUseCase
import com.demo.mvvmcomposehilt.ui.navigation.MatchDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMatchInfoUseCase: GetMatchInfoUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<MatchDetailUiState> = MutableStateFlow(MatchDetailUiState.Loading)
    val uiState: StateFlow<MatchDetailUiState> = _uiState.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<MatchDetail>()
        getMatchInfoData(args.matchId)
    }

    private fun getMatchInfoData(matchId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMatchInfoUseCase.execute(matchId = matchId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _uiState.value = MatchDetailUiState.Loading
                    }

                    is Resource.Success -> {
                        _uiState.value = MatchDetailUiState.Success(matchDetails = it.response)
                    }

                    is Resource.Error -> {
                        _uiState.value = MatchDetailUiState.Error(error = it.exception.toString())
                    }
                }
            }
        }
    }
}

