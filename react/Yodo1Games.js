import {
  NativeModules,
  NativeEventEmitter,
  Alert,
  findNodeHandle,
  requireNativeComponent,
  Platform,
} from 'react-native';
const {Yodo1MASAds} = NativeModules;

export const NativeView = requireNativeComponent('NativeView');
export const BannerView = requireNativeComponent('BannerView');
export const CustomView = requireNativeComponent('CustomView');

export const hasReward = () => {
  // Your code here to check if the user has earned a reward
  // From Redux, AsyncStorage
  return true;
};
export const setReward = () => {
  // Your code here to confirm that the user earned a reward
  // Redux, AsyncStorage, etc...
};
export const isAdsInitialized = () => {
  // Your code here to check if the ads are initialized from other components
};
export const setAdsInitialized = isInitialized => {
  // Your code here to set the adsInitialized state
  // Redux, AsyncStorage, etc...
};
export const showAdExplainer = () =>
  Alert.alert('You must watch the entire Ad to earn the reward!');
export const ShowRewardGivenAlert = () => Alert.alert('Reward Added');

const handleYodoEvent = ({value}) => {
  __DEV__ && console.log(`MAS Event: ${value}`);
  switch (value) {
    // Event received when Ads initialization is successful
    case 'onMasInitSuccessful':
      //Yodo1MASAds.showBannerAds(); // This has effect only in Android
      // setAdsInitialized(true)
      // isAdsInitialized(true);
      break;
    case 'onMasInitFailed':
      setTimeout(() => Yodo1MASAds.initMasSdk(true, true, true), 5000); // Try again in 5 seconds
      break;
    // User earned a reward!
    case 'reward-onRewardAdEarned':
      setTimeout(() => (hasReward() ? null : ShowRewardGivenAlert()), 5000);
      setReward();
      break;
    // Reward Ad loaded you can check it by this method if ad is loaded or not
    case 'reward-onRewardAdLoaded':
      setTimeout(() => (hasReward() ? null : showAdExplainer()), 500);
      break;
    // Reward Ad Failed to Open
    case 'reward-onRewardAdOpened':
      setTimeout(() => (hasReward() ? null : showAdExplainer()), 500);
      break;
    // User closed the Ad, let's check if he earned a reward
    case 'reward-onRewardAdClosed':
      setTimeout(() => (hasReward() ? null : showAdExplainer()), 500);
      break;
    // Something went wrong, let's skip the checks on reward
    case 'reward-onRewardAdFailedToLoad':
      setReward();
      break;
    case 'adRefresh':
      break;
  }
};
// Call me on App Initialization
export const registerYodoAds = () => {
  const eventEmitter = new NativeEventEmitter(Yodo1MASAds);
  const eventListener = eventEmitter.addListener('adEvent', handleYodoEvent);
  //pass CCPA, COPPA, GDPR values in the initSDK method
  Yodo1MASAds.initMasSdk(true, true, true);
  return eventListener;
};
export const showBannerAds = (ref: any) => {
  //const adsAvailable = await Yodo1MASAds.isInitialized()
  //adsAvailable &&
  // Perform UI-related tasks here
  const reactTag = findNodeHandle(ref);
  Yodo1MASAds.showBannerAds(reactTag);
};

export const showInterstitialAds = async () => {
  //const adsAvailable = await Yodo1MASAds.isInitialized()
  //adsAvailable &&
  Yodo1MASAds.showInterstitialAds();
};
export const showInterstitialAdsWithPlacementId = async () => {
  Yodo1MASAds.showInterstitialAdsWithPlacementId('InterstitialAdPlacementId');
};

export const showRewardedAds = async () => {
  // const adsAvailable = await Yodo1MASAds.isInitialized()
  // adsAvailable ?
  Yodo1MASAds.showRewardedAds(); //: setReward()
};
export const showRewardedAdsWithPlacementId = async () => {
  Yodo1MASAds.showRewardedAdsWithPlacementId('RewardedAdPlacementId');
};

export const showAppOpenAds = async () => {
  Yodo1MASAds.showAppOpenAds();
};

export const showAppOpenAdsWithPlacementId = async () => {
  Yodo1MASAds.showAppOpenAdsWithPlacementId('AppOpenAdPlacementId');
};

export const showRewardInterstitialAds = async () => {
  Yodo1MASAds.showRewardInterstitialAds();
};

export const showRewardInterstitialAdsWithPlacementId = async () => {
  Yodo1MASAds.showRewardInterstitialAdsWithPlacementId(
    'RewardInterstitialAdPlacementId',
  );
};

export const showNativeAds = async (ref: any) => {
  const reactTag = findNodeHandle(ref);
  Yodo1MASAds.showNativeAds(reactTag);
};

export const setCCPA = async () => {
  Yodo1MASAds.setCCPA(true);
};

export const setCOPPA = async () => {
  Yodo1MASAds.setCOPPA(true);
};

export const setGDPR = async () => {
  Yodo1MASAds.setGDPR(true);
};

export const closeBannerAd = async (ref: any) => {
  const reactTag = findNodeHandle(ref);
  Platform.OS === 'ios'
    ? Yodo1MASAds.closeBannerAd(reactTag)
    : Yodo1MASAds.closeBannerAd();
};

export const closeNativeAd = async (ref: any) => {
  const reactTag = findNodeHandle(ref);
  Platform.OS === 'ios'
    ? Yodo1MASAds.closeNativeAd(reactTag)
    : Yodo1MASAds.closeNativeAd();
};
