package com.example.firststep


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import com.example.firststep.R.id.btn_goalsettingcalendar
import java.util.Calendar
import java.util.GregorianCalendar

class goalsetting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goalsetting)
        /*
                val goalsettingcalendarButton = findViewById<Button>(btn_goalsettingcalendar)

                var startDate = ""

                goalsettingcalendarButton.setOnClickListener {
                    val today = GregorianCalendar()
                    val year = today.get(Calendar.YEAR)
                    val month = today.get(Calendar.MONTH)
                    val day = today.get(Calendar.DATE)

                    val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                            TODO("Not yet implemented")
                            //2021 + 20
                            //5
                            //20

                            //startDate = "${year} + ${month} + ${dayOfMonth}"

                            startDate = year.toString() + (month + 1).toString() + dayOfMonth.toString()
                            Log.d("day : ", startDate)
                        }
                    }, year, month, day)

                }
        */
        goalsettingToTimeractivity()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0) // 애니메이션 비활성화
    }
    private fun goalsettingToTimeractivity() {
        val button = findViewById<Button>(R.id.btn_goalsettingcomplete)
        button.setOnClickListener {
            val intent = Intent(this,timerActivity::class.java)
            startActivity(intent)
        }
    }
}





/*
    //콤보 박스 시간



    //Spinner 에 들어갈 데이터
    var data = listOf("- 하루 목표 시간을 선택하세요 - ","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24")

    //데이터와 스피터를 연결 시켜줄 adapter를 만들어 준다.
    //ArrayAdapter의 두 번쨰 인자는 스피너 목록에 아이템을 그려줄 레이아웃을 지정하여 줍니다.
    var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
    //activity_main에서 만들어 놓은 spinner에 adapter 연결하여 줍니다.
    spinner.adapter = adapter
    //데이터가 들어가 있는 spinner 에서 선택한 아이템을 가져옵니다.
    spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{


        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            //position은 선택한 아이템의 위치를 넘겨주는 인자입니다.
            result.text = data.get(position)
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }*/
/*
//목표추가 시간 설정 부분
    fun showTimeSettingPopup() {

        val dialog = AlertDialog.Builder(context).create()

        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_settime, null)

        val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min)
        val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec)

        val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no)
        val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok)
        // editText 설정해제
        minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        second.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        //최소값 설정
        minute.minValue = 0
        second.minValue = 0

        //최대값 설정
        minute.maxValue = 30
        second.maxValue = 59
        //기본값 설정
        minute.value = 1
        second.value = 0


        //취소버튼
        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        start.setOnClickListener {
            Toast.makeText(context, "${minute.value}분 ${second.value}초", Toast.LENGTH_SHORT).show()
            (activity as PageActivity).startExcercise(exname)
            dialog.dismiss()
        }

        dialog.setView(mView)

        dialog.create()
        dialog.show()
        dialog.window!!.setLayout(750, WindowManager.LayoutParams.WRAP_CONTENT)
    }

*/
