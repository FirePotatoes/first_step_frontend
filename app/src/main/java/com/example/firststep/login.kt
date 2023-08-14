package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fun settingButton() {
            val button = findViewById<Button>(R.id.btnGoSignupActivity)
            button.setOnClickListener {
                //signup activity로 이동
                val intent = Intent(this,signup::class.java)
                startActivity(intent)
            }
        }


    }
}