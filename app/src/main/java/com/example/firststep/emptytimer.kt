package com.example.firststep

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class emptytimer : AppCompatActivity() {

    private val bibleVerses = listOf(
        "너희 피가 나를 위하여 속죄물이 될 것이요, 너희 땅도 속죄물이 될 것이니라. [레위기 17:11]",
        "내가 가리켜 네게 가르칠 길은 너희 행할 길이요, 내가 너희를 눈으로 주시고 너희 발을 꾸짖을 대로 보이리라. [시편 32:8]",
        "네 시작은 미약하였으나 네 나중은 심히 창대하리라. [욥기 8:7]",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emptytimer)
        emptytimerToGoalsetting()

        // 날짜 표시
        val textViewDate = findViewById<TextView>(R.id.textViewDate)
        val currentDate = getCurrentDateWithDay()
        textViewDate.text = "$currentDate"

        // 성경구절
        val textViewVerse = findViewById<TextView>(R.id.textViewVerse)
        val dayOfYear = getDayOfYear()
        val selectedVerse = bibleVerses[dayOfYear % bibleVerses.size]
        textViewVerse.text = "$selectedVerse"

        goCalendarButton()
        goSettingsButton()
        goTimerButton()
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

    private fun emptytimerToGoalsetting() {
        val button = findViewById<Button>(R.id.btn_goalAdd)
        button.setOnClickListener {
            val intent = Intent(this, goalsetting::class.java)
            startActivity(intent)
        }
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
