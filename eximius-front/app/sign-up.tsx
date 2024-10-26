// app/profile/register.tsx
import React from 'react';
import { View } from 'react-native';
import SignUpForm from '@/components/SignUpForm';  // Importa el componente de registro

export default function RegisterScreen() {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <SignUpForm />  {/* Usa el componente de registro */}
    </View>
  );
}
