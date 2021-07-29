package com.example.letspoll

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.String


class Result : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val button=findViewById<Button>(R.id.button_click)
        val txt=findViewById<TextView>(R.id.time_left)
        var counter=30

        button.setOnClickListener {
            Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
            object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    txt.setText(String.valueOf(counter))
                    counter--
                }
                override fun onFinish() {
                    txt.setText("FINISH!!")
                }
            }.start()
        }
    }
}