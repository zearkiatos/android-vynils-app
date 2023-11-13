package com.androidvynils.app.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.androidvynils.app.adapters.CollectorApiServiceAdapter
import com.androidvynils.app.adapters.CommentApiServiceAdapter
import com.androidvynils.app.models.Album
import com.androidvynils.app.models.Comment

class CommentsRepository(val application: Application) : Repository<Comment, VolleyError> {
    /*  override fun refreshData(callback: (List<Comment>)->Unit, onError: (VolleyError)->Unit){
Not
    } */
    override fun refreshData(callback: (List<Comment>) -> Unit, onError: (VolleyError) -> Unit) {
        TODO("Not yet implemented")
    }

    fun refreshDataById(albumId: Int, callback: (List<Comment>)->Unit, onError: (VolleyError)->Unit) {
        CommentApiServiceAdapter.getInstance(application).getComments(albumId, {
            callback(it)
        },
            onError
        )
    }
}