package dk.shantech.myoffer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name


    init {
        _name.value = "Droid"
    }
}