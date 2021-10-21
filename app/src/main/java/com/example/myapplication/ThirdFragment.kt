package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ThirdFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?= inflater.inflate(
        R.layout.third_fragment,
        container,
        false)
    companion object{
        fun newInstance():ThirdFragment{
            return ThirdFragment()
        }
    }
}
