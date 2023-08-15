package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginToSignupButton()
        loginToTimerButton()
    }
        fun loginToSignupButton() {
            val button = findViewById<Button>(R.id.btn_GoSignupActivity)
            button.setOnClickListener {
                val intent = Intent(this,signup::class.java)
                startActivity(intent)
            }
        }

    fun loginToTimerButton() {
        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            val intent = Intent(this,timerActivity::class.java)
            startActivity(intent)
        }
    }
}