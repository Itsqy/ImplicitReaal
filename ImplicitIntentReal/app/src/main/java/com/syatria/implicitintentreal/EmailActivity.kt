package com.syatria.implicitintentreal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syatria.implicitintentreal.databinding.ActivityEmailBinding

class EmailActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnKirimEmail.setOnClickListener {

            val emailTujuan = binding.edtEmailTujuan.text.toString()
            val subject = binding.edtSubject.text.toString()
            val body = binding.edtBodyEmail.text.toString()

            if (emailTujuan.isEmpty()) {
                binding.edtEmailTujuan.error = "email masih kosong "
                return@setOnClickListener
            }
            if (subject.isEmpty()) {
                binding.edtSubject.error = "subject masih kosong "
                return@setOnClickListener
            }
            if (body.isEmpty()) {
                binding.edtBodyEmail.error = "Isi masih kosong "
                return@setOnClickListener
            }

            val i = Intent(Intent.ACTION_SENDTO)
            i.data = Uri.parse("mailto:")
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailTujuan))
            i.putExtra(Intent.EXTRA_SUBJECT, subject)
            i.putExtra(Intent.EXTRA_TEXT, body)
            startActivity(i)
        }


    }


}
