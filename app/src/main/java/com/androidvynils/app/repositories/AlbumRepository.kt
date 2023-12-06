package com.androidvynils.app.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.androidvynils.app.models.Album
import com.androidvynils.app.adapters.AlbumApiServiceAdapter


class AlbumRepository(val application: Application): Repository<Album, VolleyError> {
    override fun refreshData(): List<Album> {
        return AlbumApiServiceAdapter.getInstance(application).getAlbums()
    }
}