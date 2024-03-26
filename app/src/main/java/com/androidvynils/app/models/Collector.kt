package com.androidvynils.app.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collectors")
data class Collector (
    @PrimaryKey val collectorId: Int,
    val name:String,
    val telephone:String,
    val email:String
)