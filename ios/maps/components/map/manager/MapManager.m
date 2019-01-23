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

@interface MapManager()<CLLocationManagerDelegate, MapViewDelegate> {
  CLLocationManager *locationManager;
  MapView *currentMapView;
  BOOL isLocationEnabled;
}
@end

@implementation MapManager

RCT_EXPORT_MODULE()

RCT_EXPORT_VIEW_PROPERTY(currentLocation, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onRegionChange, RCTBubblingEventBlock)

RCT_CUSTOM_VIEW_PROPERTY(isInteractionEnabled, BOOL, MKMapView) {
  currentMapView.zoomEnabled = json;
  currentMapView.scrollEnabled = json;
  currentMapView.userInteractionEnabled = json;
}

RCT_CUSTOM_VIEW_PROPERTY(maxZoomLevel, float, MKMapView){
}

RCT_CUSTOM_VIEW_PROPERTY(minZoomLevel, float, MKMapView){
}

RCT_CUSTOM_VIEW_PROPERTY(region, MKCoordinateRegion, MKMapView) {
  json = [RCTConvert NSDictionary:json];
  CLLocationCoordinate2D locationCoordinate;
  locationCoordinate.latitude = [json[@"latitude"] doubleValue];
  locationCoordinate.longitude = [json[@"longitude"] doubleValue];
  double spanInMeters = 100000;
  if (json[@"span"]) {
    spanInMeters = [json[@"span"] doubleValue];
  }
  MKCoordinateRegion viewRegion = MKCoordinateRegionMakeWithDistance(locationCoordinate, spanInMeters, spanInMeters);
  [currentMapView setRegion:viewRegion animated:YES];
}

RCT_CUSTOM_VIEW_PROPERTY(isLocationEnabled, BOOL, MKMapView) {

  isLocationEnabled = (BOOL)json ? (BOOL)json : NO;
  if (isLocationEnabled) {
    [self getCurrentLocation];
  }
}

- (UIView *)view {
  currentMapView = [[MapView alloc] init];
  [currentMapView setShowsUserLocation:YES];
  [currentMapView setDelegate:self];
  return currentMapView;
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

#pragma mark MapViewDelegate methods

- (void)mapView:(MKMapView *)mapView regionDidChangeAnimated:(BOOL)animated {
  MKCoordinateRegion region = mapView.region;
  if(currentMapView.onRegionChange) {
    currentMapView.onRegionChange(@{
                              @"region": @{
                                  @"latitude": @(region.center.latitude),
                                  @"longitude": @(region.center.longitude),
                                  @"latitudeDelta": @(region.span.latitudeDelta),
                                  @"longitudeDelta": @(region.span.longitudeDelta),
                                  }
                              });
  }
}

#pragma mark CLLocationManagerDelegate methods

- (void)locationManager:(CLLocationManager *)manager
     didUpdateLocations:(NSArray<CLLocation *> *)locations API_AVAILABLE(ios(6.0), macos(10.9)) {
  
  CLLocation *latestLocation = [locations lastObject];
//  [currentMapView setCenterCoordinate:latestLocation.coordinate];
  if(currentMapView.currentLocation && isLocationEnabled) {
    currentMapView.currentLocation(@{
                                 @"currentLocation": @{
                                     @"latitude": @(latestLocation.coordinate.latitude),
                                     @"longitude": @(latestLocation.coordinate.longitude),
                                     }
                                 });
  }
}


@end
