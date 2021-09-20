package dk.shantech.myoffer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.shantech.myoffer.data.DealerRepository
import dk.shantech.myoffer.model.DealerFrontResponseItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dealerRepository: DealerRepository): ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            getDealerList("name", "paged,incito")
            dealers.onCompletion { _isRefreshing.emit(false) }

        }
    }


    lateinit var dealers : Flow<PagingData<DealerFrontResponseItem>>

    init {
        getDealerList("name", "paged,incito") // TODO: 20/09/2021 Move orderBy and type to UI 
    }

    private fun getDealerList(orderBy: String, types: String) {
        dealers = dealerRepository.getDealerList(orderBy = orderBy, types = types).cachedIn(viewModelScope)
    }
}