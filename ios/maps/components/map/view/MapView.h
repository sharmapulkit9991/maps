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

@protocol MapViewDelegate <NSObject>
@optional
- (void)mapView:(MKMapView *)mapView regionDidChangeAnimated:(BOOL)animated;
@end

@interface MapView : MKMapView<MKMapViewDelegate>

@property (nonatomic, copy) RCTBubblingEventBlock currentLocation;
@property (nonatomic, copy) RCTBubblingEventBlock onRegionChange;
@property (nonatomic, weak) id <MapViewDelegate> delegate;
@end

NS_ASSUME_NONNULL_END
