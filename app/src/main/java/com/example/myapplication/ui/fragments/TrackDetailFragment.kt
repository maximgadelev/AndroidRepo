package com.example.myapplication.ui.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTrackDetailBinding
import com.example.myapplication.model.Track
import com.example.myapplication.repo.TrackListRepo
import com.example.myapplication.service.MusicService

 class TrackDetailFragment:Fragment(R.layout.fragment_track_detail) {
    var binding: FragmentTrackDetailBinding? = null;
    lateinit var track: Track
    private var musicService: MusicService? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }


    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if(musicService!=null){
                createView()
            }
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createView()
    }

    override fun onStart() {
        super.onStart()
        initService()
    }

    override fun onResume() {
        super.onResume()
        initService()
    }

    private fun initService(){
        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, binderConnection, Context.BIND_AUTO_CREATE)
    }

    fun createView() {
        val id = arguments?.getInt("id")
        id?.let {
            track = TrackListRepo.trackList[id]
            binding?.tvDetailTitle?.text = track.title
            binding?.tvDetailAuthor?.text = track.author
            binding?.ivDetailCover?.setImageResource(track.cover)
            createButtonsView(id)
        }
    }
    fun createButtonsView(id:Int){
        musicService?.setTrack(id)
        musicService?.playTrack()
        binding?.ivPlay?.setOnClickListener{
            musicService?.playTrack()
            showPauseButton()
        }
            binding?.ivPause?.setOnClickListener {
            musicService?.pauseTrack()
            showPlayButton()
        }
        binding?.ivNext?.setOnClickListener{
            musicService?.playNextTrack()
            updateView(musicService?.currentTrackId?:0)
        }
        binding?.ivPrev?.setOnClickListener{
            musicService?.playPreviousTrack()
            updateView(musicService?.currentTrackId?:0)
        }
        binding?.ivStop?.setOnClickListener{
            musicService?.stopTrack()
            showPlayButton()
            navigateToTheList()
            musicService?.cancelTrack()
        }
    }
    private fun showPauseButton(){
        binding?.ivPlay?.visibility = View.GONE
        binding?.ivPause?.visibility = View.VISIBLE
    }

    private fun showPlayButton(){
        binding?.ivPlay?.visibility = View.VISIBLE
        binding?.ivPause?.visibility = View.GONE
    }

         fun updateView(id: Int) {
             id.let {
                 track = TrackListRepo.trackList[id]
                 binding?.tvDetailTitle?.text = track.title
                 binding?.tvDetailAuthor?.text = track.author
                 binding?.ivDetailCover?.setImageResource(track.cover)
                 showPauseButton()
                 binding?.ivPlay?.setOnClickListener {
                     musicService?.playTrack()
                     showPauseButton()
                 }
             }
         }
     public fun navigateToTheList(){
         parentFragmentManager.beginTransaction()?.addToBackStack(null)?.replace(R.id.fragment_container,TrackListFragment())?.commit()
     }
}
