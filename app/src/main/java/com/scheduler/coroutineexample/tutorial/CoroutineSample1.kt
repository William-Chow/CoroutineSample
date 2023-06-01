package com.scheduler.coroutineexample.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineSample1 : AppCompatActivity() {

    val log = "William"
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.i(log, "Hello, Main Thread")

        // This Coroutine will be another thread instead of main thread
        // Delay similar to sleep or block thread
        GlobalScope.launch {
            delay(5000L) // Delay will just block the current coroutine but no block the main thread
            Log.i(log, "Coroutine said hello from thread ${Thread.currentThread().name}")
            // it work as asynchronous - if main thread get quit or killed, the current coroutine won't run as well due to cancelled/finished main thread.
        }

        Log.i(log, "This is Main Thread ${Thread.currentThread().name}")
    }
}