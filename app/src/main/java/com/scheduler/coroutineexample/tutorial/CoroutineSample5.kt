package com.scheduler.coroutineexample.tutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class CoroutineSample5 : AppCompatActivity() {

    val log = "William"

    // Coroutine Job
    // Job, Waiting, Cancellation

    // one job equal to one coroutine
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // launch function will return as a job, and save this job as a variable
        val job = GlobalScope.launch(Dispatchers.Default) {
            /*
            repeat(5) {
                Log.i(log, "Coroutine is still working...")
                delay(1000L)
            }
             */

            // In this situation, if the current coroutine is too busy with the for loop here for calculation so that is no time to check the cancellation
            // due to fib method is not using suspend function and no pause method there, so it won't stop at all
            // * so we need to check manual way by using "isActive"
            // "isActive" will check the job is it still active.
           /*
            Log.i(log, "Start long running Calculation...")
            for (i in 30..40) {
                Log.i(log, "Result for i = $i: ${fib(i)}")
            }
            Log.i(log, "End long running Calculation...")
            */

            // Like this
            /*
            Log.i(log, "Start long running Calculation...")
            for (i in 30..40) {
                if(isActive) Log.i(log, "Result for i = $i: ${fib(i)}")
            }
            Log.i(log, "End long running Calculation...")
             */

            // Reason doing like above due to the we may happen timeout issues
            // if the thing took too much time so we need to cancel it
            // so we need to use withTimeout method
            Log.i(log, "Start long running Calculation...")
            withTimeout(3000L) {
                for (i in 30..50) {
                    if(isActive) Log.i(log, "Result for i = $i: ${fib(i)}")
                }
            }
            Log.i(log, "End long running Calculation...")
            // so with this we no longer needed this runBlocking -> job.cancel()
        }

        // we can wait this job done only continue proceed main thread

        // Join
        /*
        runBlocking {
        // we will block main thread, to wait "job" finish first
            job.join()
            Log.i(log, "Main Thread is continuing... ")
            // so, the main thread still there
        }
         */

        // Cancel
        /*
        runBlocking {
            delay(3000L)
            job.cancel()
            Log.i(log, "Cancelled Job!")
            // so i cancel the job after 3 seconds but the thread is still there
        }
         */
    }

    private fun fib(n: Int): Long {
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fib(n - 1) + fib(n - 2)
        }
    }
}