package com.example.coroutineapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val mainScope = CoroutineScope(Dispatchers.Main)
val ioScope = CoroutineScope(Dispatchers.IO)
val computationScope = CoroutineScope(Dispatchers.Default)

val TAG = "zzz"