package com.hypersoft.ads.practice.gmaAds.banner.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.libraries.ads.mobile.sdk.banner.AdView
import com.hypersoft.ads.practice.gmaAds.databinding.ViewBannerBinding

class BannerAdView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr), BannerContainerView {

    private val binding = ViewBannerBinding.inflate(LayoutInflater.from(context), this)

    override fun setAdView(adView: AdView) {
        if (adView.parent === this) {
            binding.mtvLoadingAds.visibility = GONE
            visibility = VISIBLE
            adView.visibility = VISIBLE
            return
        }
        adView.detachFromParent()
        detachChildAdViews()
        binding.mtvLoadingAds.visibility = GONE
        val adSize = adView.getBannerAd()?.getAdSize()
        val widthPx = adSize?.getWidthInPixels(context) ?: 0
        val heightPx = adSize?.getHeightInPixels(context) ?: 0
        if (widthPx > 0) minimumWidth = widthPx
        if (heightPx > 0) minimumHeight = heightPx
        adView.visibility = VISIBLE
        addView(
            adView,
            LayoutParams(
                if (widthPx > 0) LayoutParams.WRAP_CONTENT else LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
            ).apply { gravity = Gravity.CENTER_HORIZONTAL },
        )
        visibility = VISIBLE
        requestLayout()
    }

    override fun clearView() {
        detachChildAdViews()
        binding.mtvLoadingAds.visibility = VISIBLE
    }

    private fun detachChildAdViews() {
        for (index in childCount - 1 downTo 0) {
            val child = getChildAt(index)
            if (child is AdView) {
                (child.parent as? ViewGroup)?.removeView(child)
            }
        }
    }
}