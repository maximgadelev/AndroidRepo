package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemHeroBinding

class HeroHolder(
    private val binding: ItemHeroBinding,
    private val action:(Int)->Unit
):RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Hero) {
with(binding) {
    tvName.text = item.name
    tvInfo.text = item.info
    ivPhoto.setImageResource(item.photo)
}
        itemView.setOnClickListener{
            action(item.id)
        }
    }
    companion object{
        fun create(
            parent:ViewGroup,
            action: (Int) -> Unit
        )=HeroHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),action
        )
    }
}
