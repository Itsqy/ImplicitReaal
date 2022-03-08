package com.syatria.implicitintentreal

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syatria.implicitintentreal.databinding.ActivityAlarmBinding
import java.util.*

class AlarmActivity : AppCompatActivity() {
    lateinit var binding: ActivityAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTimePicker.setOnClickListener {
            setTimeAlarm()
        }
    }

    private fun setTimeAlarm() {

        val calender = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this@AlarmActivity,
            TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val i = Intent(this@AlarmActivity, AlarmReceiver::class.java)
                i.putExtra("TEKS", "bangun kangggg !!!!!")
                val pendingIntent = PendingIntent.getBroadcast(this, 1, i, 0)

                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                cal.set(Calendar.SECOND, 0)

                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pendingIntent)

                binding.tvSetAlarm.text = "alaram ddiatur untuk jam ${cal.time}"
            },
            calender.get(Calendar.HOUR_OF_DAY),
            calender.get(Calendar.MINUTE),
            true
        )

        timePickerDialog.show()
    }
}