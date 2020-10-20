package com.example.coroutineapp.usecases

import android.util.Log
import com.example.coroutineapp.TAG
import com.example.coroutineapp.computationScope
import com.example.coroutineapp.ioScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.String

object MassLaunching {
    fun io() {
        for (i in 1..120) {
            doJob(ioScope, i)
        }
    }

    fun computation() {
        for (i in 1..120) {
            doJob(computationScope, i)
        }
    }

    fun doJob(scope: CoroutineScope, i: Int) {
        scope.launch {
            val counter = String.format("%03d", i)
            Log.d(TAG, "Job $counter starting")
            delay(250)
            Log.d(TAG, "Job $counter DONE")
        }
    }
}