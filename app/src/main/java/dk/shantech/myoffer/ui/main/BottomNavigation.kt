package dk.shantech.myoffer.ui.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import dk.shantech.myoffer.ui.main.NavigationItem.*
import dk.shantech.myoffer.ui.theme.Purple200
import dk.shantech.myoffer.ui.theme.White


@Composable
fun setupBottomNavigation(navController: NavController) {

    when(currentRoute(navController = navController)) {
       Home.route, Music.route, Movies.route -> BottomNavigationBar(navController = navController)
       else -> {}
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Home,
        Music,
        Movies,
    )
    BottomNavigation(
        backgroundColor = Purple200,
        contentColor = White
    ) {
        val currentRoute = currentRoute(navController = navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = White,
                unselectedContentColor = White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

