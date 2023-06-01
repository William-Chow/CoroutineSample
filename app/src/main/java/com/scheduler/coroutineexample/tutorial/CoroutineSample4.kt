package com.scheduler.coroutineexample.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking
class CoroutineSample4 : AppCompatActivity() {

    val log = "William"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(log, "Hello, Main Thread")

        // Different between GlobalScope.launch and runBlocking
        // if using GlobalScope.launch for delay method will still able to use UI, UI continue run itself
        // if using runBlocking for delay method this will block all UI update
        // GlobalScope.launch(Dispatchers.Main) {  }

        Log.i(log, "Before runBlocking")
        runBlocking {
            // Two launch will run continuously
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.i(log, "Finish IO Coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.i(log, "Finish IO Coroutine 2")
            }
            Log.i(log, "Started runBlocking")
            delay(5000L)
            Log.i(log, "End of runBlocking")

            // So in short
            // launch coroutine it run itself then finish the IO then main coroutine will finish after another 2 seconds

            // * runBlocking will only affect the main thread, and no affect on other coroutine
        }
        Log.i(log, "After runBlocking")


        // Same as
        // Log.i(log, "Thread Before runBlocking")
        // Log.i(log, "Thread Started runBlocking")
        // Thread.sleep(5000L)
        // Log.i(log, "Thread End of runBlocking")
        // Log.i(log, "Thread After runBlocking")

        // So it force the MainThread to delay it, sleep for 5 seconds
    }
}