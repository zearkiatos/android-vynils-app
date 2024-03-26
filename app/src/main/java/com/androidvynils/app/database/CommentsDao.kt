package com.androidvynils.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidvynils.app.models.Comment

@Dao
interface CommentsDao {
    @Query("SELECT * FROM comments WHERE albumId = :albumId ORDER BY rating DESC")
    fun getComments(albumId:Int):List<Comment>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(comment: Comment)

    @Query("DELETE FROM comments")
    suspend fun clear():Void

    @Query("DELETE FROM comments WHERE albumId = :albumId")
    suspend fun deleteAll(albumId: Int)
}