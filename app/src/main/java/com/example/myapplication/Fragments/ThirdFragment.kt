package com.example.myapplication.Fragments

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.cardRw.CardAdapter
import com.example.myapplication.cardRw.CardRepo

class ThirdFragment : Fragment() {
    var adapter : CardAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= CardAdapter(CardRepo.cardList)
        view.findViewById<RecyclerView>(R.id.rv_cards).adapter=adapter
    }
}
