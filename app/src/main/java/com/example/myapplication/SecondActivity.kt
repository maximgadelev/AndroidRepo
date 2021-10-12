package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent = this.intent;
        if (intent.getStringExtra(Intent.EXTRA_TEXT) != null) {
            Toast.makeText(this, intent.getStringExtra(Intent.EXTRA_TEXT), Toast.LENGTH_SHORT).show()
            var text = intent.getStringExtra(Intent.EXTRA_TEXT)
            binding.tvText.text=text.toString()
        }
    }
}

