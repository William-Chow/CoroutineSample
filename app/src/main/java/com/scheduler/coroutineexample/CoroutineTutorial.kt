package com.scheduler.coroutineexample

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.scheduler.coroutineexample.tutorial.CoroutineSample1
import com.scheduler.coroutineexample.tutorial.CoroutineSample2
import com.scheduler.coroutineexample.tutorial.CoroutineSample3
import com.scheduler.coroutineexample.tutorial.CoroutineSample4
import com.scheduler.coroutineexample.tutorial.CoroutineSample5
import com.scheduler.coroutineexample.tutorial.CoroutineSample6
import com.scheduler.coroutineexample.tutorial.CoroutineSample7

class CoroutineTutorial : AppCompatActivity() {

    private lateinit var tvTutorial1 : TextView
    private lateinit var tvTutorial2 : TextView
    private lateinit var tvTutorial3 : TextView
    private lateinit var tvTutorial4 : TextView
    private lateinit var tvTutorial5 : TextView
    private lateinit var tvTutorial6 : TextView
    private lateinit var tvTutorial7 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        init()
    }

    // A thread describes in which context this sequence of instructions should be executed.
    // E.g.
    // Log.i("log", "Hello World")
    // var x = 3
    // x *= x
    // Log.i("log", "The Result is $x")

    // Threading important for android apps?
    // E.g.
    // initView()
    // updateUI()
    // updateUI()
    // Log.i("log", "Main thread Log")
    // updateUI()
    // No issues, because ntg to wait
    // ---------------------------------------
    // if got network call, show UI depend on the Network Call
    // initView()
    // updateUI()
    // doNetworkCall()
    // updateUI() <- this depend Network Call, then UI blocked, lagging and cause User experience bad
    // Log.i("log", "Main thread Log")
    // updateUI()
    // ---------------------------------------
    // Solution is -
    // initView()
    // updateUI()
    // Thread{ doNetworkCall() }.start()
    // updateUI()
    // Log.i("log", "Main thread Log")
    // updateUI()
    // Multi-threading is for Network Call, Database Operations, Complex Calculation
    // Coroutine like lightweight thread, can do what thread do.

    private fun init(){
        tvTutorial1 = findViewById(R.id.tvTutorial_1)
        tvTutorial1.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample1::class.java))
        }

        tvTutorial2 = findViewById(R.id.tvTutorial_2)
        tvTutorial2.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample2::class.java))
        }

        tvTutorial3 = findViewById(R.id.tvTutorial_3)
        tvTutorial3.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample3::class.java))
        }

        tvTutorial4 = findViewById(R.id.tvTutorial_4)
        tvTutorial4.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample4::class.java))
        }

        tvTutorial5 = findViewById(R.id.tvTutorial_5)
        tvTutorial5.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample5::class.java))
        }

        tvTutorial6 = findViewById(R.id.tvTutorial_6)
        tvTutorial6.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample6::class.java))
        }

        tvTutorial7 = findViewById(R.id.tvTutorial_7)
        tvTutorial7.setOnClickListener {
            this@CoroutineTutorial.startActivity(Intent(this@CoroutineTutorial, CoroutineSample7::class.java))
        }

        // Coroutine can good use with Retrofit, to shorten the code of request response.
    }

}