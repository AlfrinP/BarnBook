package com.example.barnbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.barnbook.activity.ActivityScreen
import com.example.barnbook.home.Home
import com.example.barnbook.items.ItemsScreen
import com.example.barnbook.profile.ProfileScreen
import com.example.barnbook.ui.components.BottomNavBar
import com.example.barnbook.ui.theme.BarnBookTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarnBookTheme {
                MainScreen()
            }
        }
    }
}

@Serializable
object HomeScreenRoute

@Serializable
object ProfileScreenRoute

@Serializable
object ActivityScreenRoute

@Serializable
object ItemsScreenRoute


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                onHomeClick = {
                    navController.navigate(route = HomeScreenRoute)
                },
                onItemsClick = {
                    navController.navigate(route = ItemsScreenRoute)
                },
                onActivityClick = {
                    navController.navigate(route = ActivityScreenRoute)
                },
                onProfileClick = {
                    navController.navigate(route = ProfileScreenRoute)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = HomeScreenRoute) {
                composable<HomeScreenRoute> { Home() }
                composable<ProfileScreenRoute> { ProfileScreen() }
                composable<ActivityScreenRoute> { ActivityScreen() }
                composable<ItemsScreenRoute> { ItemsScreen() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BarnBookTheme {
        MainScreen()
    }
}