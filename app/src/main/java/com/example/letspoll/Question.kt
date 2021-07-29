package com.example.letspoll

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.Date
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.*


class Question : AppCompatActivity() {

    lateinit var btnup:Button
    lateinit var que:EditText
    lateinit var add:Button
    lateinit var clear:Button
    lateinit var remove:Button
    lateinit var opt:EditText
    lateinit var list:ListView
    lateinit var btn:Button
    lateinit var dat:EditText
    private val questionCollectionRef = Firebase.firestore.collection("questions")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
       // Toast.makeText(this,"Firebase Success",Toast.LENGTH_SHORT).show()
        btnup=findViewById(R.id.btnupload)
        que=findViewById(R.id.etques)
        opt=findViewById(R.id.etopt)
        add=findViewById(R.id.add)
        clear=findViewById(R.id.clear)
        remove=findViewById(R.id.remove)
        list=findViewById(R.id.listView)
        btn=findViewById(R.id.btnret)
        dat=findViewById(R.id.date)

        val relativeLayout = findViewById<RelativeLayout>(R.id.llayout)


        // Initializing the array lists and the adapter
        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice
            , itemlist)
        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {

            itemlist.add(opt.text.toString())
            list.adapter =  adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input space or the eidt text space will be cleared
            opt.text.clear()
        }

        // Clearing all the items in the list when the clear button is pressed
        clear.setOnClickListener {

            itemlist.clear()
            adapter.notifyDataSetChanged()
        }
        // Adding the toast message to the list when an item on the list is pressed
        list.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "You have selected "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }

        // Selecting and Deleting the items from the list when the delete button is pressed
        remove.setOnClickListener {
            val position: SparseBooleanArray = list.checkedItemPositions
            val count = list.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }



        btnup.setOnClickListener {
            val ask=que.text.toString()
            val item1=itemlist
            val ques=Ques(ask,item1)
            saveQuestion(ques)
        }

        btn.setOnClickListener {
            // Do something in response to button click
            val intent= Intent(this,PollHere::class.java)
            startActivity(intent)
        }


    }



    private fun saveQuestion(ques:Ques)= CoroutineScope(Dispatchers.IO).launch {
        try {

            questionCollectionRef.add(ques).await()
            withContext(Dispatchers.Main){
                Toast.makeText(this@Question,"Successfully Saved data",Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception)
        {
            withContext(Dispatchers.Main){
                Toast.makeText(this@Question,e.message,Toast.LENGTH_SHORT).show()

            }
        }
    }




}


