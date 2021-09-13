package dk.shantech.myoffer.ui.main

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun setupToolbar(navController: NavController) {
    when(currentRoute(navController = navController)) {
        NavigationItem.Home.route,  NavigationItem.Movies.route -> MainToolBar()
        NavigationItem.Music.route -> MainTwoToolBar()
        else -> {}
    }
}

@Composable
fun MainToolBar(viewModel: ToolbarViewModel = viewModel()) {
    val title: String by viewModel.title.observeAsState("")
    TopAppBar(title = { Text(text = title)})

}

@Composable
fun MainTwoToolBar(viewModel: ToolbarViewModel = viewModel()) {
    val title: String by viewModel.title.observeAsState("")
    TopAppBar(title = { Text(text = title)})

}


class ToolbarViewModel: ViewModel() {

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title


    init {
        _title.value = "main"
    }
}
