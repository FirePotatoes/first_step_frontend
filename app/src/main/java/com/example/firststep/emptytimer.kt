package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class emptytimer : AppCompatActivity() {

    private val bibleVerses = listOf(
        "너희 피가 나를 위하여 속죄물이 될 것이요, 너희 땅도 속죄물이 될 것이니라. [레위기 17:11]",
        "내가 가리켜 네게 가르칠 길은 너희 행할 길이요, 내가 너희를 눈으로 주시고 너희 발을 꾸짖을 대로 보이리라. [시편 32:8]",
        "네 시작은 미약하였으나 네 나중은 심히 창대하리라. [욥기 8:7]",
        "우리가 알거니와 하나님을 사랑하는 자 곧 그의 뜻대로 부르심을 입은 자들에게는 모든 것이 합력하여 선을 이루느니라. [롬 8:28]",
        "만일 우리가 우리 죄를 자백하면 그는 미쁘시고 의로우사 우리 죄를 사하시며 우리를 모든 불의에서 깨끗하게 하실 것이요. [요일 1:1]",
        "나의 하나님이 그리스도 예수 안에서 영광 가운데 그 풍성한 대로 너희 모든 쓸 것을 채우시리라. [빌 4:19]",
        "너희 염려를 다 주께 맡기라 이는 그가 너희를 돌보심이라. [벧전 5:7]",
        "내가 네게 명령한 것이 아니냐 강하고 담대하라 두려워하지 말며 놀라지 말라 네가 어디로 가든지 네 하나님 여호와가 너와 함께 하느니라 하시니라. [수 1:9]",
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
        val textViewVerse = findViewById<TextView>(R.id.bibleView)
        val dayOfYear = getDayOfYear()
        val selectedVerse = bibleVerses[dayOfYear % bibleVerses.size]
        textViewVerse.text = "$selectedVerse"

        goCalendarButton()
        goSettingsButton()
        goTimerButton()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
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
