package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.graphics.PorterDuff
import android.view.View
import android.widget.Button

class settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        goCalendarButton()
        goTimerButton()
        goSettingsButton()
    }

    private fun goSettingsButton() {
        val button = findViewById<Button>(R.id.btn_settings)
        button.setOnClickListener {
            val intent = Intent(this,settings::class.java)
            startActivity(intent)
        }
    }

    private fun goTimerButton() {
        val button = findViewById<Button>(R.id.btn_timer)
        button.setOnClickListener {
            val intent = Intent(this,timerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goCalendarButton() {
        val button = findViewById<Button>(R.id.btn_calendar)
        button.setOnClickListener {
            val intent = Intent(this,calendar::class.java)
            startActivity(intent)
        }
    }
}





