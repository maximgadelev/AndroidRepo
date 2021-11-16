package com.example.myapplication.cardRw

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCardBinding

class CardHolder(private val binding: ItemCardBinding)
    :RecyclerView.ViewHolder(binding.root) {
    val title: TextView = binding.tvCardTitle
    val info: TextView = binding.tvCardInfo
    var imageAdapter:ImageAdapter?=null

    fun bind(card: Card) {
        title.text=card.title
        info.text=card.info
        imageAdapter= ImageAdapter(card.imageList)
        binding.vpImage.adapter=imageAdapter
    }
    companion object{
        fun create(
            parent: ViewGroup
        ) = CardHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
        ))
    }
}
