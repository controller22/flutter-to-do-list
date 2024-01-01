//import liraries
import React from 'react';
import { Button, StyleSheet, Text, View } from 'react-native';
import { GlobalStyles } from '../../constants/styles';

// create a component
function ErrorOverlay({ message}) {
    return (
        <View style={styles.container}>
            <Text style={[styles.text, styles.title]}>오류가 발생하였습니다</Text>
            <Text style={styles.text}>{message}</Text>
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
    text: {
        color: 'white',
        textAlign: 'center',
        marginBottom: 8
    },
    title: {
        fontSize: 20,
        fontWeight: 'bold',
    }
});

//make this component available to the app
export default ErrorOverlay;
