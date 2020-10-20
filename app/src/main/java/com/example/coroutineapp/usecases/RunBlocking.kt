package com.example.coroutineapp.usecases

import android.util.Log
import com.example.coroutineapp.TAG
import com.example.coroutineapp.ioScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Two use cases in one
object RunBlocking {

    // Ilustrate how runblocking works.
    // Calling thread will be blocked
    fun runOffThreadBlocking() {
        runBlocking {
            offThreadJob()
            doSameThreadJob()
        }
    }

    // Illustrates that runBlocking is negated by any
    // sub-method doing "launch"
    fun runBlockingWithInnerLaunch() {
        runBlocking {
            ioScopeWrappingOffThreadJob()
            doSameThreadJob()
        }
    }

    private fun ioScopeWrappingOffThreadJob() {
        ioScope.launch {
            offThreadJob()
        }
    }

    private suspend fun offThreadJob() {
        Log.i(TAG, "Offthread longrunning job starting")
        delay(3000)
        Log.i(TAG, "Offthread longrunning job completed")
    }

    private fun doSameThreadJob() {
        Log.d(TAG, "SameThreadJob done")
    }
}