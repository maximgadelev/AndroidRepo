package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build

private const val CHANNEL_ID = "channel_id_1"

class NotifyService(
    context: Context
) {

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val audio =
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

    fun createNotificationChanel(context: Context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.title),
                IMPORTANCE_HIGH
            ).apply {
                enableVibration(true)
                description = context.getString(R.string.info)
                lightColor = Color.BLUE
                vibrationPattern = longArrayOf(100L,200L,0L,300L)
                setSound(Uri.parse("android.resource://" + context.packageName + "/"+R.raw.guitar), audio)
            }.also {
                manager.createNotificationChannel(it)
            }
        }
    }
}
