package com.yodo1_mas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import com.yodo1.mas.banner.Yodo1MasBannerAdSize;
import com.yodo1.mas.banner.Yodo1MasBannerAdView;

public class BannerView extends LinearLayout {

    private LinearLayout containerLayout;

    private Yodo1MasBannerAdView bannerAdView;

    private boolean isBannerLoaded;

    public BannerView(Context context) {
        super(context);
        init(context, R.layout.banner);
    }

    private void init(Context context, int layoutId) {
        LayoutInflater.from(context).inflate(layoutId, this, true);
        containerLayout = findViewById(R.id.native_layout);
        bannerAdView = findViewById(R.id.banner_ad_view);
    }

    public void loadBannerAd() {
        if(isBannerLoaded)
            return;
        isBannerLoaded = true;
        bannerAdView.loadAd();
    }

    public void adBannerAd() {
        bannerAdView = new Yodo1MasBannerAdView(this.getContext());
        bannerAdView.setAdSize(Yodo1MasBannerAdSize.Banner);
        bannerAdView.setId(R.id.banner_ad_view);
        containerLayout.addView(bannerAdView, new LayoutParams(320, 50));
    }
}

