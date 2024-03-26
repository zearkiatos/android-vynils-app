package com.androidvynils.app.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.androidvynils.app.adapters.AlbumApiServiceAdapter
import com.androidvynils.app.database.AlbumsDao
import com.androidvynils.app.models.Album


class AlbumCacheRepository(val application: Application, private val albumsDao: AlbumsDao) {
    suspend fun refreshData(): List<Album> {
        var cached = albumsDao.getAlbums()
        return if (cached.isNullOrEmpty()) {
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            }
            else AlbumApiServiceAdapter.getInstance(application).getAlbums()
        }
        else cached
    }
}