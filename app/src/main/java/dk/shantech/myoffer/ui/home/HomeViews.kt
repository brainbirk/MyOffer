package dk.shantech.myoffer.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dk.shantech.myoffer.ui.theme.MyOfferTheme

@Composable
fun Greeting(homeViewModel: HomeViewModel = viewModel()) {
    val name: String by homeViewModel.name.observeAsState("")
    Text(text = "Hello ${name}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyOfferTheme {
        Greeting()
    }
}