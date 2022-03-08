package com.syatria.implicitintentreal

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_phone.*
import kotlinx.android.synthetic.main.activity_sms.*

class SmsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        btn_pilih_kontak_sms.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            i.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            startActivityForResult(i, 99)
        }

        btn_sms_intent.setOnClickListener {
            val notel = edt_nomor_telepon_sms.text.toString()
            val text = edt_pesan.text.toString()
            if (notel.isEmpty()) {
                Toast.makeText(this@SmsActivity, "isi dahulu pesannya", Toast.LENGTH_SHORT).show()
            }
            if (notel.isEmpty()) {
                Toast.makeText(this@SmsActivity, "Pesan masih kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val i = Intent(Intent.ACTION_SENDTO)
            i.data = Uri.parse("sms:$notel")
            i.putExtra("sms_body", text)
            startActivity(i)
        }

        btn_kirim_sms.setOnClickListener {
            val notel = edt_nomor_telepon_sms.text.toString()
            val text = edt_pesan.text.toString()
            if (notel.isEmpty()) {
                Toast.makeText(this@SmsActivity, "isi dahulu pesannya", Toast.LENGTH_SHORT).show()
            }
            if (notel.isEmpty()) {
                Toast.makeText(this@SmsActivity, "Pesan masih kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ContextCompat.checkSelfPermission(
                    this@SmsActivity,
                    Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val smsManager = SmsManager.getDefault()
                try {
                    smsManager.sendTextMessage(notel, null, text, null, null)
                } catch (e: Exception) {
                    Toast.makeText(this@SmsActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }


            }else {
                ActivityCompat.requestPermissions(
                    this@SmsActivity,
                    arrayOf(Manifest.permission.SEND_SMS), 200
                )
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 99) {
            if (resultCode == Activity.RESULT_OK) {
                val uri = data?.data
                val cursor = contentResolver.query(
                    uri!!,
                    arrayOf(
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    ), null, null, null
                )

                if (cursor != null && cursor.moveToNext()) {
                    val noTel = cursor.getString(0)
                    edt_no_telp.setText(noTel)
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {

           edt_nomor_telepon_sms.setText("")
            Toast.makeText(this, "Cancel Ambil KONTAK", Toast.LENGTH_SHORT).show()
        }
    }
}