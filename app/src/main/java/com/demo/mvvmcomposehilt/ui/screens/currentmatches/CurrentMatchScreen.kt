package com.demo.mvvmcomposehilt.ui.screens.currentmatches

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.mvvmcomposehilt.R
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import com.demo.mvvmcomposehilt.ui.navigation.MatchDetail
import com.demo.mvvmcomposehilt.ui.theme.Typography

@Composable
fun CurrentMatchScreen(
    modifier: Modifier = Modifier,
    onItemClick: (MatchDetail) -> Unit
) {
    val viewModel: CurrentMatchesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    CurrentMatchScreenContent(
        modifier = modifier,
        matchUiState = uiState,
        onClick = onItemClick
    )
}

@Composable
private fun CurrentMatchScreenContent(
    modifier: Modifier = Modifier,
    matchUiState: CurrentMatchUiState,
    onClick: (MatchDetail) -> Unit
) {
    when (matchUiState) {
        is CurrentMatchUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CurrentMatchUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(matchUiState.error, style = Typography.titleSmall)
            }
        }

        is CurrentMatchUiState.Success -> {
            LazyColumn(modifier = modifier) {
                items(matchUiState.data.size) {
                    MatchListItem(match = matchUiState.data[it], onClick = onClick)
                    HorizontalDivider(
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
private fun MatchListItem(
    match: CurrentMatchMapper,
    onClick: (MatchDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(all = dimensionResource(id = R.dimen.margin_medium))
        .background(color = MaterialTheme.colorScheme.background)
        .clickable {
            onClick(MatchDetail(match.id, match.name))
        }
    ) {
        Text(match.name, style = Typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(match.status, style = Typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(match.venue, style = Typography.bodySmall.copy(color = Color.Gray))
    }
}