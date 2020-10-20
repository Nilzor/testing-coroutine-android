package com.example.coroutineapp.usecases

import android.util.Log
import com.example.coroutineapp.TAG
import com.example.coroutineapp.ioScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// This test illustrates that calling ".join" will wait suspend
// and wait for offthread job

object WaitForJobOffThread {
    suspend fun go() {
        fireOffThreadJob().join() // join() makes us suspend until offThreadJob is done
        doSameThreadJob()
    }

    private fun fireOffThreadJob(): Job {
        return ioScope.launch {
            Log.i(TAG, "Offthread longrunning job starting")
            delay(500)
            Log.i(TAG, "Offthread longrunning job completed")
        }
    }

    private fun doSameThreadJob() {
        Log.d(TAG, "SameThreadJob done")
    }
}