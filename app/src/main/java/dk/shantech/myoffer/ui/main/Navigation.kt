package dk.shantech.myoffer.ui.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import dk.shantech.myoffer.R
import dk.shantech.myoffer.ui.home.Greeting

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_launcher_foreground, "Home")
    object Music : NavigationItem("music", R.drawable.ic_launcher_foreground, "Music")
    object Movies : NavigationItem("movies", R.drawable.ic_launcher_foreground, "Movies")
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            Greeting()
        }
        composable(NavigationItem.Music.route) {
            Text(text = "2")
        }
        composable(NavigationItem.Movies.route) {
            Text(text = "3")
        }
    }
}