package com.androidvynils.app.adapters
import android.content.Context
import android.util.SparseArray
import androidx.collection.LruCache
import com.androidvynils.app.models.Album
import com.androidvynils.app.models.Collector
class CollectorCacheManagerAdapter(context:Context) {
    companion object{
        var instance: CollectorCacheManagerAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CollectorCacheManagerAdapter(context).also {
                    instance = it
                }
            }

    }

        private var collectors: LruCache<Int, List<Collector>> = LruCache(3)

        fun addCollectors(collectorList: List<Collector>){
            if (collectors[0] == null){
                collectors.put(0, collectorList)
            }
        }

        fun getCollectors() : List<Collector>{
            return if (collectors[0]!=null) collectors[0]!! else listOf<Collector>()
        }

}