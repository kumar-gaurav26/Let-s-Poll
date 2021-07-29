package com.example.letspoll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val button: Button = findViewById(R.id.host)
        button.setOnClickListener {
            // Do something in response to button click
            val intent= Intent(this,Question::class.java)
            startActivity(intent)
        }

        val btn: Button = findViewById(R.id.join)
        btn.setOnClickListener {
            // Do something in response to button click
            val intent= Intent(this,Wait_Host::class.java)
            startActivity(intent)
        }

        val hombtn: Button = findViewById(R.id.home)
        hombtn.setOnClickListener {
            // Do something in response to button click
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}