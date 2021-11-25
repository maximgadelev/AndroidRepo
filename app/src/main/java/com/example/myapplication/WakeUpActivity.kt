package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityWakeUpBinding

class WakeUpActivity:AppCompatActivity() {
    lateinit var binding: ActivityWakeUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wake_up)

    }
}
