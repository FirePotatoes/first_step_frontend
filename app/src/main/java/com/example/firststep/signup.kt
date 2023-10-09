package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        signUpButton()


        val bankSpinner = findViewById<Spinner>(R.id.bankSpinner)

        // 은행 목록을 가져와서 어댑터에 설정
        val bankNames = resources.getStringArray(R.array.bankList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Spinner에 어댑터 설정
        bankSpinner.adapter = adapter
    }
        private fun signUpButton() {
            val button = findViewById<Button>(R.id.btn_signup)
            button.setOnClickListener {
                val intent = Intent(this,login::class.java)
                startActivity(intent)
            }
        }
    }