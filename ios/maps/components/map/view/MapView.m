//
//  MapView.m
//  maps
//
//  Created by Pulkit on 21/01/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "MapView.h"

@implementation MapView

#pragma mark MKMapViewDelegate methods

- (void)mapView:(MKMapView *)mapView regionDidChangeAnimated:(BOOL)animated {
  if ([self.delegate respondsToSelector:@selector(mapView:regionDidChangeAnimated::)]) {
    [self.delegate mapView:mapView regionDidChangeAnimated:animated];
  }
}
@end
