package com.example.firststep

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class aboutapp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutapp)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }
}
