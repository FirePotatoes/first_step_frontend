package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.graphics.PorterDuff
import android.view.View
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        goCalendarButton()
        goTimerButton()
        goSettingsButton()
        goTermsOfUSeButton()
        goAccountButton()
        goInfoButton()
    }


    private fun goAccountButton() {
        val button = findViewById<Button>(R.id.btn_account)
        button.setOnClickListener {
            val intent = Intent(this,account::class.java)
            startActivity(intent)
        }
    }

    private fun goInfoButton() {
        val button = findViewById<Button>(R.id.btn_appinfo)
        button.setOnClickListener {
            val intent = Intent(this,aboutapp::class.java)
            startActivity(intent)
        }
    }

    private fun goTermsOfUSeButton() {
        val button = findViewById<Button>(R.id.btn_termsofuse)
        button.setOnClickListener {
            val intent = Intent(this,termsofuse::class.java)
            startActivity(intent)
        }
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

    private fun getDayOfYear(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_YEAR)
    }

    private fun getCurrentDateWithDay(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 (E)", Locale.getDefault())
        val date = calendar.time
        return dateFormat.format(date)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 뒤로 가기 버튼을 누를 때마다 이전 액티비티로 이동
    }
}





