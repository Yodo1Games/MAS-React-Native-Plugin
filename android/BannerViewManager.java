package com.yodo1_mas;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class BannerViewManager extends SimpleViewManager<BannerView> {
    public static final String REACT_CLASS = "BannerView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected BannerView createViewInstance(ThemedReactContext reactContext) {
        return new BannerView(reactContext);
    }
}

