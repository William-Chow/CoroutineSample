package com.scheduler.coroutineexample.tutorial

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.scheduler.coroutineexample.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineSample3 : AppCompatActivity() {

    lateinit var tvTesting : TextView
    val log = "William"
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_sample_3)

        tvTesting = findViewById(R.id.tvTesting)

        Log.i(log, "Hello, Main Thread")

        // GlobalScope.launch {
            // Dispatchers.Main - use this for the UI operation, can only change the UI from Main Thread
            // Dispatchers.IO - use for data operation, write database, networking, or writing to FILES
            // Dispatchers.Default - use for long calculation or complex algorithm, compile list of thousand record
            // Dispatchers.Unconfined - can use in testing purpose

            // can create own thread name - newSingleThreadContext("MyThread")
        // }

        GlobalScope.launch(Dispatchers.IO) {
            Log.i(log, " Start Coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.i(log, " Setting text in thread ${Thread.currentThread().name}")
                tvTesting.text = answer
            }
        }

        Log.i(log, "What is here?")
    }

    private suspend fun doNetworkCall() : String{
        delay(3000L)
        return "This is Network Call"
    }
}