package com.example.myapplication


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.RawRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class Receiver:BroadcastReceiver(){
    private var service: NotifyService? = null

    override fun onReceive(context: Context?, p1: Intent?) {

                val i = Intent(context, WakeUpActivity::class.java)
                p1!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent= PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_ONE_SHOT)
                val builder = NotificationCompat.Builder(context!!, "channel_id_1")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setShowWhen(false)
                    .setAutoCancel(true)
                    .setContentTitle("Пора вставать")
                    .setContentIntent(pendingIntent)
                    .setContentText("Прозвенел будильник")
                    .setVibrate(longArrayOf(100L,200L,0L,300L))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                val manager=NotificationManagerCompat.from(context)
                manager.notify(1, builder.build())
            }
        }

private fun Context.getSoundUri(
    @RawRes id: Int
) = Uri.parse("android.resource://${packageName}/$id")


