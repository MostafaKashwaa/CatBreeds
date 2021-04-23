package com.example.catbreeds

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kashwaa.shared.domain.Breed
import com.kashwaa.shared.repositories.BreedRepoRemote
import com.kashwaa.shared.repositories.BreedSDK
import kotlinx.coroutines.launch

class BreedsViewModel(private val sdk: BreedSDK) : ViewModel() {
    sealed class Status(val value:String) {
        object Loading: Status("loading")
        object Success: Status("done")
        object Failed: Status("failed")
        object Idle: Status("")
    }

    private val repo = BreedRepoRemote()

    var breeds by mutableStateOf<List<Breed>>(listOf())
    var status: Status by mutableStateOf(Status.Idle)

    fun onLoadBreeds() {
        viewModelScope.launch {
            status = Status.Loading
            runCatching {
                sdk.getBreeds(false)
            }.onSuccess {
                status = Status.Success
                breeds = it
            }.onFailure {
                status = Status.Failed
                Log.i("ViewModel", "onBreedsUpdate: ${it.localizedMessage}")
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class BreedsViewModelFactory(private val sdk: BreedSDK): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BreedsViewModel(sdk) as T
    }
}