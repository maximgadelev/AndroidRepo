package com.example.myapplication.songRw

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.songRw.Song

class SongDiffCallBack: DiffUtil.ItemCallback<Song>() {

    override fun areItemsTheSame(
        oldItem: Song,
        newItem: Song
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Song,
        newItem: Song
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Song, newItem: Song): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString("ARG_TITLE", newItem.title)
        }
        if (oldItem.info != newItem.info) {
            bundle.putString("ARG_INFO", newItem.info)
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}
