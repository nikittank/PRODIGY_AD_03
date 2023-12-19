package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatch.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    var milliSeconds= 0
    var running= false
    var wasRunning= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runTimer()
    }


    private fun runTimer() {
        val handler= Handler()
        handler.post(object: Runnable{
            override fun run() {
                val minutes= milliSeconds / 3600
                val seconds= (milliSeconds % 3600) / 60
                val ms= milliSeconds % 100
                val time= String.format(Locale.getDefault(),"%02d:%02d:%02d", minutes, seconds, ms)
                binding.tvTimer.text= time
                if (running){
                    milliSeconds ++
                }
                handler.postDelayed(this, 10)
            }
        })

    }

    fun onClickStart(view: View) {
        running= true
    }
    fun onClickPause(view: View) {
        running= false
    }
    fun onClickReset(view: View) {
        running= false
        milliSeconds= 0
    }
}