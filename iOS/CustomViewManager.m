#import "CustomViewManager.h"
#import "CustomView.h"

@implementation CustomViewManager

RCT_EXPORT_MODULE()

- (UIView *)view {
      CustomView *customView = [[CustomView alloc] init];
      customView.hidden = YES; // Hide the view initially
      return customView;
}

@end
