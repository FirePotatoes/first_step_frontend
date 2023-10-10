package com.example.firststep

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class goalsetting : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var editText3: EditText
    private lateinit var startDate: Date
    private lateinit var endDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goalsetting)

        editText = findViewById(R.id.editText)
        editText3 = findViewById(R.id.editText3)

        // Calendar 초기화
        val calendar = Calendar.getInstance()

        // DatePickerDialog에서 시작 날짜를 설정
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                startDate = calendar.time

                // 두 번째 DatePickerDialog를 표시
                showEndDatePicker()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun showEndDatePicker() {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                endDate = calendar.time

                // 선택한 날짜를 editText에 표시
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val startDateStr = dateFormat.format(startDate)
                val endDateStr = dateFormat.format(endDate)
                editText.setText("$startDateStr ~ $endDateStr")

                // 예치금 계산 및 설정
                calculateAndSetDeposit(startDate, endDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun calculateAndSetDeposit(startDate: Date, endDate: Date) {
        // 두 날짜 사이의 일 수 계산
        val dayInMillis = 24 * 60 * 60 * 1000
        val days = ((endDate.time - startDate.time) / dayInMillis).toInt()

        // 시작 날짜와 종료 날짜에 따라 예치금 범위 설정
        val minAmount: Int
        val maxAmount: Int

        val calendar = Calendar.getInstance()
        calendar.time = startDate

        when {
            days >= 3 && days <= 15 -> {
                // 3일부터 15일까지의 경우
                minAmount = 3000
                maxAmount = 10000
            }
            days >= 16 && days <= 30 -> {
                // 16일부터 30일까지의 경우
                minAmount = 7000
                maxAmount = 20000
            }
            days >= 90 -> {
                // 3개월 이상인 경우
                if (days < 180) {
                    // 3개월
                    minAmount = 10000
                    maxAmount = 30000
                } else if (days < 270) {
                    // 6개월
                    minAmount = 15000
                    maxAmount = 40000
                } else if (days < 365) {
                    // 9개월
                    minAmount = 17000
                    maxAmount = 50000
                } else {
                    // 12개월 이상
                    minAmount = 20000
                    maxAmount = 60000
                }
            }
            else -> {
                // 다른 범위에 속하지 않는 경우
                minAmount = 0
                maxAmount = 0
            }
        }

        // 예치금 계산
        val depositAmount = minAmount * days

        if (isValidDeposit(depositAmount, minAmount, maxAmount)) {
            // 예치금이 유효한 경우
            editText3.setText("$depositAmount 원")
        } else {
            // 조건에 맞지 않는 경우 오류 메시지 표시
            val errorMessage =
                if (depositAmount < minAmount) "최소 금액 미달, 요일과 금액을 확인해주세요."
                else "최대 금액 초과, 요일과 금액을 확인해주세요."
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidDeposit(depositAmount: Int, minAmount: Int, maxAmount: Int): Boolean {
        return depositAmount >= minAmount && depositAmount <= maxAmount
    }
}
