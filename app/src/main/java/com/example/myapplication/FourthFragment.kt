package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FourthFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?= inflater.inflate(
        R.layout.fourth_fragment,
        container,
        false)
    companion object{
        fun newInstance():FourthFragment{
            return FourthFragment()
        }
    }
}
