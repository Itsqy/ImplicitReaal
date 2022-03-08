package com.syatria.implicitintentreal

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syatria.implicitintentreal.databinding.ActivityWifiBinding

class WifiActivity : AppCompatActivity() {
    lateinit var binding: ActivityWifiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (wifiManager.isWifiEnabled) {
            binding.swchWifi.isChecked = true
            binding.status.text = "Wi-Fi is Active"
        } else {
            binding.swchWifi.isChecked = false
            binding.status.text = "Wi-Fi is InActive"
        }
        binding.swchWifi.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                wifiManager.isWifiEnabled = true
                binding.status.text = "Wi-Fi Active"

            } else {
                wifiManager.isWifiEnabled = false
                binding.status.text = "Wi-Fi InActive"
            }
        }

    }
}