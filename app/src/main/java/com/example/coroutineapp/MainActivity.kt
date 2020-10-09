package com.example.coroutineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    val mainScope = CoroutineScope(Dispatchers.Main)
    val api = HttpBinApi.getApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StrictMode.enableDefaults()

        findViewById<TextView>(R.id.text).setText("Loading data..")
    }


    override fun onResume() {
        super.onResume()

        suspendingNetworkOnMain()
        findViewById<ProgressBar>(R.id.progressBar)
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