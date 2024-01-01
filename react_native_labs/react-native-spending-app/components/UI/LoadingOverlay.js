//import liraries
import React, { Component } from 'react';
import { View, Text, StyleSheet, ActivityIndicator } from 'react-native';
import { GlobalStyles } from '../../constants/styles';

// create a component
function LoadingOverlay(){
    return (
        <View style={styles.container}>
            <ActivityIndicator size='large' color="white"/>
        </View>
    );
};

// define your styles
const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: GlobalStyles.colors.primary700,
    },
});

//make this component available to the app
export default LoadingOverlay;
