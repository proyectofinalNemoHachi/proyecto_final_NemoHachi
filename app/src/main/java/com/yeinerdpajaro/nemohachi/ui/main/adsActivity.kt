package com.yeinerdpajaro.nemohachi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yeinerdpajaro.nemohachi.databinding.ActivityAdsBinding
import com.yeinerdpajaro.nemohachi.databinding.ActivityMainBinding

class adsActivity : AppCompatActivity() {
    private lateinit var adsBinding: ActivityAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adsBinding = ActivityAdsBinding.inflate(layoutInflater)

        val view = adsBinding.root
        setContentView(view)
    }
}