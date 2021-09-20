package dk.shantech.myoffer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.shantech.myoffer.data.DealerRepository
import dk.shantech.myoffer.model.DealerFrontResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dealerRepository: DealerRepository): ViewModel() {

    lateinit var dealers : Flow<PagingData<DealerFrontResponseItem>>

    init {
        getDealerList("name", "paged,incito") // TODO: 20/09/2021 Move orderBy and type to UI 
    }

    private fun getDealerList(orderBy: String, types: String) {
        dealers = dealerRepository.getDealerList(orderBy = orderBy, types = types).cachedIn(viewModelScope)
    }
}