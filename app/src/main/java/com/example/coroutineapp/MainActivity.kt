package com.example.coroutineapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.coroutineapp.usecases.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .detectNetwork().penaltyDeathOnNetwork()
            .build())
        findViewById<TextView>(R.id.text).setText("Loading data..")
    }

    fun retrofitBlocking(view: View) {
        val delayData = RetrofitAsync.blockingGet()
        findViewById<TextView>(R.id.text).setText("Your ip is: ${delayData.origin}")
    }

    fun retrofitSuspending(view: View) {
        mainScope.launch {
            val delayData = RetrofitAsync.suspendingGet()
            findViewById<TextView>(R.id.text).setText("Your ip is: ${delayData.origin}")
        }
    }

    fun fireAndForgetOffThread(view: View) {
        FireAndForgetOffThread.go()
    }

    fun waitForJobOffthread(view: View) {
        mainScope.launch {
            WaitForJobOffThread.go()
        }
    }

    fun runBlockingStandard(view: View) {
        RunBlocking.runOffThreadBlocking()
    }

    fun runBlockingWithLaunch(view: View) {
        RunBlocking.runBlockingWithInnerLaunch()
    }

    fun massLaunchIo(view: View) {
        MassLaunching.io()
    }

    fun massLaunchComputation(view: View) {
        MassLaunching.computation()
    }

    fun activityScope(view: View) {
        lifecycleScope.launch {
            val text = LifecycleScope.slowFeedback()
            findViewById<TextView>(R.id.text).text = text
        }
    }
}