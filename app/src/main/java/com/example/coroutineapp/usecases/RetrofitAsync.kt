package com.example.coroutineapp.usecases

import android.util.Log
import com.example.coroutineapp.networking.DelayModel
import com.example.coroutineapp.networking.HttpBinApi
import com.example.coroutineapp.TAG

object RetrofitAsync {
    private val api = HttpBinApi.getApi()

    // this method fails with NetworkOnMainThreadException
    fun blockingGet(): DelayModel {
        val res = api.delaySync().execute().body()!!
        Log.d(TAG, "Your IP is: ${res.origin}")
        return res
    }

    suspend fun suspendingGet(): DelayModel {
        val delayData = api.delay()
        Log.d(TAG, "Your IP is: ${delayData.origin}")
        return delayData
    }
}