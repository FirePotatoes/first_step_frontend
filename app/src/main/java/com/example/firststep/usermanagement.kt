package com.example.firststep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class usermanagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usermanagement)
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