package com.example.firststep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }
}