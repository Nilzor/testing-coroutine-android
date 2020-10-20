package com.example.coroutineapp.networking

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HttpBinApi {
    @GET("/delay/1")
    suspend fun delay() : DelayModel

    @GET("/delay/1")
    fun delaySync() : Call<DelayModel>

    companion object {
        fun getApi() : HttpBinApi {
            return Retrofit.Builder()
                .baseUrl("http://httpbin.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HttpBinApi::class.java)
        }
    }
}