package com.example.myapplication.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Track

class TrackListAdapter(
    private val list:List<Track>,
    private val action: (Int)->Unit
): RecyclerView.Adapter<TrackListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListHolder= TrackListHolder.create(parent,action)

    override fun onBindViewHolder(holder: TrackListHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount():Int=list.size
}

