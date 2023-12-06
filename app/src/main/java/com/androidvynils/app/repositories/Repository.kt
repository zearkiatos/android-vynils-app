package com.androidvynils.app.repositories

import android.app.Application

interface Repository<T, K> {
    fun refreshData(): List<T>;
}