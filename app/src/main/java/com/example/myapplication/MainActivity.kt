package com.example.myapplication

import android.app.AlarmManager

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var picker:MaterialTimePicker
    lateinit var pendingIntent: PendingIntent
    private var service: NotifyService? = null
    lateinit var alarmManager: AlarmManager
    private var calendar: Calendar? =null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        service= NotifyService(this)
        service?.createNotificationChanel(this);
        binding.bSelectTime.setOnClickListener{
            showTimePicker()

        }
        binding.bStartAlarm.setOnClickListener{
            setAlarm()
        }
        binding.bCancelAlarm.setOnClickListener{
            closeAlarm()
        }
    }

    private  fun closeAlarm() {
        alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        if(calendar!=null) {
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this, "Будильник успешно отменен", Toast.LENGTH_SHORT).show()
            calendar=null
            binding.tvTime.text="00 : 00"
        }else{
            Toast.makeText(this, "Нет времени", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAlarm() {
        alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        val intent= Intent(this,Receiver::class.java)
        pendingIntent= PendingIntent.getBroadcast(this,0, intent,PendingIntent.FLAG_UPDATE_CURRENT)
        if(calendar!=null) {
            alarmManager.setExact(
                 AlarmManager.RTC_WAKEUP,calendar!!.timeInMillis, pendingIntent
            )
            Toast.makeText(this, "Будильник установлен", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Нет времени", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showTimePicker() {
        picker=MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(0)
            .setMinute(0)
            .setTitleText("Установить время")
            .build()
        picker.show(supportFragmentManager,"tag")
        picker.addOnPositiveButtonClickListener{
             calendar=Calendar.getInstance().also {
                it[Calendar.HOUR_OF_DAY] = picker.hour
                 it[Calendar.MINUTE] = picker.minute
                 it[Calendar.SECOND] = 0
                it[Calendar.MILLISECOND] = 0
             }
            binding.tvTime.text=
                String.format("%02d",picker.hour)+ " : " +String.format(
                    "%02d",
                    picker.minute
                )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service=null
    }
}
