package com.scheduler.coroutineexample.tutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.scheduler.coroutineexample.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineSample7 : AppCompatActivity(){

    lateinit var btnStart : Button
    val log = "William"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_sample_7)

        btnStart = findViewById(R.id.btnStart)
        btnStart.setOnClickListener {
            // If this below show that even the CoroutineSample7.class is finish/kill, the main thread is still working in background
            // with this method will cause waste memory and garbage collect
            /*
            GlobalScope.launch {
                while (true){
                    delay(1000L)
                    Log.i(log, "Running....")
                }
            }
             */

            // Solution
            // when activity gone, lifecycle destroyed, then this thread will stop running as well.

            lifecycleScope.launch {
                while (true){
                    delay(1000L)
                    Log.i(log, "Running....")
                }
            }



            GlobalScope.launch {
                delay(5000L)
                Intent(this@CoroutineSample7, CoroutineSample7SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}