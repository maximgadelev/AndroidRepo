package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.HeroInfoBinding
import com.google.android.material.snackbar.Snackbar

class HeroInfo :AppCompatActivity(){
    lateinit var binding:HeroInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= HeroInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val list = HeroRepository.getHeroes()
        val id = intent.getStringExtra("id")?.toInt()
        binding.tvHeroName.text=list[id!!].name
        binding.tvHeroInfo.text=list[id].info
        binding.ivHeroPhoto.setImageResource(list[id!!].photo)

    }

}
