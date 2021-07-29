package com.example.letspoll


import android.os.Build
import androidx.annotation.RequiresApi
import com.google.type.Date
import com.google.type.DateTime
import java.time.LocalDate
import kotlin.collections.ArrayList

data class Ques @RequiresApi(Build.VERSION_CODES.O) constructor(
    var ask:String="",
    var item1: ArrayList<String> = arrayListOf<String>()

)