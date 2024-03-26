package com.androidvynils.app.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.androidvynils.app.database.VinylRoomDatabase
import com.androidvynils.app.models.Comment
import com.androidvynils.app.repositories.CommentsCacheRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentViewModel(application: Application, albumId: Int) :  AndroidViewModel(application)  {
    val id:Int = albumId
    private val _comments = MutableLiveData<List<Comment>>()
    private val commentsRepository = CommentsCacheRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).commentsDao())

    val comments: LiveData<List<Comment>>
        get() = _comments

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    init {
        refreshDataFromAdapter()
    }


    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown
    private fun refreshDataFromAdapter() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = commentsRepository.refreshDataById(id)
                    _comments.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch(ex: Exception) {
            Log.d("Error", ex.toString())
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CommentViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}