package com.example.firststep

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class timerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_start: Button
    private lateinit var btn_refresh: Button
    private lateinit var btn_ranking: Button
    private lateinit var tv_hour: TextView
    private lateinit var tv_minute: TextView
    private lateinit var tv_second: TextView
    private lateinit var progressBar: ProgressBar

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

    private var isRunning = false
    private var timer: Timer? = null
    private var time = 0
    private var progress = 0

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        btn_start = findViewById(R.id.btn_start)
        btn_refresh = findViewById(R.id.btn_refresh)
        tv_hour = findViewById(R.id.tv_hour)
        tv_minute = findViewById(R.id.tv_minute)
        tv_second = findViewById(R.id.tv_second)

        progressBar = findViewById(R.id.progress_bar)

        btn_start.setOnClickListener(this)
        btn_refresh.setOnClickListener(this)


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

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_start -> {
                if (isRunning) {
                    pause()
                } else {
                    start()
                }
            }

            R.id.btn_refresh -> {
                refresh()
            }
        }
    }

    private fun start() {
        btn_start.text = getString(R.string.btn_pause)
        btn_start.setBackgroundColor(ContextCompat.getColor(this, R.color.button))
        isRunning = true

        progressBar.visibility = View.VISIBLE
        progressBar.progress = 0

        val maxTime = 6000
        // 타이머가 60초 동안 실행되어야 한다면 6000으로 설정 목표 추가에서 설정한 값이 여기로 온다

        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                // Update UI
                time++

//                val millisecond = time % 100
                val second = (time / 100) % 60
                val minute = (time / 6000) % 60
                val hour = (time / 360000)

                runOnUiThread  {
                    if (isRunning) {
                        // Update TextViews
//                        tv_millisecond.text = String.format(".%02d", millisecond)
                        tv_hour.text = String.format("%02d", hour)
                        tv_minute.text = String.format(":%02d", minute)
                        tv_second.text = String.format(".%02d", second)

                        // Update progress bar
                        val progress = (time * 100 / maxTime).toInt()
                        progressBar.progress = progress
                    }
                }
            }
        }, 0, 10)
    }


    private fun pause() {
        btn_start.text = getString(R.string.btn_start)
        btn_start.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_start))

        isRunning = false
        timer?.cancel()
    }

    private fun refresh() {
        timer?.cancel()

        btn_start.text = getString(R.string.btn_start)
        btn_start.setBackgroundColor(ContextCompat.getColor(this, R.color.btn_start))
        isRunning = false

        time = 0
//        tv_millisecond.text = ",00"
        tv_second.text = "00"
        tv_minute.text = "00"
        tv_hour.text = "00"
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }

}
