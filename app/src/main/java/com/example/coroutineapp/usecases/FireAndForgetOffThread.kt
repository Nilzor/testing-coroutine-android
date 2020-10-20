package com.example.coroutineapp.usecases

import android.util.Log
import com.example.coroutineapp.TAG
import com.example.coroutineapp.ioScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// This test illustrates that SameThreadJob will be
// completed before fireNonSuspendingOffThreadJob
object FireAndForgetOffThread {
    fun go() {
        fireNonSuspendingOffThreadJob()
        doSameThreadJob()
    }

    private fun doSameThreadJob() {
        Log.d(TAG, "SameThreadJob done")
    }

    private fun fireNonSuspendingOffThreadJob() {
        ioScope.launch {
            Log.i(TAG, "Offthread longrunning job starting")
            delay(500)
            Log.i(TAG, "Offthread longrunning job completed")
        }
    }
}