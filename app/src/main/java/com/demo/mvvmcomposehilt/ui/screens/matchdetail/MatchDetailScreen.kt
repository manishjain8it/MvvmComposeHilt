package com.demo.mvvmcomposehilt.ui.screens.matchdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.mvvmcomposehilt.R
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import com.demo.mvvmcomposehilt.ui.theme.Typography

@Composable
fun MatchDetailScreen(
    modifier: Modifier
) {
    val viewModel: MatchDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    MatchDetailScreenContent(
        modifier = modifier,
        matchUiState = uiState
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MatchDetailScreenContent(
    modifier: Modifier = Modifier,
    matchUiState: MatchDetailUiState
) {
    when (matchUiState) {
        is MatchDetailUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is MatchDetailUiState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(matchUiState.error, style = Typography.titleSmall)
            }
        }

        is MatchDetailUiState.Success -> {
            MatchDetailItem(modifier = modifier, matchInfo = matchUiState.matchDetails)
        }
    }
}

@Composable
private fun MatchDetailItem(
    modifier: Modifier,
    matchInfo: CurrentMatchMapper
) {
    with(matchInfo) {
        Column(modifier = modifier.padding(all = dimensionResource(id = R.dimen.margin_medium))) {
            Text(name, style = Typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 4.dp))
            Text(date, style = Typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            Text(venue, style = Typography.bodySmall.copy(color = Color.Gray), fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Match Status:", style = Typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 4.dp))
            Text(status, style = Typography.titleMedium, modifier = Modifier.padding(bottom = 4.dp))

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                "Scorecard:",
                style = Typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row {
                Text("Run - ", style = Typography.bodyMedium)
                Text(score.first().r.toString(), style = Typography.bodyMedium)
            }
            Row {
                Text("Wicket - ", style = Typography.bodyMedium)
                Text(score.first().w.toString(), style = Typography.bodyMedium)
            }
            Row {
                Text("Over - ", style = Typography.bodyMedium)
                Text(score.first().o.toString(), style = Typography.bodyMedium)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}