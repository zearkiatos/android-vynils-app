package com.androidvynils.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidvynils.app.models.Album
import com.androidvynils.app.models.Collector
import com.androidvynils.app.models.Comment

@Database(entities = [Album::class, Collector::class, Comment::class], version = 1, exportSchema = false)
abstract class VinylRoomDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao
    abstract fun collectorsDao(): CollectorsDao
    abstract fun commentsDao(): CommentsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinylRoomDatabase? = null

        fun getDatabase(context: Context): VinylRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            try {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        VinylRoomDatabase::class.java,
                        "vinyls_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
            catch(e: Exception) {
                println("Some error ocurred")
                throw Exception("Some error ocurred")
            }
        }
    }
}