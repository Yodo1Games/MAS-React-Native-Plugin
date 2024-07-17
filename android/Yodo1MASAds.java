package com.yodo1_mas;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;


import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.views.view.ReactViewGroup;
import com.yodo1.mas.Yodo1Mas;
import com.yodo1.mas.appopenad.Yodo1MasAppOpenAd;
import com.yodo1.mas.appopenad.Yodo1MasAppOpenAdListener;
import com.yodo1.mas.banner.Yodo1MasBannerAdListener;
import com.yodo1.mas.banner.Yodo1MasBannerAdSize;
import com.yodo1.mas.banner.Yodo1MasBannerAdView;
import com.yodo1.mas.error.Yodo1MasError;
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig;
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAd;
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAdListener;
import com.yodo1.mas.nativeads.Yodo1MasNativeAdListener;
import com.yodo1.mas.nativeads.Yodo1MasNativeAdView;
import com.yodo1.mas.reward.Yodo1MasRewardAd;
import com.yodo1.mas.reward.Yodo1MasRewardAdListener;
import com.yodo1.mas.rewardedinterstitial.Yodo1MasRewardedInterstitialAd;
import com.yodo1.mas.rewardedinterstitial.Yodo1MasRewardedInterstitialAdListener;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.bridge.Callback;


public class Yodo1MASAds extends ReactContextBaseJavaModule {
   ReactApplicationContext context = null;

   boolean initialized = false;
   Yodo1MasAppOpenAd appOpenAd = Yodo1MasAppOpenAd.getInstance();

   Yodo1MasRewardAd rewardAd = Yodo1MasRewardAd.getInstance();

   Yodo1MasInterstitialAd interstitialAd = Yodo1MasInterstitialAd.getInstance();

    Yodo1MasRewardedInterstitialAd rewardedInterstitialAd = Yodo1MasRewardedInterstitialAd.getInstance();
    private Yodo1MasBannerAdView bannerAdView;

    private Yodo1MasNativeAdView nativeAdView;

    private boolean isBannerLoaded;

   Yodo1MASAds(ReactApplicationContext context) {
       super(context);
       this.context = context;
   }

    @ReactMethod
    public void addListener(String eventName) {

    }


    @ReactMethod
    public void removeListeners(Integer count) {

    }


   @ReactMethod
   public void initMasSdk(boolean ccpa, boolean coppa, boolean gdpr) {
       Yodo1MasAdBuildConfig config = new Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build();
       Yodo1Mas.getInstance().setAdBuildConfig(config);
       Yodo1Mas.getInstance().setCCPA(ccpa);
       Yodo1Mas.getInstance().setCOPPA(coppa);
       Yodo1Mas.getInstance().setGDPR(gdpr);
       //Call Delegate Function before Init Sdk:
       Yodo1Mas.getInstance().initMas(Yodo1MASAds.this.getCurrentActivity(), "YourAppKey", new Yodo1Mas.InitListener() {
           @Override
           public void onMasInitSuccessful() {
               setInitialized(true);
               sendEvent("onMasInitSuccessful");
               LoadAppOpenAds();
               LoadInterstitialAds();
               LoadRewardedAds();
               LoadRewardInterstitialAds();
           }
           @Override
           public void onMasInitFailed(@NonNull Yodo1MasError error) {
               sendEvent("onMasInitFailed");
           }
       });
   }
    @ReactMethod
    public void setCCPA(Boolean ccpa) {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Yodo1Mas.getInstance().setCCPA(ccpa);
            }
        });
    }

    @ReactMethod
    public void setCOPPA(Boolean coppa) {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Yodo1Mas.getInstance().setCOPPA(coppa);
            }
        });
    }

    @ReactMethod
    public void setGDPR(Boolean gdpr) {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Yodo1Mas.getInstance().setGDPR(gdpr);
            }
        });
    }


   @ReactMethod
   public void LoadAppOpenAds()
   {
       Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {


           @Override
           public void run() {
               appOpenAd.loadAd(Yodo1MASAds.this.getCurrentActivity());

               appOpenAd.setAdListener(new Yodo1MasAppOpenAdListener() {
           @Override
           public void onAppOpenAdLoaded(Yodo1MasAppOpenAd ad) {
               // Code to be executed when an ad finishes loading.
               sendEvent("onAppOpenAdLoaded");
           }


           @Override
           public void onAppOpenAdFailedToLoad(Yodo1MasAppOpenAd ad, @NonNull Yodo1MasError error) {
               // Code to be executed when an ad request fails.
               sendEvent("onAppOpenAdFailedToLoad");
           }


           @Override
           public void onAppOpenAdOpened(Yodo1MasAppOpenAd ad) {
               // Code to be executed when an ad opens an overlay that
               // covers the screen.
               sendEvent("onAppOpenAdOpened");
           }


           @Override
           public void onAppOpenAdFailedToOpen(Yodo1MasAppOpenAd ad, @NonNull Yodo1MasError error) {
               // Code to be executed when an ad open fails.
               sendEvent("onAppOpenAdFailedToOpen");
           }


           @Override
           public void onAppOpenAdClosed(Yodo1MasAppOpenAd ad) {
               // Code to be executed when the user is about to return
               // to the app after tapping on an ad.
               sendEvent("onAppOpenAdClosed");
               ad.loadAd(Yodo1MASAds.this.getCurrentActivity());
           }
       });
   }
   });
   }

   @ReactMethod
   public void showAppOpenAds()
   {
       Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

           @Override
           public void run() {
               appOpenAd.showAd(Yodo1MASAds.this.getCurrentActivity());
           }
       });

   }

   @ReactMethod
   public void showAppOpenAdsWithPlacementId(String placementId)
   {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                appOpenAd.showAd(Yodo1MASAds.this.getCurrentActivity(), placementId);
            }
        });

    }

   @ReactMethod
   public void LoadInterstitialAds() {
       Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

           @Override
           public void run() {
               interstitialAd.loadAd(Yodo1MASAds.this.getCurrentActivity());

               interstitialAd.setAdListener(new Yodo1MasInterstitialAdListener() {
                   @Override
                   public void onInterstitialAdLoaded(Yodo1MasInterstitialAd ad) {
                       // Code to be executed when an ad finishes loading.
                       sendEvent("onInterstitialAdLoaded");
                   }


                   @Override
                   public void onInterstitialAdFailedToLoad(Yodo1MasInterstitialAd ad, @NonNull Yodo1MasError error) {
                       // Code to be executed when an ad request fails.
                       sendEvent("onInterstitialAdFailedToLoad");
                   }


                   @Override
                   public void onInterstitialAdOpened(Yodo1MasInterstitialAd ad) {
                       // Code to be executed when an ad opens an overlay that
                       // covers the screen.
                       sendEvent("onInterstitialAdOpened");
                   }


                   @Override
                   public void onInterstitialAdFailedToOpen(Yodo1MasInterstitialAd ad, @NonNull Yodo1MasError error) {
                       // Code to be executed when an ad open fails.
                       sendEvent("onInterstitialAdFailedToOpen");
                   }


                   @Override
                   public void onInterstitialAdClosed(Yodo1MasInterstitialAd ad) {
                       // Code to be executed when the user is about to return
                       // to the app after tapping on an ad.
                       sendEvent("onInterstitialAdClosed");
                       ad.loadAd(Yodo1MASAds.this.getCurrentActivity());
                   }
               });
           }
       });
   }

   @ReactMethod
   public void showInterstitialAds() {

        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                interstitialAd.showAd(Yodo1MASAds.this.getCurrentActivity());
            }
        });
    }

   @ReactMethod
   public void showInterstitialAdsWithPlacementId(String placementId) {

        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                interstitialAd.showAd(Yodo1MASAds.this.getCurrentActivity(), placementId);
            }
        });
    }

   @ReactMethod
   public void LoadRewardedAds() {
       Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

           @Override
           public void run() {
               rewardAd.loadAd(Yodo1MASAds.this.getCurrentActivity());

               rewardAd.setAdListener(new Yodo1MasRewardAdListener() {
                   @Override
                   public void onRewardAdLoaded(Yodo1MasRewardAd ad) {
                       // Code to be executed when an ad finishes loading.
                       sendEvent("onRewardAdLoaded");
                   }


                   @Override
                   public void onRewardAdFailedToLoad(Yodo1MasRewardAd ad, @NonNull Yodo1MasError error) {
                       // Code to be executed when an ad request fails.
                       sendEvent("onRewardAdFailedToLoad");
                   }


                   @Override
                   public void onRewardAdOpened(Yodo1MasRewardAd ad) {
                       // Code to be executed when an ad opens an overlay that
                       // covers the screen.
                       sendEvent("onRewardAdOpened");
                   }


                   @Override
                   public void onRewardAdFailedToOpen(Yodo1MasRewardAd ad, @NonNull Yodo1MasError error) {
                       // Code to be executed when an ad open fails.
                       sendEvent("onRewardAdFailedToOpen");
                   }


                   @Override
                   public void onRewardAdClosed(Yodo1MasRewardAd ad) {
                       // Code to be executed when the user is about to return
                       // to the app after tapping on an ad.
                       sendEvent("onRewardAdClosed");
                       ad.loadAd(Yodo1MASAds.this.getCurrentActivity());
                   }

                   @Override
                   public void onRewardAdEarned(Yodo1MasRewardAd ad) {
                       // Code to be executed when the user is about to return
                       // to the app after tapping on an ad.
                       sendEvent("onRewardAdEarned");
                   }
               });
           }
       });
   }

   @ReactMethod
   public void showRewardedAds() {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                rewardAd.showAd(Yodo1MASAds.this.getCurrentActivity());
            }
        });
    }

   @ReactMethod
   public void showRewardedAdsWithPlacementId(String placementId) {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                rewardAd.showAd(Yodo1MASAds.this.getCurrentActivity(), placementId);
            }
        });
    }

   @ReactMethod
   public void showBannerAds(final int viewId) {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
                BannerView view = (BannerView) uiManager.resolveView(viewId);
                if (view != null) {
                    bannerAdView = view.findViewById(R.id.banner_ad_view);
                    bannerAdView.setAdListener(new Yodo1MasBannerAdListener() {
                        @Override
                        public void onBannerAdLoaded(Yodo1MasBannerAdView ad) {
                            // Code to be executed when an ad finishes loading.
                            isBannerLoaded = true;
                            sendEvent("onBannerAdLoaded");
                        }


                        @Override
                        public void onBannerAdFailedToLoad(Yodo1MasBannerAdView ad, @NonNull Yodo1MasError error) {
                            // Code to be executed when an ad request fails.
                            isBannerLoaded = false;
                            sendEvent("onBannerAdFailedToLoad");
                            ad.loadAd();
                        }


                        @Override
                        public void onBannerAdOpened(Yodo1MasBannerAdView ad) {
                            // Code to be executed when an ad opens an overlay that
                            // covers the screen.
                            sendEvent("onBannerAdOpened");
                        }


                        @Override
                        public void onBannerAdFailedToOpen(Yodo1MasBannerAdView ad, @NonNull Yodo1MasError error) {
                            // Code to be executed when an ad open fails.
                            sendEvent("onBannerAdFailedToOpen");
                        }


                        @Override
                        public void onBannerAdClosed(Yodo1MasBannerAdView ad) {
                            // Code to be executed when the user is about to return
                            // to the app after tapping on an ad.
                            isBannerLoaded = false;
                            sendEvent("onBannerAdClosed");
                            ad.loadAd();
                        }
                    });
                    if(isBannerLoaded) {
                        sendEvent("onBannerAdOpened");
                        return;
                    }
                    view.loadBannerAd();

                } else {
                    Log.e("CustomView", "CustomView with viewId " + viewId + " not found");
                }
            }

        });
    }

   @ReactMethod
   public void closeBannerAd() {
       Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

           @Override
           public void run() {
               sendEvent("bannerAdUnloaded");
           }
       });
    }


   @ReactMethod
   public void showNativeAds(final int viewId) {
    Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

        @Override
        public void run() {

            UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
            NativeView view = (NativeView) uiManager.resolveView(viewId);
            if (view != null) {
                nativeAdView = view.findViewById(R.id.native_ad_view);
                nativeAdView.setAdListener(new Yodo1MasNativeAdListener() {
                    @Override
                    public void onNativeAdLoaded(Yodo1MasNativeAdView view) {
                        // Code to be executed when an ad finishes loading.
                        sendEvent("onNativeAdLoaded");
                    }

                    @Override
                    public void onNativeAdFailedToLoad(Yodo1MasNativeAdView view, Yodo1MasError error) {
                        // Code to be executed when an ad request fails.
                        sendEvent("onNativeAdFailedToLoad");
                        view.loadAd();
                    }
                });
                if(nativeAdView.isLoaded()) {
                    sendEvent("onNativeAdLoaded");
                    return;
                }
                view.loadNativeAd();

            } else {
                Log.e("CustomView", "CustomView with viewId " + viewId + " not found");
            }
        }

    });
}

   @ReactMethod
   public void closeNativeAd() {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                sendEvent("nativeAdUnloaded");
            }
        });
    }

   @ReactMethod
   public void LoadRewardInterstitialAds() {
    Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

        @Override
        public void run() {
            rewardedInterstitialAd.loadAd(Yodo1MASAds.this.getCurrentActivity());

            rewardedInterstitialAd.setAdListener(new Yodo1MasRewardedInterstitialAdListener() {
                @Override
                public void onRewardedInterstitialAdLoaded(Yodo1MasRewardedInterstitialAd ad) {
                    // Code to be executed when an ad finishes loading.
                    sendEvent("onRewardedInterstitialAdLoaded");

                }


                @Override
                public void onRewardedInterstitialAdFailedToLoad(Yodo1MasRewardedInterstitialAd ad, @NonNull Yodo1MasError error) {
                    // Code to be executed when an ad request fails.

                    sendEvent("onRewardedInterstitialAdFailedToLoad");
                }


                @Override
                public void onRewardedInterstitialAdOpened(Yodo1MasRewardedInterstitialAd ad) {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                    sendEvent("onRewardedInterstitialAdOpened");
                }


                @Override
                public void onRewardedInterstitialAdFailedToOpen(Yodo1MasRewardedInterstitialAd ad, @NonNull Yodo1MasError error) {
                    // Code to be executed when an ad open fails.
                    sendEvent("onRewardedInterstitialAdFailedToOpen");
                }


                @Override
                public void onRewardedInterstitialAdClosed(Yodo1MasRewardedInterstitialAd ad) {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                    sendEvent("onRewardedInterstitialAdClosed");
                    ad.loadAd(Yodo1MASAds.this.getCurrentActivity());
                }
                @Override
                public void onRewardedInterstitialAdEarned(Yodo1MasRewardedInterstitialAd ad) {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                    sendEvent("onRewardedInterstitialAdEarned");
                }
            });
        }
    });
}
   @ReactMethod
   public void showRewardInterstitialAds() {
       Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

           @Override
           public void run() {
               rewardedInterstitialAd.showAd(Yodo1MASAds.this.getCurrentActivity());
           }
       });
   }

   @ReactMethod
   public void showRewardInterstitialAdsWithPlacementId(String placementId) {
        Yodo1MASAds.this.getCurrentActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                rewardedInterstitialAd.showAd(Yodo1MASAds.this.getCurrentActivity(), placementId);
            }
        });
    }


   @ReactMethod
   public void isInitialized(final Promise promise) {
       promise.resolve(this.initialized);
   }
   private void setInitialized(boolean initialized) {
       this.initialized = initialized;
   }


   @Override
   public String getName() {
       return "Yodo1MASAds";
   }


   private void sendEvent(String value) {
       WritableMap params = Arguments.createMap();
       params.putString("value", value);
       this.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("adEvent", params);
   }


}
