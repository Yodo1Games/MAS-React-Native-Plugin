package com.yodo1_mas;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.yodo1.mas.nativeads.Yodo1MasNativeAdView;

public class NativeView extends LinearLayout {

    private Yodo1MasNativeAdView nativeAdView;
    private LinearLayout containerLayout;

    public NativeView(Context context) {
        super(context);
        init(context, R.layout.nativead);
    }

    private void init(Context context, int layoutId) {
        LayoutInflater.from(context).inflate(layoutId, this, true);
        containerLayout = findViewById(R.id.native_layout);
        nativeAdView = findViewById(R.id.native_ad_view);
    }

    public void loadNativeAd() {
        nativeAdView.loadAd();
    }

    public void adNativeAd() {
        nativeAdView.setId(R.id.native_ad_view);
        containerLayout.addView(nativeAdView, new LayoutParams(LayoutParams.MATCH_PARENT, 300));
    }
}

