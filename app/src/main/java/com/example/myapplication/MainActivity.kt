package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var heroAdapter: HeroAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heroAdapter = HeroAdapter(HeroRepository.getHeroes()){
            val intent = Intent(this,HeroInfo::class.java)
            intent.putExtra("id",it.toString())
            startActivity(intent)
        }
        findViewById<RecyclerView>(R.id.rv_heroes).run {
            this.adapter=heroAdapter
        }
    }

}
