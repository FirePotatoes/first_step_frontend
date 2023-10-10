package com.example.firststep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class rakingmanagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rakingmanagement)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }
}