package com.example.myapplication.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TrackListItemBinding
import com.example.myapplication.model.Track

class TrackListHolder(
    private val binding: TrackListItemBinding,
    private val action:(Int)->Unit
):RecyclerView.ViewHolder(binding.root) {
    fun bind(item:Track){
        with(binding){
            tvTitle.text=item.title
            tvAuthor.text=item.author
            ivCover.setImageResource(item.cover)
        }
        itemView.setOnClickListener{
            action(item.id)
        }
    }
    companion object{
        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        )=TrackListHolder(
            TrackListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),action
        )
    }
}
