package com.hypersoft.ads.practice.gmaAds.banner.view

import android.view.ViewGroup
import com.google.android.libraries.ads.mobile.sdk.banner.AdView
import java.util.Collections
import java.util.WeakHashMap

private val destroyedViews: MutableSet<AdView> = Collections.synchronizedSet(
    Collections.newSetFromMap(WeakHashMap<AdView, Boolean>()),
)

internal fun AdView.detachFromParent() {
    (parent as? ViewGroup)?.removeView(this)
}

internal fun AdView.isDestroyed(): Boolean = this in destroyedViews

internal fun AdView.destroySafely() {
    destroyedViews.add(this)
    getBannerAd()?.let {
        it.adEventCallback = null
        it.bannerAdRefreshCallback = null
    }
    detachFromParent()
    destroy()
}
