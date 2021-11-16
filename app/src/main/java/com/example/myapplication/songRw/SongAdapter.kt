package com.example.myapplication.songRw

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter


class SongAdapter
    (
    private val onDeleteItem:(Int)->Unit
):
    ListAdapter<Song, SongHolder>(SongDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder.create(parent, onDeleteItem)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(getItem(position))
    }
    override fun onBindViewHolder(
        holder: SongHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<Song>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }

}
