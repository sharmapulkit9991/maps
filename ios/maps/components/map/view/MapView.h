//
//  MapView.h
//  maps
//
//  Created by Pulkit on 21/01/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import <React/RCTComponent.h>

NS_ASSUME_NONNULL_BEGIN

@interface MapView : MKMapView

@property (nonatomic, copy) RCTBubblingEventBlock currentLocation;

@end

NS_ASSUME_NONNULL_END
