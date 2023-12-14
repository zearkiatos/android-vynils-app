package com.androidvynils.app.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidvynils.app.models.Collector

interface CollectorsDao {
    @Query("SELECT * FROM collectors")
    fun getCollectors():List<Collector>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(collector: Collector)

    @Query("DELETE FROM collectors")
    suspend fun deleteAll()
}