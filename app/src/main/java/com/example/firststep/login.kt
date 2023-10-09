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
        loginToEmptyTimerButton()
    }
    private fun loginToSignupButton() {
        val button = findViewById<Button>(R.id.btn_GoSignupActivity)
        button.setOnClickListener {
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
        }
    }

    private fun loginToEmptyTimerButton() {
        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            val intent = Intent(this,emptytimer::class.java)
            startActivity(intent)
        }
    }
}