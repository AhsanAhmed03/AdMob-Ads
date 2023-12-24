package com.example.admob.implementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)

        InstritialAds.loadInterstitial(this)

        val banner_ad_layout = findViewById<ShimmerFrameLayout>(R.id.banner_layout)

        val simple_banner_btn = findViewById<Button>(R.id.simple_banner)
        val rectangle_banner_btn = findViewById<Button>(R.id.mediumRec_banner)
        val large_banner_btn = findViewById<Button>(R.id.large_banner)
        val show_instrititial_btn = findViewById<Button>(R.id.interstital_ad_btn)

        simple_banner_btn.setOnClickListener {
            BannerAds.createBanner(this,banner_ad_layout,this@MainActivity, 1)
        }

        rectangle_banner_btn.setOnClickListener {
            BannerAds.createBanner(this,banner_ad_layout,this@MainActivity, 2)
        }

        large_banner_btn.setOnClickListener {
            BannerAds.createBanner(this,banner_ad_layout,this@MainActivity, 3)
        }

        show_instrititial_btn.setOnClickListener {
            InstritialAds.showInterstital(this, this@MainActivity)
        }
    }
}