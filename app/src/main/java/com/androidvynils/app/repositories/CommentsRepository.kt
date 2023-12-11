package com.androidvynils.app.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.androidvynils.app.adapters.CollectorApiServiceAdapter
import com.androidvynils.app.adapters.CommentApiServiceAdapter
import com.androidvynils.app.adapters.CommentCacheManagerAdapter
import com.androidvynils.app.models.Album
import com.androidvynils.app.models.Comment

class CommentsRepository(val application: Application) : Repository<Comment, VolleyError> {
    /*  override fun refreshData(callback: (List<Comment>)->Unit, onError: (VolleyError)->Unit){
Not
    } */
    override suspend fun refreshData(): List<Comment> {
        TODO("Not yet implemented")
    }

    suspend fun refreshDataById(albumId: Int):List<Comment> {
        var potentialResponse = CommentCacheManagerAdapter.getInstance(application.applicationContext).getComments(albumId)
        if(potentialResponse.isEmpty()){
            Log.d("Cache decision", "get from network")
            var comments = CommentApiServiceAdapter.getInstance(application).getComments(albumId)
            CommentCacheManagerAdapter.getInstance(application.applicationContext).addComments(albumId, comments)
            return comments
        }
        else {
            Log.d("Cache decision", "return ${potentialResponse.size} elements from cache")
            return potentialResponse
        }
    }
}