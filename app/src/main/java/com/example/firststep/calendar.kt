package com.example.firststep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Entity
import androidx.room.PrimaryKey




class calendar : AppCompatActivity() {

    private lateinit var binding : activityCalendar

    private lateinit var db : AppDatabaase

//calendarBinding 선언 안됨 오류남, 그냥 calendar 하면 inflate 안됨
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = activityCalendar.inflate(layoutInflater)
        setContentView(binding.root)


    }
}