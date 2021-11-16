package com.example.myapplication.songRw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemSongBinding

class SongHolder(
    private val binding:ItemSongBinding,
    private val onDeleteItem:(Int)->Unit
):RecyclerView.ViewHolder(binding.root) {
    val title:TextView=binding.tvTitle
    val info:TextView=binding.tvAbout
    val deleteView:ImageView=binding.ivDelete
   fun bind(song: Song) {
       with(binding) {
           title.text = song.title
           info.text = song.info
           deleteView.setOnClickListener {
               onDeleteItem(adapterPosition)
           }
       }
   }
    fun updateFields(bundle: Bundle){
        bundle.run {
            getString("ARG_TITLE")?.also {
                updateTitle(it)
            }
            getString("ARG_INFO")?.also {
                updateInfo(it)
            }
        }
    }
    fun updateTitle(title:String){
       binding.tvTitle.text=title
    }
    fun updateInfo(info:String){
        binding.tvAbout.text=info
    }
    companion object{
        fun create(
            parent: ViewGroup,
                   onDeleteItem: (Int) -> Unit) =
            SongHolder(
            ItemSongBinding.inflate(LayoutInflater.from(parent.context),parent,false),
            onDeleteItem
        )
    }
}
