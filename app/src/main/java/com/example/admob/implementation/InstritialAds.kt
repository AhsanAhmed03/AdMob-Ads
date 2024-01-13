package com.example.admob.implementation

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback

object InstritialAds {

    var interstitalAd: AdManagerInterstitialAd? = null

    fun loadInterstitial(context: Context){

        Log.e("InterstitalLogs","Load  interstital Ad Method...")

        val adRequest = AdManagerAdRequest.Builder().build()

        AdManagerInterstitialAd.load(context, AppAdsId.INTERSTITAL_ID, adRequest,
            object : AdManagerInterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    Log.e("InterstitalLogs","Interstital Ad Failed to Load: "+ p0.message)
                }

                override fun onAdLoaded(p0: AdManagerInterstitialAd) {
                    super.onAdLoaded(p0)
                    Log.e("InterstitalLogs","Interstital Ad Loaded...")
                    interstitalAd = p0
                }
            })
    }

    fun showInterstital(context: Context, activity: Activity){

        if (interstitalAd != null){
            val ad = interstitalAd!!
            ad.show(activity)
            ad.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdClicked() {
                    super.onAdClicked()
                    Log.e("InterstitalLogs","Interstital Ad Clicked...")
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    Log.e("InterstitalLogs","Interstital Ad Dismissed...")
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    Log.e("InterstitalLogs","Interstital Ad Failed to show: "+p0.message)
                    interstitalAd = null
                    loadInterstitial(context)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    loadInterstitial(context)
                    Log.e("InterstitalLogs","Interstital Ad Impression Counted...")
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    Log.e("InterstitalLogs","Interstital Ad Shown...")
                    interstitalAd = null
                }
            }

        }else{
            Log.e("InterstitalLogs","Interstital Ad is null")
            loadInterstitial(context)
        }
    }
}