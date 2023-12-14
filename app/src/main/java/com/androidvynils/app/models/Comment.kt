package com.androidvynils.app.models

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "comments")
data class Comment(
    val description:String,
    val rating:String,
    val albumId:Int
)
