package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        accountToUserGoalInfoButton()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }

    private fun accountToUserGoalInfoButton() {
        val button = findViewById<Button>(R.id.btn_goto_UserGoalInfo)
        button.setOnClickListener {
            val intent = Intent(this,UserGoalInfo::class.java)
            startActivity(intent)
        }
    }
}