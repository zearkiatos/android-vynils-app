package com.androidvynils.app.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidvynils.app.adapters.AlbumApiServiceAdapter
import com.androidvynils.app.models.Album
import com.androidvynils.app.repositories.AlbumRepository

class AlbumViewModel(application: Application): AndroidViewModel(application) {
    private val _albums = MutableLiveData<List<Album>>()
    private val albumRepository = AlbumRepository(application)

    val albums: LiveData<List<Album>>
        get() = _albums

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromAdapter()
    }

    private fun refreshDataFromAdapter() {
        try {
            val data = albumRepository.refreshData()
            _albums.postValue(data)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }
        catch(ex: Exception) {
            Log.d("Error", ex.toString())
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}