package com.androidvynils.app.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.androidvynils.app.adapters.CommentApiServiceAdapter
import com.androidvynils.app.database.CommentsDao
import com.androidvynils.app.models.Comment

class CommentsCacheRepository(val application: Application, private val commentsDao: CommentsDao) {
    suspend fun refreshDataById(albumId: Int):List<Comment> {
        val cached = commentsDao.getComments(albumId)

        return if(cached.isNullOrEmpty()) {
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            }
            else CommentApiServiceAdapter.getInstance(application).getComments(albumId)
        }
        else cached
    }
}