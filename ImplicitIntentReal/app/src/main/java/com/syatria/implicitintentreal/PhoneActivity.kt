package com.syatria.implicitintentreal

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_phone.*

class PhoneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        btn_pilih_kontak.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            i.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            startActivityForResult(i, 99)
        }

        btn_dial_phone.setOnClickListener {
            val notel = edt_no_telp.text.toString()
            if (notel.isEmpty()) {
                edt_no_telp.error = "Nomor masih kosong"
                return@setOnClickListener
            }

            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:$notel")
            startActivity(i)

        }

        btn_panggil.setOnClickListener {
            val notel = edt_no_telp.text.toString()
            if (notel.isEmpty()) {
                edt_no_telp.error = "Isi nomor terlebih dahulu "
                return@setOnClickListener
            }

            if (ContextCompat.checkSelfPermission(
                    this@PhoneActivity,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val i = Intent(Intent.ACTION_CALL)
                i.data = Uri.parse("tel:$notel")
                startActivity(i)

            } else {
                ActivityCompat.requestPermissions(
                    this@PhoneActivity,
                    arrayOf(Manifest.permission.CALL_PHONE), 200
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

            edt_no_telp.setText("")
            Toast.makeText(this, "Cancel Ambil KONTAK", Toast.LENGTH_SHORT).show()
        }
    }
}