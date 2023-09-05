package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class emptytimer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emptytimer)
        emptytimerToGoalsetting()
    }

    private fun emptytimerToGoalsetting() {
        val button = findViewById<Button>(R.id.btn_goalAdd)
        button.setOnClickListener {
            val intent = Intent(this,goalsetting::class.java)
            startActivity(intent)
        }
    }
}