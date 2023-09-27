package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Calendar
import java.util.Timer
import kotlin.concurrent.timer

class timerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_start: Button
    private lateinit var btn_refresh: Button
    private lateinit var tv_minute: TextView
    private lateinit var tv_second: TextView
    private lateinit var tv_millisecond: TextView

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

        btn_start.setOnClickListener(this)
        btn_refresh.setOnClickListener(this)

        timerToCalendarButton()
        timerToSettings()
    }

    private fun timerToCalendarButton() {
        val button = findViewById<Button>(R.id.btn_calendar)
        button.setOnClickListener {
            val intent = Intent(this, Calendar::class.java)
            startActivity(intent)
        }
    }

    private fun timerToSettings() {
        val button = findViewById<Button>(R.id.btn_settings)
        button.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
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
        btn_start.setBackgroundColor(getColor(R.color.button))
        isRunning = true

        timer = timer(period = 10) {
            // Update UI
            time++

            val milli_second = time % 100
            val second = (time % 6000) / 100
            val minute = time / 6000

            runOnUiThread {
                if (isRunning) {
                    // Update TextViews
                }
            }
        }
    }

    private fun pause() {
        btn_start.text = getString(R.string.btn_start)
        btn_start.setBackgroundColor(getColor(R.color.button))

        isRunning = false
        timer?.cancel()
    }

    private fun refresh() {
        timer?.cancel()

        btn_start.text = getString(R.string.btn_start)
        btn_start.setBackgroundColor(getColor(R.color.btn_start))
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
