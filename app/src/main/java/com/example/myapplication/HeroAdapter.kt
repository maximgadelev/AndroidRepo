package com.example.myapplication

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter(
    private val list:List<Hero>,
    private val action: (Int)->Unit
):RecyclerView.Adapter<HeroHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder= HeroHolder.create(parent,action)

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount():Int=list.size
}
