package com.androidvynils.app.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidvynils.app.adapters.CommentApiServiceAdapter
import com.androidvynils.app.models.Comment
import com.androidvynils.app.repositories.CommentsRepository

class CommentViewModel(application: Application, albumId: Int) :  AndroidViewModel(application)  {
    val id:Int = albumId
    private val _comments = MutableLiveData<List<Comment>>()
    private val commentsRepository = CommentsRepository(application)

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
        commentsRepository.refreshData({
            _comments.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
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