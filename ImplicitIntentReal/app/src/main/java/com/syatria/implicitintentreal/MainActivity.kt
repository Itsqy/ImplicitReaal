package com.syatria.implicitintentreal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

//import com.syatria.implicitintentreal.CameraActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var tombolPhone = findViewById<Button>(R.id.btn_phone)
        tombolPhone.setOnClickListener {

            val i = Intent(this@MainActivity, PhoneActivity::class.java)
            startActivity(i)
        }

        var tombolSms = findViewById<Button>(R.id.btn_sms)
        tombolSms.setOnClickListener {

            val i = Intent(this@MainActivity, SmsActivity::class.java)
            startActivity(i)
        }
        var tombolCamera = findViewById<Button>(R.id.btn_camera)
        tombolCamera.setOnClickListener {

            val i = Intent(this@MainActivity, CameraActivity::class.java)
            startActivity(i)
        }

        var tombolEmail = findViewById<Button>(R.id.btn_email)
        tombolEmail.setOnClickListener {

            val i = Intent(this@MainActivity, EmailActivity::class.java)
            startActivity(i)
        }
        var tombolSpeech = findViewById<Button>(R.id.btn_speech)
        tombolSpeech.setOnClickListener {

            val i = Intent(this@MainActivity, TextToSpeechActivity::class.java)
            startActivity(i)
        }

        var tombolNotifikasi = findViewById<Button>(R.id.btn_notification)
        tombolNotifikasi.setOnClickListener {

            val idNotif = 1
            val idChannel = "Notif Kotlin"
            val channelName = "Kotlin Intermediete"
            val i = Intent(this, SmsActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, i, 0)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(this, idChannel)
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
        btn_app.setOnClickListener {
            var intent = packageManager.getLaunchIntentForPackage("com.mobile.legends")
            if (intent != null) {
                startActivity(intent)
            } else {
                val error = Intent(Intent.ACTION_VIEW)
                error.data =
                    Uri.parse("https://www.taptap.io/app/228434?hreflang=en_US")
                startActivity(error)

            }
        }

        btn_audio.setOnClickListener {
            val intent = Intent(this@MainActivity, AudioManagerActivity::class.java)
            startActivity(intent)
        }

        btn_wifi.setOnClickListener {
            val intent = Intent(this@MainActivity, WifiActivity::class.java)
            startActivity(intent)
        }

        btn_alarm.setOnClickListener {
            val intent = Intent(this@MainActivity, AlarmActivity::class.java)
            startActivity(intent)
        }
    }
}