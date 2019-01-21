
import React, {Component} from 'react';
import {SafeAreaView, StyleSheet, Text, View, TextInput, Dimensions} from 'react-native';

import MapView from './mapView';



type Props = {

  isSearchable? : boolean,
  header? : boolean,
  heading? : string
};
export default class Maps extends Component<Props> {

  static defaultProps = {
    isSearchable: true,
    header: true,
    heading: 'Enter Location'
  }

  render() {

  const { isSearchable, heading, header } = this.props;
    return (
      <SafeAreaView style={{flex: 1}}>
        {header && <View style = {styles.header}>
          <Text>
            {heading}
          </Text>
          {
            isSearchable && <TextInput style={styles.searchField}>
            </TextInput>
          }
        </View>
       }
      <MapView
        isLocationEnabled = {true}
        style={styles.container}
      />

      </SafeAreaView>
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
  header: {
    justifyContent: 'center',
    alignItems: 'center',
    height: 100,
  },
  searchField: {
    height: 50,
    width: Dimensions.get('window').width - 30,
    borderBottomColor: 'lightgray',
    borderBottomWidth: 1,
    marginHorizontal: 10
  }

});
