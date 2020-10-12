package com.example.coroutineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.*
import retrofit2.awaitResponse
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    val mainScope = CoroutineScope(Dispatchers.Main)
    val ioScope = CoroutineScope(Dispatchers.IO)
    val api = HttpBinApi.getApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StrictMode.enableDefaults()

        findViewById<TextView>(R.id.text).setText("Loading data..")
    }


    override fun onResume() {
        super.onResume()
        runBlocking {
            jobEntryPoint()
        }
        Log.d("zzz", "runBlocking done")
    }

    fun jobEntryPoint() {
        fireNonSuspendingOffThreadJob()
        doOtherStuff()
    }

    fun fireNonSuspendingOffThreadJob() {
        ioScope.launch {
            delay(500)
            Log.i("zzz", "fireNonSuspendingOffThreadJob")
        }
    }

    suspend fun entryPoint() {
        fireOffThreadJob().join() // join() makes us suspend until offThreadJob is done
        doOtherStuff()
    }

    suspend fun fireOffThreadJob(): Job {
        return ioScope.launch {
            delay(500)
            Log.d("zzz", "offThreadJob done")
        }
    }

    private fun doOtherStuff() {
        Log.d("zzz", "otherStuff done")
    }

    // this method fails with NetworkOnMainThreadException
    private fun networkOnMainThread() {
        val delayData = api.delaySync().execute().body()!!
        Log.d("zzz", "Your IP is: ${delayData.origin}")
    }

    private fun suspendingNetworkOnMain() {
        mainScope.launch {
            val delayData = api.delay()
            Log.d("zzz", "Your IP is: ${delayData.origin}")
            findViewById<TextView>(R.id.text).setText("Your ip is: ${delayData.origin}")
        }
    }
}