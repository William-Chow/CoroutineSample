package com.scheduler.coroutineexample.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class CoroutineSample6 : AppCompatActivity() {

    val log = "William"

    // Async and Await
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            /*
            val answer1 = networkCall()
            val answer2 = secondNetworkCall()

            Log.i(log, "Print the $answer1")
            Log.i(log, "Print the $answer2") // 6 seconds to complete two network call
            // Working in above way we should execute it in async way, make it run 3 seconds instead of 6 seconds
             */

            /*
            // count how many time to took for network call
            val time = measureTimeMillis {
                val answer1 = networkCall()
                val answer2 = secondNetworkCall()
                Log.i(log, "Print the $answer1")
                Log.i(log, "Print the $answer2")
            }
            Log.i(log, "Request took the $time ms.")
             */

            /*
            // with this way it will run only 3 seconds
            // because each run itself particular

            // * take note is this is bad practice -> use Async
            val time = measureTimeMillis {
                var ans1: String? = null
                var ans2: String? = null

                val job = launch { ans1 = networkCall() }
                val job2 = launch { ans2 = networkCall() }

                job.join()
                job2.join()

                Log.i(log, "Result the $ans1")
                Log.i(log, "Result the $ans2")
            }
            Log.i(log, "Request took the $time ms.")
             */

            // Use Async and await
            val time = measureTimeMillis {
                // with this way both network call will run in parallel
                val ans1 = async { networkCall() }
                val ans2 = async { secondNetworkCall() }
                Log.i(log, "Result the ${ans1.await()}")
                Log.i(log, "Result the ${ans2.await()}")
            }
            Log.i(log, "Request took the $time ms.")
        }
    }

    private suspend fun networkCall() : String {
        delay(3000L)
        return "Network Call 1"
    }

    private suspend fun secondNetworkCall() : String {
        delay(3000L)
        return "Network Call 2"
    }
}