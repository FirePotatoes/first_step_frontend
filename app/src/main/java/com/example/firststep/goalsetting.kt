package com.example.firststep

import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class goalsetting: AppCompatActivity() {


    private lateinit var goalSettingCalendarButton: ImageButton
    private lateinit var dateTextView: TextView
    private lateinit var hoursSpinner: Spinner
    private lateinit var depositTextView: TextView
    private lateinit var refundAccountSpinner: Spinner

    private lateinit var editText: EditText
    private lateinit var editText3: EditText
    private lateinit var startDate: Date
    private lateinit var endDate: Date

    private var userEnteredAmount: Int = 0 // 사용자가 입력한 금액을 저장할 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goalsetting)


        // XML 요소에 대한 참조 가져오기
        goalSettingCalendarButton = findViewById(R.id.btn_goalSettingCalendar)
        dateTextView = findViewById(R.id.editText)
        hoursSpinner = findViewById(R.id.categoryComboBox)
        depositTextView = findViewById(R.id.editText3)
        editText = findViewById(R.id.editText)
        editText3 = findViewById(R.id.editText3)

        val bankSpinner = findViewById<Spinner>(R.id.categoryComboBox2)

        // 은행 목록을 가져와서 어댑터에 설정
        val bankNames = resources.getStringArray(R.array.bankList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Spinner에 어댑터 설정
        bankSpinner.adapter = adapter

        // Calendar 초기화
        val calendar = Calendar.getInstance()


// DatePickerDialog에서 시작 날짜를 설정
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                startDate = calendar.time

                // 시작 날짜를 선택한 후에 종료 날짜 선택을 표시 (showEndDatePicker 함수 호출 제거)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()



        goalSettingCalendarButton.setOnClickListener {
            // ImageButton를 숨깁니다.
            goalSettingCalendarButton.visibility = View.GONE

            val intent = Intent(this, goalsetting::class.java)
            startActivity(intent)
        }

        // 하루 목표 시간 선택 스피너 설정
        val hoursData = mutableListOf<String>()
        for (i in 1..24) {
            hoursData.add("$i 시간")
        }
        val hoursAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hoursData)
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hoursSpinner.adapter = hoursAdapter

        // 스피너 아이템 선택 이벤트 처리
        hoursSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedHours = position + 1
                // 선택한 시간을 텍스트뷰에 표시
                depositTextView.text = "선택한 시간: $selectedHours 시간"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무것도 선택되지 않았을 때 처리
            }



        }

        // 예치금과 환불 계좌 스피너 설정은 마찬가지로 수행할 수 있습니다.

        val btnCopy: ImageView = findViewById(R.id.btn_copy)
        val textView3: TextView = findViewById(R.id.myAccountTextView)

        btnCopy.setOnClickListener {
            val accountNumber = textView3.text.toString()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            if (clipboard != null) {
                val clip = ClipData.newPlainText("계좌번호", accountNumber)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(applicationContext, "계좌번호를 복사했습니다.", Toast.LENGTH_SHORT).show()

                // 3초 후에 토스트 메시지를 사라지게 만들기
                Handler().postDelayed({
                    Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).cancel()
                }, 3000)
            } else {
                val errorMessage = "클립보드 관리자를 사용할 수 없습니다. 앱 권한 또는 장치 설정을 확인하세요."
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
            }
        }

        showDatePickerDialog()
        goalSettingToTimer()
    }


    // 사용자가 목표 기간을 선택한 후 호출되는 함수
    private fun onGoalPeriodSelected(startDate: Date, endDate: Date) {
        // 날짜 간의 차이를 계산하여 일 수를 얻습니다.
        val dayInMillis = 24 * 60 * 60 * 1000
        val days = ((endDate.time - startDate.time) / dayInMillis).toInt()

        // 최소 및 최대 금액 계산
        val (minAmount, maxAmount) = calculateDepositRange(days)

        // 사용자가 목표 기간을 선택한 후 계산된 범위로 editText3에 텍스트 설정
        editText3.setText("최소 금액: $minAmount 원, 최대 금액: $maxAmount 원")
    }

    // 일 수에 따라 최소 및 최대 금액을 계산하는 함수
    private fun calculateDepositRange(days: Int): Pair<Int, Int> {
        val minAmount: Int
        val maxAmount: Int

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

        return Pair(minAmount, maxAmount)
    }

    // 'goalSettingToTimer' 함수 내에서 호출되는 부분 수정
    private fun goalSettingToTimer() {
        val button = findViewById<Button>(R.id.btn_goalSettingComplete)
        button.setOnClickListener {
            // editText3에 표시된 내용을 가져와서 파싱
            val depositRangeText = editText3.text.toString()
            val (minAmount, maxAmount) = parseDepositRange(depositRangeText)

            // 사용자가 입력한 금액이 유효한지 확인하고 범위 내에 있는지 검사
            if (isValidDeposit(userEnteredAmount, minAmount, maxAmount)) {
                // 조건에 맞으면 TimerActivity로 이동
                val intent = Intent(this, timerActivity::class.java)
                startActivity(intent)
            } else {
                // 조건에 맞지 않으면 오류 메시지 표시
                val errorMessage =
                    "금액 범위를 벗어났습니다. 최소 금액: $minAmount 원, 최대 금액: $maxAmount 원"
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 'goalSettingToTimer' 함수 내에서 호출되는 부분 수정
    private fun parseDepositRange(depositRangeText: String): Pair<Int, Int> {
        val regex = Regex("(\\d+) 원, (\\d+) 원")
        val matchResult = regex.find(depositRangeText)

        return if (matchResult != null) {
            val (minAmountStr, maxAmountStr) = matchResult.destructured
            Pair(minAmountStr.toInt(), maxAmountStr.toInt())
        } else {
            Pair(0, 0)
        }
    }

    // DatePickerDialog 표시
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                // 선택한 날짜를 텍스트뷰에 표시
                dateTextView.text = "$selectedYear-$selectedMonth-$selectedDay"
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    // 종료 날짜 선택 DatePickerDialog 표시
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
                onGoalPeriodSelected(startDate, endDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    // 예치금이 유효한지 확인하는 함수
    private fun isValidDeposit(depositAmount: Int, minAmount: Int, maxAmount: Int): Boolean {
        return depositAmount >= minAmount && depositAmount <= maxAmount
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }
}