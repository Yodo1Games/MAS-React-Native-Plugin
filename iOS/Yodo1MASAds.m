#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <Yodo1MasCore/Yodo1Mas.h>
#import "Yodo1MasBannerAdView.h"
#import "CustomView.h"
#import <React/RCTUIManager.h>
@interface Yodo1MASAds : RCTEventEmitter <RCTBridgeModule,
                                          Yodo1MasRewardDelegate,
                                          Yodo1MasAppOpenAdDelegate,
                                          Yodo1MasInterstitialDelegate,
                                          Yodo1MasRewardedInterstitialAdDelegate,
                                          Yodo1MasNativeAdViewDelegate,
                                          Yodo1MasBannerAdViewDelegate>
@property (nonatomic, strong) Yodo1MasBannerAdView *bannerAdView;
@property (nonatomic, strong) Yodo1MasNativeAdView *nativeAdView;
@property (nonatomic, strong) UIViewController *modalViewController;

@end
@implementation Yodo1MASAds {
  bool hasListeners;
}

- (void)startObserving {
  hasListeners = YES;
}
- (void)stopObserving {
  hasListeners = NO;
}
- (NSArray<NSString *> *)supportedEvents {
  return @[ @"adEvent" ];
}
- (void)sendEvent:(NSString *)event {
  if (hasListeners) {
    [self sendEventWithName:@"adEvent" body:@{@"value" : event}];
    // NSLog(@"Yodo1MASAds: sent Event to RN: %@", event);
  }
}

#pragma mark - Yodo1MasInterstitialDelegate
- (void)onInterstitialAdLoaded:(Yodo1MasInterstitialAd *)ad {
    // Code to be executed when an ad finishes loading.
    [self sendEvent:@"onInterstitialAdLoaded"];
}

- (void)onInterstitialAdFailedToLoad:(Yodo1MasInterstitialAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
    [self sendEvent:@"onInterstitialAdFailedToLoad"];
}

- (void)onInterstitialAdOpened:(Yodo1MasInterstitialAd *)ad {
    // Code to be executed when an ad opened
    [self sendEvent:@"onInterstitialAdOpened"];
}

- (void)onInterstitialAdFailedToOpen:(Yodo1MasInterstitialAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad open fails.
    [self sendEvent:@"onInterstitialAdFailedToOpen"];
    [[Yodo1MasInterstitialAd sharedInstance] loadAd];
}

- (void)onInterstitialAdClosed:(Yodo1MasInterstitialAd *)ad {
    // Code to be executed when the ad closed
    [self sendEvent:@"onInterstitialAdClosed"];
    [[Yodo1MasInterstitialAd sharedInstance] loadAd];
}

#pragma mark - Yodo1MasRewardDelegate
- (void)onRewardAdLoaded:(Yodo1MasRewardAd *)ad {
    // Code to be executed when an ad finishes loading.
    [self sendEvent:@"onRewardAdLoaded"];
}

- (void)onRewardAdFailedToLoad:(Yodo1MasRewardAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
    [self sendEvent:@"onRewardAdFailedToLoad"];
}

- (void)onRewardAdOpened:(Yodo1MasRewardAd *)ad {
    // Code to be executed when an ad opened
    [self sendEvent:@"onRewardAdOpened"];
}

- (void)onRewardAdFailedToOpen:(Yodo1MasRewardAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad open fails.
    [self sendEvent:@"onRewardAdFailedToOpen"];
    [[Yodo1MasRewardAd sharedInstance] loadAd];
}

- (void)onRewardAdClosed:(Yodo1MasRewardAd *)ad {
    // Code to be executed when the ad closed
    [self sendEvent:@"onRewardAdClosed"];
    [[Yodo1MasRewardAd sharedInstance] loadAd];
}
- (void)onRewardAdEarned:(Yodo1MasRewardAd *)ad {
    // Code executed when getting rewards
    [self sendEvent:@"onRewardAdEarned"];
}

#pragma mark - Yodo1MasRewardedInterstitialAdDelegate
- (void)onRewardedInterstitialAdLoaded:(Yodo1MasRewardedInterstitialAd *)ad {
    // Code to be executed when an ad finishes loading.
    [self sendEvent:@"onRewardedInterstitialAdLoaded"];
}

- (void)onRewardedInterstitialAdFailedToLoad:(Yodo1MasRewardedInterstitialAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
    [self sendEvent:@"onRewardedInterstitialAdFailedToLoad"];
}

- (void)onRewardedInterstitialAdOpened:(Yodo1MasRewardedInterstitialAd *)ad {
    // Code to be executed when an ad opened
    [self sendEvent:@"onRewardedInterstitialAdOpened"];
}

- (void)onRewardedInterstitialAdFailedToOpen:(Yodo1MasRewardedInterstitialAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad open fails.
    [self sendEvent:@"onRewardedInterstitialAdFailedToOpen"];
    [[Yodo1MasRewardedInterstitialAd sharedInstance] loadAd];
}

- (void)onRewardedInterstitialAdClosed:(Yodo1MasRewardedInterstitialAd *)ad {
    // Code to be executed when the ad closed
    [self sendEvent:@"onRewardedInterstitialAdClosed"];
    [[Yodo1MasRewardedInterstitialAd sharedInstance] loadAd];
}

- (void)onRewardedInterstitialAdEarned:(Yodo1MasRewardedInterstitialAd *)ad {
    [self sendEvent:@"onRewardedInterstitialAdEarned"];
    // Code executed when getting rewards
}


#pragma mark - Yodo1MasBannerAdViewDelegate
- (void)onBannerAdLoaded:(Yodo1MasBannerAdView *)adView {
    // Code to be executed when an ad finishes loading.
    [self sendEvent:@"onBannerAdLoaded"];
}

- (void)onBannerAdFailedToLoad:(Yodo1MasBannerAdView *)adView withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
    [self sendEvent:@"onBannerAdFailedToLoad"];
}

- (void)onBannerAdOpened:(Yodo1MasBannerAdView *)adView {
    // Code to be executed when an ad opened
    [self sendEvent:@"onBannerAdOpened"];
}

- (void)onBannerAdFailedToOpen:(Yodo1MasBannerAdView *)adView withError:(Yodo1MasError *)error {
    // Code to be executed when an ad open fails.
    [self sendEvent:@"onBannerAdFailedToOpen"];
}

- (void)onBannerAdClosed:(Yodo1MasBannerAdView *)adView {
    // Code to be executed when the ad closed
    [self sendEvent:@"onBannerAdClosed"];
}


#pragma mark - Yodo1MasNativeAdViewDelegate
- (void)onNativeAdLoaded:(Yodo1MasNativeAdView *)adView {
    // Code to be executed when an ad finishes loading.
  [self sendEvent:@"onNativeAdLoaded"];
}

- (void)onNativeAdFailedToLoad:(Yodo1MasNativeAdView *)adView withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
  [self sendEvent:@"onNativeAdFailedToLoad"];
}

#pragma mark - Yodo1MasAppOpenAdDelegate
- (void)onAppOpenAdLoaded:(Yodo1MasAppOpenAd *)ad {
    // Code to be executed when an ad finishes loading.
  [self sendEvent:@"onAppOpenAdLoaded"];
}

- (void)onAppOpenAdFailedToLoad:(Yodo1MasAppOpenAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad request fails.
  [self sendEvent:@"onAppOpenAdFailedToLoad"];
}

- (void)onAppOpenAdOpened:(Yodo1MasAppOpenAd *)ad {
    // Code to be executed when an ad opened
  [self sendEvent:@"onAppOpenAdOpened"];
}

- (void)onAppOpenAdFailedToOpen:(Yodo1MasAppOpenAd *)ad withError:(Yodo1MasError *)error {
    // Code to be executed when an ad open fails.
  [self sendEvent:@"onAppOpenAdFailedToOpen"];
    [[Yodo1MasAppOpenAd sharedInstance] loadAd];
}

- (void)onAppOpenAdClosed:(Yodo1MasAppOpenAd *)ad {
    // Code to be executed when the ad closed
    [self sendEvent:@"onAppOpenAdClosedv"];
    [[Yodo1MasAppOpenAd sharedInstance] loadAd];
}
/*RCT_EXPORT_METHOD(isInitialized:(RCTPromiseResolveBlock) resolve
rejecter:(RCTPromiseRejectBlock) reject ) { NSLog(@"Yodo1MASAds: isInitialized:
%@", [Yodo1Mas sharedInstance]. ? @"YES" : @"NO"); resolve(@([Yodo1Mas
sharedInstance].initialize));
}*/

RCT_EXPORT_METHOD(initMasSdk:(BOOL *)ccpa coppa:(BOOL *)coppa gdpr:(BOOL *)gdpr) {
  dispatch_async(dispatch_get_main_queue(), ^{
    Yodo1MasAdBuildConfig *config = [Yodo1MasAdBuildConfig instance];
    config.enableUserPrivacyDialog = YES;  // default value is NO
    [Yodo1Mas sharedInstance].isCCPADoNotSell = ccpa;
    [Yodo1Mas sharedInstance].isCOPPAAgeRestricted = coppa;
    [Yodo1Mas sharedInstance].isGDPRUserConsent = gdpr;
    [[Yodo1Mas sharedInstance] setAdBuildConfig:config];
    
    [[Yodo1Mas sharedInstance] initMasWithAppKey:@"YourAppKey"
        successful:^{
          [self sendEvent:@"onMasInitSuccessful"];
        }
        fail:^(NSError *_Nonnull error) {
          [self sendEvent:@"onMasInitFailed"];
        }];
    

    // IntertsitialAds Delegate and Load
    [Yodo1MasInterstitialAd sharedInstance].adDelegate = self;
    [[Yodo1MasInterstitialAd sharedInstance] loadAd];

    // Reward Ad loading
    [Yodo1MasRewardAd sharedInstance].adDelegate = self;
    [[Yodo1MasRewardAd sharedInstance] loadAd];
    
    //Open Ad
    [Yodo1MasAppOpenAd sharedInstance].adDelegate = self;
    [[Yodo1MasAppOpenAd sharedInstance] loadAd];
    
    //Reward Interstitial Ad
    [Yodo1MasRewardedInterstitialAd sharedInstance].adDelegate = self;
    [[Yodo1MasRewardedInterstitialAd sharedInstance] loadAd];
    
    //banner Ad
    if (!self.bannerAdView) {
      self.bannerAdView = [[Yodo1MasBannerAdView alloc] initWithFrame:CGRectMake(0, 0, 320, 50)];
      self.bannerAdView.adDelegate = self; // Set the delegate if needed
      self.bannerAdView.tag = 999;
      [self.bannerAdView loadAd];
    }
    
    //native Ad
    if (!self.nativeAdView) {
      self.nativeAdView = [[Yodo1MasNativeAdView alloc] initWithFrame:CGRectMake(0, 0, 320, 150)];
      self.nativeAdView.adDelegate = self; // Set the delegate if needed
      self.nativeAdView.tag = 1000;
      [self.nativeAdView loadAd];
    }
  });
}

RCT_EXPORT_METHOD(setCCPA:(BOOL *)ccpa) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [Yodo1Mas sharedInstance].isCCPADoNotSell = ccpa;
    });
}

RCT_EXPORT_METHOD(setCOPPA:(BOOL *)coppa) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [Yodo1Mas sharedInstance].isCOPPAAgeRestricted = coppa;
    });
}

RCT_EXPORT_METHOD(setGDPR:(BOOL *)gdpr) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [Yodo1Mas sharedInstance].isGDPRUserConsent = gdpr;
    });
}

RCT_EXPORT_METHOD(showAppOpenAds) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasAppOpenAd sharedInstance] showAd];
    });
}

RCT_EXPORT_METHOD(showAppOpenAdsWithPlacementId:(NSString *)id) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasAppOpenAd sharedInstance] showAdWithPlacement:id];
    });
}

RCT_EXPORT_METHOD(showRewardedAds) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasRewardAd sharedInstance] showAd];
    });
}

RCT_EXPORT_METHOD(showRewardedAdsWithPlacementId:(NSString *)id) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasRewardAd sharedInstance] showAdWithPlacement:id];
    });
}

RCT_EXPORT_METHOD(showRewardInterstitialAds) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasRewardedInterstitialAd sharedInstance] showAd];
    });
}

RCT_EXPORT_METHOD(showRewardInterstitialAdsWithPlacementId:(NSString *)id) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasRewardedInterstitialAd sharedInstance] showAdWithPlacement:id];
    });
}

RCT_EXPORT_METHOD(showInterstitialAds) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasInterstitialAd sharedInstance] showAd];
    });
}

RCT_EXPORT_METHOD(showInterstitialAdsWithPlacementId:(NSString *)id) {
    dispatch_async(dispatch_get_main_queue(), ^{
      [[Yodo1MasInterstitialAd sharedInstance] showAdWithPlacement:id];
    });
}

//RCT_EXPORT_METHOD(showBannerAds:(nonnull NSNumber *)reactTag) {
//  dispatch_async(dispatch_get_main_queue(), ^{
////    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
////       self.modalViewController = [[UIViewController alloc] init];
////    
////       self.modalViewController.modalPresentationStyle = UIModalPresentationFullScreen;  // Make the modal full screen
////
////       
////       UIView *modalView = [[UIView alloc] initWithFrame:CGRectMake(0, 50, 320, 50)];
////       modalView.backgroundColor = [UIColor whiteColor];
////       [modalView addSubview:self.bannerAdView];
////    
////       
////
////        UIButton *closeButton = [UIButton buttonWithType:UIButtonTypeCustom];
////        [closeButton setTitle:@"✕" forState:UIControlStateNormal];
////        [closeButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
////        closeButton.titleLabel.font = [UIFont systemFontOfSize:30];
////        closeButton.frame = CGRectMake(modalView.bounds.size.width - 50, 30, 40, 40);
////        [closeButton addTarget:self action:@selector(closeModal) forControlEvents:UIControlEventTouchUpInside];
////
////       [modalView addSubview:closeButton];
////       [self.modalViewController.view addSubview:modalView];
////
////       [rootViewController presentViewController:self.modalViewController animated:YES completion:nil];
//           RCTUIManager *uiManager = self.bridge.uiManager;
//           [uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
//               UIView *view = viewRegistry[reactTag];
//               if ([view isKindOfClass:[CustomView class]]) {
//                   CustomView *customView = (CustomView *)view;
//                   [customView addCustomSubview];
//               }
//           }];
//   });
//}
RCT_EXPORT_METHOD(showBannerAds:(nonnull NSNumber *)reactTag) {
    dispatch_async(dispatch_get_main_queue(), ^{
      UIView *view = [self.bridge.uiManager viewForReactTag:reactTag];
          if (view) {
             // Set frame for subview
            view.hidden = false;
            [view addSubview:self.bannerAdView];
          }
    });
}

//RCT_EXPORT_METHOD(showNativeAds) {
//  dispatch_async(dispatch_get_main_queue(), ^{
//    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
//       self.modalViewController = [[UIViewController alloc] init];
//    
//       self.modalViewController.modalPresentationStyle = UIModalPresentationFullScreen;  // Make the modal full screen
//
//       
//       UIView *modalView = [[UIView alloc] initWithFrame:rootViewController.view.bounds];
//       modalView.backgroundColor = [UIColor whiteColor];
//       [modalView addSubview:self.nativeAdView];
//
//        UIButton *closeButton = [UIButton buttonWithType:UIButtonTypeCustom];
//        [closeButton setTitle:@"✕" forState:UIControlStateNormal];
//        [closeButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
//        closeButton.titleLabel.font = [UIFont systemFontOfSize:30];
//        closeButton.frame = CGRectMake(modalView.bounds.size.width - 50, 30, 40, 40);
//        [closeButton addTarget:self action:@selector(closeModal) forControlEvents:UIControlEventTouchUpInside];
//
//       [modalView addSubview:closeButton];
//       [self.modalViewController.view addSubview:modalView];
//
//       [rootViewController presentViewController:self.modalViewController animated:YES completion:nil];
//     
//   });
//}

RCT_EXPORT_METHOD(showNativeAds:(nonnull NSNumber *)reactTag) {
    dispatch_async(dispatch_get_main_queue(), ^{
      UIView *view = [self.bridge.uiManager viewForReactTag:reactTag];
          if (view) {
             // Set frame for subview
            view.hidden = false;
            [view addSubview:self.nativeAdView];
          }
    });
}

RCT_EXPORT_METHOD(closeBannerAd:(nonnull NSNumber *)reactTag)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    UIView *view = [self.bridge.uiManager viewForReactTag:reactTag];
        if (view) {
           // Set frame for subview
          view.hidden = true;
          UIView *subview = [view viewWithTag:999];
                if (subview) {
                  [subview removeFromSuperview];
                }
        }
  });
}

RCT_EXPORT_METHOD(closeNativeAd:(nonnull NSNumber *)reactTag)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    UIView *view = [self.bridge.uiManager viewForReactTag:reactTag];
        if (view) {
           // Set frame for subview
          view.hidden = true;
          UIView *subview = [view viewWithTag:1000];
                if (subview) {
                  [subview removeFromSuperview];
                }
        }
  });
}
// To export a module named Yodo1MASAds
RCT_EXPORT_MODULE(Yodo1MASAds);
@end

