package com.aryanakbarpour.localgraffiti.arts

import androidx.lifecycle.*
import com.aryanakbarpour.localgraffiti.data.Art
import com.aryanakbarpour.localgraffiti.di.AppContainer
import kotlinx.coroutines.launch
import java.lang.Exception

enum class APIServiceStatus { LOADING, ERROR, DONE}

class ArtsViewModel(private val appContainer: AppContainer, private val cityName: String) : ViewModel() {
    private val _status = MutableLiveData<APIServiceStatus>()
    val status: LiveData<APIServiceStatus> = _status

    private val _localArts = MutableLiveData<List<Art>>()
    val localArts: LiveData<List<Art>> = _localArts

    init {
        retrieveLocalArts()
    }

    private fun retrieveLocalArts() {
        viewModelScope.launch {
            _status.value = APIServiceStatus.LOADING
            try {
                _localArts.value = appContainer.retrofit.getCityArts(cityName)
                _status.value = APIServiceStatus.DONE
            } catch (e: Exception){
                _status.value = APIServiceStatus.ERROR
                _localArts.value = listOf()
                e.printStackTrace()
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class ArtsViewModelFactory (
    private val appContainer: AppContainer,
    private val cityName: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (ArtsViewModel(appContainer, cityName) as T)
}