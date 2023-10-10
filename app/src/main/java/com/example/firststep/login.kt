package com.example.firststep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginToSignupButton()
        loginToEmptyTimerButton()
    }
    private fun loginToSignupButton() {
        val button = findViewById<Button>(R.id.btn_GoSignupActivity)
        button.setOnClickListener {
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
        }
    }

    private fun loginToEmptyTimerButton() {
        val button = findViewById<Button>(R.id.btn_login)

        button.setOnClickListener {
            println("안녕")
            GlobalScope.launch(Dispatchers.IO) {
                val HOST = "x.kamilake.com"
                val PORT = 7023
                val USERNAME = "hamonkbu"
                val PASSWORD = "FDEEiJVFbu6UMEN4"

                try {
                    val jsch = JSch()
                    val session: Session = jsch.getSession(USERNAME, HOST, PORT)
                    session.setPassword(PASSWORD)
                    val config = Properties()
                    config["StrictHostKeyChecking"] = "no"
                    session.setConfig(config)
                    session.connect()

                    // SSH 명령 실행
                    val command = "ls -al" // 실행할 명령
                    val channel = session.openChannel("exec") as ChannelExec
                    channel.setCommand("ls -al")
                    channel.connect()

                    // 명령 실행 결과 읽기
                    val inputStream = channel.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        println("성공"+line)
                    }

                    // 연결 종료
                    channel.disconnect()
                    session.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            val intent = Intent(this, emptytimer::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }

}