package com.androidvynils.app.repositories

import android.app.Application

interface Repository<T, K> {
    suspend fun refreshData(): List<T>;
}