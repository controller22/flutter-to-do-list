//import liraries
import { Ionicons } from '@expo/vector-icons';
import React from 'react';
import { Pressable, StyleSheet, View } from 'react-native';

// create a component
function IconButton({ icon, size, color, onPress }) {
    return (
        <Pressable onPress={onPress} style={({ pressed }) => pressed && styles.pressed}>
            <View style={styles.buttonContainer}>
                <Ionicons name={icon} size={size} color={color} />
            </View>
        </Pressable>
    );
};

// define your styles
const styles = StyleSheet.create({
    buttonContainer: {
        borderRadius: 24,
        padding: 6,
        marginVertical:2,
        marginHorizontal:8,
    },
    pressed: {
        opacity: 0.75
    }
});

//make this component available to the app
export default IconButton;
