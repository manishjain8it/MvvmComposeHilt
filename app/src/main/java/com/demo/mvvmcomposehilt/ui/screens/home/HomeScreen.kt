package com.demo.mvvmcomposehilt.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.demo.mvvmcomposehilt.ui.navigation.NavigationGraph
import com.demo.mvvmcomposehilt.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Current Matches", color = MaterialTheme.colorScheme.onPrimary) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            navigationIcon = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val showNavigationState = rememberSaveable { (mutableStateOf(false)) }
                showNavigationState.value = currentDestination?.route == Routes.MATCH_LIST_SCREEN

                if (!showNavigationState.value) {
                    Icon(
                        modifier = Modifier.clickable { navController.navigateUp() },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        )
    }) { padding: PaddingValues ->
        NavigationGraph(
            modifier = Modifier.padding(padding), navController = navController
        )
    }
}