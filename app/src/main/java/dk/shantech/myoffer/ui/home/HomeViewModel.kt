package dk.shantech.myoffer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.shantech.myoffer.data.Repository
import dk.shantech.myoffer.model.DealerFrontResponse
import dk.shantech.myoffer.model.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _data : MutableLiveData<DealerFrontResponse> = MutableLiveData()
    val data: LiveData<DealerFrontResponse> = _data


    init {
        getAllDealers()
    }

    fun getAllDealers() = viewModelScope.launch {
        repository.getAllDealers().collect { values ->
            when(values) {
                is NetworkResult.Error -> Timber.d("Error")
                is NetworkResult.Loading -> Timber.d("Loading")
                is NetworkResult.Success -> {
                    Timber.d("Success ${values.data?.size}")
                    values.data?.let { _data.value = it }
                }
            }
        }
    }

}