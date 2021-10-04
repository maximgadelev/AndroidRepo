package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.b_settings)
        var name = findViewById<TextView>(R.id.tv_account_name)
        var edit_name = findViewById<EditText>(R.id.et_account_name)
        button.setOnClickListener {
            edit_name.visibility = View.VISIBLE
            name.visibility = View.INVISIBLE;
            edit_name.isFocusableInTouchMode = true
        }
    }
}

