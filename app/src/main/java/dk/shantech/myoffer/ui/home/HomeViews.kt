package dk.shantech.myoffer.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dk.shantech.myoffer.model.DealerFrontResponse
import dk.shantech.myoffer.ui.theme.MyOfferTheme

@Composable
fun HomeView(homeViewModel: HomeViewModel = viewModel()) {
    val data: DealerFrontResponse by homeViewModel.data.observeAsState(initial = DealerFrontResponse())
    Column {
        data.forEach { data ->
            Text(text = data.dealer.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyOfferTheme {
        HomeView()
    }
}