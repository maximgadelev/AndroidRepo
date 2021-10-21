package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = arrayOf(binding.ivGallery, binding.ivPhoto, binding.ivHome, binding.ivMusic)

        binding.ivMusic.setOnClickListener {
            replaceFragment(FirstFragment.newInstance())
            changePrevButtonsColor(list,binding.ivMusic)
        }
        binding.ivHome.setOnClickListener {
            replaceFragment(SecondFragment.newInstance())
            changePrevButtonsColor(list,binding.ivHome)
        }
        binding.ivPhoto.setOnClickListener {
            replaceFragment((ThirdFragment.newInstance()))
            changePrevButtonsColor(list,binding.ivPhoto)
        }
        binding.ivGallery.setOnClickListener {
            replaceFragment(FourthFragment.newInstance())
            changePrevButtonsColor(list,binding.ivGallery)

        }
    }


    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
            .replace(R.id.layout_fl, fragment).addToBackStack("tag").commit()
    }

    fun changeColorOnButton(view: ImageView, color: Int) {
        view.setColorFilter(ContextCompat.getColor(this, color))
    }

    fun changePrevButtonsColor(
        list:Array
        <AppCompatImageView>, id: AppCompatImageView
    ) {
        for(i in list){
            if(i==id){
                changeColorOnButton(i,R.color.black)
            }else{
                changeColorOnButton(i,R.color.buttonColor)
            }

        }


    }
}

