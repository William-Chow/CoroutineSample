package com.scheduler.coroutineexample.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineSample2 : AppCompatActivity() {

    val log = "William"
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Overview of Suspend Function - delay

        // Suspend function 'delay' should be called only from a coroutine or another suspend function
        // X - delay(1000L)
        GlobalScope.launch {
            // Suspend keyword is only work with suspend method of function
            // delay(1000L) // -> this is suspend function

            val returnNetworkCall = doNetworkCall()
            val returnNetworkCall2 = doNetworkCallAgain()
            // reason why it is 6 seconds, because they are in same coroutine, so it will auto add up the delay

            Log.i(log, returnNetworkCall)
            Log.i(log, returnNetworkCall2)
        }
    }

    private suspend fun doNetworkCall() : String{
        delay(3000L)
        return "This is Network Call, take some time here 3 seconds"
    }

    private suspend fun doNetworkCallAgain() : String{
        delay(3000L)
        return "This is Network Call2, take some time here 3 seconds"
    }
}