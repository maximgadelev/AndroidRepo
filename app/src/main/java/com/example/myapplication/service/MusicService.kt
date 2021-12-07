package com.example.myapplication.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.fragment.app.Fragment
import com.example.myapplication.model.Track
import com.example.myapplication.repo.TrackListRepo
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.fragments.TrackDetailFragment
import com.example.myapplication.ui.fragments.TrackListFragment

class MusicService: Service() {
    private lateinit var mPlayer: MediaPlayer
    var currentTrackId: Int? = null
    lateinit var trackList: ArrayList<Track>
    private lateinit var musicBinder: MusicBinder;
    private lateinit var notificationController:NotifyControl
    override fun onBind(intent: Intent?): IBinder =musicBinder

    inner class MusicBinder : Binder() {

        fun getService(): MusicService = this@MusicService

    }

    override fun onCreate() {
        super.onCreate()
        createField()
        initNotificationBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "RESUME" -> {
                if (mPlayer.isPlaying) pauseTrack() else playTrack()

            }
            "PREVIOUS" -> {
                playPreviousTrack()
            }
            "NEXT" -> {
                playNextTrack()
            }
            "STOP"->{
                stopTrack()
                cancelTrack()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun initNotificationBar(){
        notificationController = NotifyControl(this).apply {
            build(2)
        }
    }

    fun createField(){
        currentTrackId = 0
        mPlayer = MediaPlayer()
        musicBinder = MusicBinder()
        trackList = TrackListRepo.trackList
    }

    fun playTrack() {
        mPlayer.start()
    }

    fun pauseTrack() {
        mPlayer.pause()
    }
    fun stopTrack(){
        mPlayer.stop();
    }
    fun setTrack(id: Int) {
        if (mPlayer.isPlaying) mPlayer.stop()
        mPlayer = MediaPlayer.create(applicationContext, trackList[id].sound)
        currentTrackId = id
        mPlayer.run {
            setOnCompletionListener {
                stop()
            }
        }
        notificationController.build(id)
    }
    fun playPreviousTrack() {
        currentTrackId?.let {
            currentTrackId = if (it == 0) {
                trackList.size - 1
            } else {
                it - 1
            }
            setTrack(currentTrackId ?: 0)
            playTrack()
        }
    }

    fun playNextTrack() {
        currentTrackId?.let {
            currentTrackId = if (it == trackList.size - 1) {
                0
            } else {
                it + 1
            }
            setTrack(currentTrackId ?: 0)
            playTrack()
        }
    }
    fun cancelTrack(){
        notificationController.cancel()
    }

}
