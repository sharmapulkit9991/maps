// MapView.js

import {requireNativeComponent, ViewPropTypes} from 'react-native';
import PropTypes from 'prop-types'
// requireNativeComponent automatically resolves 'RNTMap' to 'RNTMapManager'

const iface = {
    name: 'WadiMaps',
    propTypes: {
        region: PropTypes.instanceOf(Object),
        minZoomLevel: PropTypes.number,
        maxZoomLevel: PropTypes.number,
        initialZoomLevel: PropTypes.number,
        isInteractionEnabled: PropTypes.bool,
        onRegionChange: PropTypes.func,
      onCurrentLocation: PropTypes.func,
        moveToCurrentLocation:PropTypes.bool,
        ...ViewPropTypes, // include the default view properties
    },
};
module.exports = requireNativeComponent('Map', iface);
