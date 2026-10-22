package com.hypersoft.ads.practice.gmaAds.banner.view

import com.google.android.libraries.ads.mobile.sdk.banner.AdView

interface BannerContainerView {

    fun setAdView(adView: AdView)

    fun clearView()
}