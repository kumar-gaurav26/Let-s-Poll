package com.example.letspoll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PollHere : AppCompatActivity() {


    lateinit var tvques:TextView
    lateinit var btnretrive:Button

    private val questionCollectionRef = Firebase.firestore.collection("questions")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll_here)

        tvques=findViewById(R.id.tvQuestions)
        btnretrive=findViewById(R.id.btnretrive)

        btnretrive.setOnClickListener {
            retrieveQuestion()

        }


    }


    private fun retrieveQuestion() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = questionCollectionRef.get().await()
            val sb = StringBuilder()

            questionCollectionRef.orderBy("date").limit(3)
            var count=3
            for(document in querySnapshot.documents) {
                if(count!=0) {
                    val question = document.toObject(Ques::class.java)
                    sb.append("$question\n")
                    count--
                }
                else
                    break

            }

            withContext(Dispatchers.Main) {
                tvques.text = sb.toString()
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@PollHere, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}