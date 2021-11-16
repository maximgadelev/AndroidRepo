package com.example.myapplication.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.songRw.Song
import com.example.myapplication.songRw.SongAdapter
import com.example.myapplication.songRw.SongRepo
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondFragment : Fragment(R.layout.second_fragment) {
    var songAdapter: SongAdapter?=null
    var dialogBuilder: AlertDialog.Builder? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songAdapter = SongAdapter{
   SongRepo.songList.removeAt(it)
            songAdapter?.submitList(SongRepo.songList)
        }
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val dialogLayout = LayoutInflater.from(context).inflate(R.layout.song_dialog,null)
        dialogLayout.findViewById<EditText>(R.id.et_title)
        dialogBuilder = AlertDialog.Builder(context)
            .setTitle("Add new song")
            .setView(dialogLayout)
            .setCancelable(true)
            .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, i: Int ->
                val song = Song(
                    SongRepo.songList.size,
                    dialogLayout.findViewById<EditText>(R.id.et_title).text.toString(),
                    dialogLayout.findViewById<EditText>(R.id.et_info).text.toString())

                SongRepo.addSong(
                    dialogLayout.findViewById<EditText>(R.id.et_position).text.toString(),
                    song
                )
                songAdapter?.submitList(SongRepo.songList)
            }
            .setNegativeButton("Cancel"){dialogInterface:DialogInterface,i:Int->
                dialogInterface.cancel()
            }
        val dialog = dialogBuilder?.create()
        with(view){
            findViewById<RecyclerView>(R.id.rv_songs).run{
                adapter=songAdapter
                addItemDecoration(decorator)
            }
            findViewById<FloatingActionButton>(R.id.fab_refresh).setOnClickListener{
                dialog?.show()
            }
            songAdapter?.submitList(SongRepo.songList)
        }
    }
}
