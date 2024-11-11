package com.demo.mvvmcomposehilt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.demo.mvvmcomposehilt.ui.screens.currentmatches.CurrentMatchScreen
import com.demo.mvvmcomposehilt.ui.screens.matchdetail.MatchDetailScreen
import kotlinx.serialization.Serializable

object Routes {
    const val MATCH_LIST_SCREEN = "match_list"
}

@Composable
fun NavigationGraph(modifier: Modifier, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.MATCH_LIST_SCREEN
    ) {
        //list screen
        composable(route = Routes.MATCH_LIST_SCREEN) {
            CurrentMatchScreen(modifier = modifier) {
                val matchDetail = MatchDetail(it.matchId, it.matchName)
                navController.navigate(matchDetail)
            }
        }
        //detail screen
        composable<MatchDetail> {
            MatchDetailScreen(modifier = modifier)
        }
    }
}

@Serializable
data class MatchDetail(
    val matchId: String,
    val matchName: String
)
