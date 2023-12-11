package com.androidvynils.app.adapters
import android.content.Context
import android.util.SparseArray
import com.androidvynils.app.models.Album


class AlbumCacheManagerAdapter(context: Context) {
    companion object{
        var instance: AlbumCacheManagerAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AlbumCacheManagerAdapter(context).also {
                    instance = it
                }
            }
    }

    private var albums: SparseArray<List<Album>> = SparseArray()
    fun addAlbums(albumList: List<Album>){
        if (albumList[0]==null){
            albums.setValueAt(0, albumList)
        }
    }
    fun getAlbums() : List<Album>{
        return if (albums[0]!=null) albums[0]!! else listOf<Album>()
    }
}