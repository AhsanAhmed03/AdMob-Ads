package com.example.admob.implementation

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.widget.TextView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

object BannerAds {

    fun createBanner(context: Context, banner_ad_layout: ShimmerFrameLayout, activity: Activity, value: Int){

        val adView: AdView
        adView = AdView(context)
        val adRequst = AdRequest.Builder().build()

        if (banner_ad_layout.childCount > 0){
            banner_ad_layout.removeAllViews()
        }

        when(value){
            1->{
                banner_ad_layout.minimumHeight = AdSize.BANNER.height
                adView.setAdSize(getAdSize(activity))
                adView.adUnitId = AppAdsId.BANNER_AD_ID
                adView.loadAd(adRequst)
            }
            2->{
                banner_ad_layout.minimumHeight = AdSize.MEDIUM_RECTANGLE.height
                adView.setAdSize(AdSize.MEDIUM_RECTANGLE)
                adView.adUnitId = AppAdsId.BANNER_AD_ID
                adView.loadAd(adRequst)
            }
            3->{
                banner_ad_layout.minimumHeight = AdSize.LARGE_BANNER.height
                adView.setAdSize(AdSize.LARGE_BANNER)
                adView.adUnitId = AppAdsId.BANNER_AD_ID
                adView.loadAd(adRequst)
            }
        }
        adView.adListener =  object : AdListener(){
            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                banner_ad_layout.hideShimmer()
                banner_ad_layout.addView(adView)
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }

        }

    }

    private fun getAdSize(activity: Activity): AdSize {
        val display: Display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val widthPixels = outMetrics.widthPixels.toFloat()
        val density = outMetrics.density
        val adWidth = (widthPixels/density).toInt()

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }
}