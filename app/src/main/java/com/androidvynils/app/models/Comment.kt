package com.androidvynils.app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "comments")
data class Comment(
    val description:String,
    val rating:String,
    val albumId:Int,
    @PrimaryKey(autoGenerate = true)
    val commentId:Int = 0
)
