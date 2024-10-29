// src/screens/profile/login.tsx
import React from 'react';
import { View, StyleSheet } from 'react-native';
import LoginForm from '@/components/LoginForm';


export default function LoginScreen() {

  return (
    <View style={styles.container}>
        <LoginForm/>
    </View>
  );
}

const styles = StyleSheet.create({ 
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#ABF732',
        maxHeight: '100%',
        maxWidth: '100%'
  },
});