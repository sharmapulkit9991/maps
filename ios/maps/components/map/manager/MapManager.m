//
//  MapManager.m
//  maps
//
//  Created by Pulkit on 21/01/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "MapManager.h"
#import "MapView.h"
#import <CoreLocation/CoreLocation.h>

@interface MapManager()<CLLocationManagerDelegate> {
  CLLocationManager *locationManager;
  MapView *mapView;
}
@end

@implementation MapManager

RCT_EXPORT_MODULE()

- (UIView *)view {
  [self getCurrentLocation];
  mapView = [[MapView alloc] init];
  [mapView setShowsUserLocation:YES];
  return mapView;
}

- (void)getCurrentLocation {
  locationManager = [[CLLocationManager alloc] init];
  locationManager.delegate = self;
  locationManager.distanceFilter = kCLDistanceFilterNone;
  locationManager.desiredAccuracy = kCLLocationAccuracyBest;
  
  if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0)
    [locationManager requestWhenInUseAuthorization];
  
  [locationManager startUpdatingLocation];
}

#pragma mark CLLocationManagerDelegate methods

- (void)locationManager:(CLLocationManager *)manager
     didUpdateLocations:(NSArray<CLLocation *> *)locations API_AVAILABLE(ios(6.0), macos(10.9)) {
  
  CLLocation *latestLocation = [locations lastObject];
  NSLog(@"Updated Location is %f, %f", latestLocation.coordinate.latitude, latestLocation.coordinate.longitude);
  [mapView setCenterCoordinate:latestLocation.coordinate];
  
}


@end
