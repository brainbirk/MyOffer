package dk.shantech.myoffer.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dk.shantech.myoffer.model.DealerFrontResponse
import dk.shantech.myoffer.model.DealerFrontResponseItem
import dk.shantech.myoffer.ui.theme.MyOfferTheme

@Composable
fun HomeView(homeViewModel: HomeViewModel = viewModel()) {
    val data: DealerFrontResponse by homeViewModel.data.observeAsState(initial = DealerFrontResponse())
    LazyColumn {
        items(data) {
            DealerItem(dealerItem = it)
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

@Composable
fun DealerItem(dealerItem: DealerFrontResponseItem) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DealerTitle(
            dealerItem.dealer.name,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DealerTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}