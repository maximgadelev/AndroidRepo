package com.example.myapplication.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.repo.TrackListRepo
import androidx.media.app.NotificationCompat.MediaStyle as NotificationCompatMediaStyle

class NotifyControl(val context: Context) {
    private val CHANNEL_ID = "play_track"
    private val notificationId = 1

    private var notificationManager: NotificationManager? = null
    private var nBuilder: NotificationCompat.Builder
    init{
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = notificationManager?.getNotificationChannel(CHANNEL_ID) ?:  NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager?.createNotificationChannel(channel)
        }

        val previousIntent = Intent(context,MusicService::class.java).apply {
            action = "PREVIOUS"
        }
        val resumeIntent = Intent(context,MusicService::class.java).apply {
            action = "RESUME"
        }
        val nextIntent = Intent(context,MusicService::class.java).apply {
            action = "NEXT"
        }
        val stopIntent=Intent(context,MusicService::class.java).apply {
            action="STOP"
        }
        val previousPendingIntent = PendingIntent.getService(context,0,previousIntent,0)
        val resumePendingIntent = PendingIntent.getService(context,1,resumeIntent,0)
        val nextPendingIntent = PendingIntent.getService(context,2,nextIntent,0)
        val stopPendingIntent=PendingIntent.getService(context,3,stopIntent,0)
        nBuilder = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_play_arrow_24)
            .addAction(R.drawable.ic_baseline_skip_previous_24,"Previous",previousPendingIntent)
            .addAction(R.drawable.ic_baseline_pause_24,"Pause",resumePendingIntent)
            .addAction(R.drawable.ic_baseline_skip_next_24,"Next",nextPendingIntent )
            .addAction(R.drawable.ic_baseline_stop_24,"Stop",stopPendingIntent)
            .setNotificationSilent()
    }
    fun build(trackId:Int){
        val track = TrackListRepo.trackList[trackId]
        val cover = BitmapFactory.decodeResource(context.resources,track.cover)

        val builder = nBuilder
            .setContentTitle(track.title)
            .setContentText(track.author)
            .setStyle(NotificationCompatMediaStyle())
            .setLargeIcon(cover)
        val notification: Notification = builder.build()
        notificationManager?.notify(notificationId,notification)
    }
    fun cancel(){
        notificationManager?.cancelAll()
    }
}

