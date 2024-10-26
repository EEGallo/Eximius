// app/profile/register.tsx
import React from 'react';
import { View, StyleSheet } from 'react-native';
import SignUpForm from '@/components/SignUpForm';  // Importa el componente de registro

export default function RegisterScreen() {
  return (
    <View style={styles.container}>
      <SignUpForm />
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