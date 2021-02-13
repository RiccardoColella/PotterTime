package com.example.pottertime

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.lang.Math.floor
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textTime: TextView = findViewById(R.id.PTime) as TextView

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                updateTime(textTime)
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTime(text : TextView){
        val time : LocalDateTime = LocalDateTime.now()
        val totalSeconds = (time.hour * 60 * 60 + time.minute * 60 + time.second) / 0.96
        val hour = floor(totalSeconds / 3600).toInt()
        val minute = (floor(totalSeconds / 60 ) % 60).toInt()
        val second = floor(totalSeconds % 60).toInt()
        val toDisplay = hour.toString() + " : " + minute.toString() + " : " + second.toString()
        text.text = toDisplay
    }
}