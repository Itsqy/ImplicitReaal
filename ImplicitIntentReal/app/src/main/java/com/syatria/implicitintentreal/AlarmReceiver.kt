package com.syatria.implicitintentreal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.angin)

        mediaPlayer.start()

        val teks = intent?.getStringExtra("TEKS")

        showNotificationAlarm(context, teks)

    }

    private fun showNotificationAlarm(context: Context?, teks: String?) {
        val idNotif = 2
        val idChannel = "Alarm ajahh"
        val channelName = "Ada meeting gercepp"
        val i = Intent(context, AlarmActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, idChannel)
            .setSmallIcon(R.drawable.caca)
            .setContentTitle("belajar Ingat ok ")
            .setSubText("bnelajar")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                idChannel, channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }

        val notifikasi = builder.build()
        notificationManager.notify(idNotif, notifikasi)
    }


}