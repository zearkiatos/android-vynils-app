package com.androidvynils.app.adapters
import android.content.Context
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import com.androidvynils.app.models.Comment

class CommentCacheManagerAdapter(context: Context) {
    companion object{
        var instance: CommentCacheManagerAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CommentCacheManagerAdapter(context).also {
                    instance = it
                }
            }
    }

    private var comments: HashMap<Int, List<Comment>> = hashMapOf()

    private var arrayMapComment: ArrayMap<Int, List<Comment>> = arrayMapOf()
    fun addComments(albumId: Int, comment: List<Comment>){
        if (!comments.containsKey(albumId)){
            comments[albumId] = comment
        }
    }

    fun getComments(albumId: Int) : List<Comment>{
        return if (comments.containsKey(albumId)) comments[albumId]!! else listOf<Comment>()
    }
}