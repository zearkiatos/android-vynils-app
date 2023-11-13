package com.androidvynils.app.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.androidvynils.app.models.Album
import com.androidvynils.app.adapters.AlbumApiServiceAdapter


class AlbumRepository(val application: Application): Repository<Album, VolleyError> {
    override fun refreshData(callback: (List<Album>)->Unit,  onError: (VolleyError)->Unit){
        AlbumApiServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }
}