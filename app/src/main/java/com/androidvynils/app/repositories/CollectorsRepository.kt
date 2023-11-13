package com.androidvynils.app.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.androidvynils.app.adapters.CollectorApiServiceAdapter
import com.androidvynils.app.models.Collector


class CollectorsRepository(val application: Application): Repository<Collector, VolleyError> {
    override fun refreshData(callback: (List<Collector>)->Unit, onError: (VolleyError)->Unit){
        CollectorApiServiceAdapter.getInstance(application).getCollectors({
            callback(it)
        },
            onError
        )
    }
}