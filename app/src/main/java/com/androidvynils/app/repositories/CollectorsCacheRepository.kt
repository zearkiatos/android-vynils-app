package com.androidvynils.app.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.androidvynils.app.adapters.CollectorApiServiceAdapter
import com.androidvynils.app.database.CollectorsDao
import com.androidvynils.app.models.Collector


class CollectorsCacheRepository(val application: Application, private val collectorsDao: CollectorsDao) {

    suspend fun refreshData(): List<Collector> {
        val cached = collectorsDao.getCollectors()
        return if (cached.isNullOrEmpty()) {
            val cm = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            }
            else CollectorApiServiceAdapter.getInstance(application).getCollectors()
        }
        else cached
    }
}