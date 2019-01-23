import React, {Component} from 'react';
import {SafeAreaView, StyleSheet, Text, View, TextInput, Dimensions, TouchableOpacity} from 'react-native';

import MapView from './mapView';


type Props = {

    isSearchable?: boolean,
    header?: boolean,
    heading?: string
};
export default class Maps extends Component<Props> {

    static defaultProps = {
        isSearchable: true,
        header: true,
        heading: 'Enter Location'
    }

    constructor() {
        super()
        this.state = {
            moveToCurrentLocation: false,
            latitude: 28.7041,
            longitude: 77.1025,
            currentLocationLatitude: null,
            currentLocationLongitude: null
        }
    }

    render() {

        const {isSearchable, heading, header} = this.props;
        const {moveToCurrentLocation} = this.state;
        return (
            <SafeAreaView style={{flex: 1}}>
                {header && <View style={styles.header}>
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
                  onCurrentLocation={(event) => {
                    this.setState({
                      currentLocationLatitude: event.currentLocation.latitude,
                      currentLocationLongitude: event.currentLocation.longitude
                    });
                  }}
                    isLocationEnabled={true}
                    style={styles.container}
                    region={
                        {
                            latitude: 28.7041,
                            longitude: 77.1025
                        }
                    }
                    minZoomLevel={5}
                    maxZoomLevel={12}
                    initialZoomLevel={11}
                    onRegionChange={(event) => {
                }}
                    moveToCurrentLocation={moveToCurrentLocation}

                />
                <TouchableOpacity onPress={() => {
                    this.setState({moveToCurrentLocation: true})

                }}
                                  style={{position: 'absolute', top: 50}}>
                    <Text style={{color: 'red', fontSize: 18}}>Go to current location</Text>
                </TouchableOpacity>


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
