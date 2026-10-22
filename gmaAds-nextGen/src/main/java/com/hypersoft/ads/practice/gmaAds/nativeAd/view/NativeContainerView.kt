package com.hypersoft.ads.practice.gmaAds.nativeAd.view

import com.google.android.libraries.ads.mobile.sdk.nativead.NativeAd

interface NativeContainerView {

    fun setNativeAd(nativeAd: NativeAd)

    fun clearView()
}
