/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';

import MapView from './src/mapView';



type Props = {};
export default class App extends Component<Props> {
  render() {
    return (
      <MapView style={styles.container}
               region={
                   {
                       latitude: 28.7041,
                       longitude: 77.1025
                   }
               }
               minZoomLevel={5}
               maxZoomLevel={12}
               initialZoomLevel={11}
      >

      </MapView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },

});
