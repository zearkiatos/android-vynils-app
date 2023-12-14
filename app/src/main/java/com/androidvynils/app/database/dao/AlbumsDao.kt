package com.androidvynils.app.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidvynils.app.models.Album

interface AlbumsDao {
    @Query("SELECT * FROM albums")
    fun getAlbums():List<Album>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(album: Album)

    @Query("DELETE FROM albums")
    suspend fun deleteAll():Int
}