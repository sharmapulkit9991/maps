//
//  MapManager.m
//  maps
//
//  Created by Pulkit on 21/01/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "MapManager.h"
#import "MapView.h"

@implementation MapManager

RCT_EXPORT_MODULE()

- (UIView *)view
{

  return [[MKMapView alloc] init];
}

@end
