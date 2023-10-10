package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Actionbar 제거
                supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            Intent(this, login::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 2500) // 3초 후(3000) 스플래시 화면을 닫습니다

    }
    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }
}