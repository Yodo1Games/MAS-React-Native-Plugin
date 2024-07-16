package com.yodo1_mas;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class NativeViewManager extends SimpleViewManager<NativeView> {
    public static final String REACT_CLASS = "NativeView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected NativeView createViewInstance(ThemedReactContext reactContext) {
        return new NativeView(reactContext);
    }
}

