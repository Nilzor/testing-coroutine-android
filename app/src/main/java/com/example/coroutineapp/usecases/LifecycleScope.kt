package com.example.coroutineapp.usecases

import kotlinx.coroutines.delay

object LifecycleScope {
    var counter = 1

    suspend fun slowFeedback(): String {
        delay(3000)
        return "slowFeedback() Invocation number ${counter++}"
    }
}