package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.service.notification.NotificationListenerService.Ranking
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.Calendar
import java.util.Timer
import java.util.TimerTask

class timerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_start: Button
    private lateinit var btn_refresh: Button
    private lateinit var btn_ranking: Button
    private lateinit var tv_minute: TextView
    private lateinit var tv_second: TextView
    private lateinit var tv_millisecond: TextView
    private lateinit var progressBar: ProgressBar

    private var isRunning = false
    private var timer: Timer? = null
    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        btn_start = findViewById(R.id.btn_start)
        btn_refresh = findViewById(R.id.btn_refresh)
        tv_minute = findViewById(R.id.tv_minute)
        tv_second = findViewById(R.id.tv_second)
        tv_millisecond = findViewById(R.id.tv_millisecond)

        progressBar = findViewById(R.id.progress_bar)

        btn_start.setOnClickListener(this)
        btn_refresh.setOnClickListener(this)

        timerToCalendarButton()
        timerToSettings()
        timerToRanking()
    }

    private fun timerToCalendarButton() {
        val button = findViewById<Button>(R.id.btn_calendar)
        button.setOnClickListener {
            val intent = Intent(this, calendar::class.java)
            startActivity(intent)
        }
    }

    private fun timerToSettings() {
        val button = findViewById<Button>(R.id.btn_settings)
        button.setOnClickListener {
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }
    }

    private fun timerToRanking() {
        val button = findViewById<Button>(R.id.btn_ranking)
        button.setOnClickListener {
            val intent = Intent(this, ranking::class.java)
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

                val milli_second = time % 100
                val second = (time / 100) % 60
                val minute = (time / 6000) % 60

                runOnUiThread  {
                    if (isRunning) {
                        // Update TextViews
                        tv_millisecond.text = String.format(",%02d", milli_second)
                        tv_second.text = String.format(":%02d", second)
                        tv_minute.text = String.format("%02d", minute)

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
        tv_millisecond.text = ",00"
        tv_second.text = ":00"
        tv_minute.text = "00"
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}
