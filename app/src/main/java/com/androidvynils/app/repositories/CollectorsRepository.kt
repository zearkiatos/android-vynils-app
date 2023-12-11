package com.androidvynils.app.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.androidvynils.app.adapters.CollectorApiServiceAdapter
import com.androidvynils.app.adapters.CollectorCacheManagerAdapter
import com.androidvynils.app.models.Collector


class CollectorsRepository(val application: Application): Repository<Collector, VolleyError> {
    override suspend fun refreshData(): List<Collector>{
        var potentialResponse = CollectorCacheManagerAdapter.getInstance(application.applicationContext).getCollectors()
        if(potentialResponse.isEmpty()){
            Log.d("Cache decision", "get from network")
            var collectors = CollectorApiServiceAdapter.getInstance(application).getCollectors()
            CollectorCacheManagerAdapter.getInstance(application.applicationContext).addCollectors(collectors)
            return collectors
        }
        else {
            Log.d("Cache decision", "return ${potentialResponse.size} elements from cache")
            return potentialResponse
        }
    }
}