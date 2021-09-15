package dk.shantech.myoffer.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dk.shantech.myoffer.ui.theme.MyOfferTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyOfferTheme {
                // A surface container using the 'background' color from the theme
               MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { setupToolbar(navController = navController) },
        bottomBar = { setupBottomNavigation(navController = navController) },
        content = { Navigation(navController = navController) }
    )

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}



