package com.androidvynils.app.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.androidvynils.app.adapters.CollectorApiServiceAdapter
import com.androidvynils.app.models.Collector


class CollectorsRepository(val application: Application): Repository<Collector, VolleyError> {
    override fun refreshData(): List<Collector>{
        return CollectorApiServiceAdapter.getInstance(application).getCollectors()
    }
}