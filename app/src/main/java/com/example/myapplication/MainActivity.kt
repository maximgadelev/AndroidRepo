package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bStartIntent.setOnClickListener {
            startActivityForResult(
                Intent().apply {
                    action = Intent.ACTION_PICK
                    type = "image/*"
                },
                123
            )
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)

            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            123 -> {
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "User get photo", Toast.LENGTH_SHORT).show()
                    var imageUri = data?.data
                    binding.ivPhoto.setImageURI(imageUri)
                }
            }
        }
    }
}
