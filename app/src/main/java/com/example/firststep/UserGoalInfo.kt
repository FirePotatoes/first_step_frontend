package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserGoalInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_goal_info)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 뒤로 가기 버튼을 누를 때마다 이전 액티비티로 이동
    }


    //    하단바
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

