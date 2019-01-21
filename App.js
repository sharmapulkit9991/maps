
import React, {Component} from 'react';
import Maps from './src';



type Props = {};
export default class App extends Component<Props> {
  render() {
    return (
      <Maps region={
          {
              latitude: 28.7041,
              longitude: 77.1025
          }
      }
            minZoomLevel={5}
            maxZoomLevel={12}
            initialZoomLevel={11}/>
    );
  }
}
