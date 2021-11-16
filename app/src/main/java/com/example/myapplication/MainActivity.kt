package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Fragments.FirstFragment
import com.example.myapplication.Fragments.SecondFragment
import com.example.myapplication.Fragments.ThirdFragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.action_home->{
                    onClickHome()
                    true
                }
                R.id.action_list->{
                    onClickList()
                    true

                }
                R.id.action_info->{
                    onClickInfo()
                    true
                }
                else-> false
            }
        }
    }
    fun onClickHome(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container, FirstFragment()).commit()
    }
    fun onClickList(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container, SecondFragment()).commit()
    }
    fun onClickInfo(){
        supportFragmentManager.beginTransaction().replace(R.id.main_container, ThirdFragment()).commit()
    }
}
