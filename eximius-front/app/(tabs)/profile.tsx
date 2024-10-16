// app/login.js
import { AuthForm } from '@/components/AuthForm';
import React from 'react';
import { View, Alert } from 'react-native';

const LoginScreen = () => {
  const handleLogin = ({ email, password }) => {
    // Maneja el inicio de sesión aquí
    Alert.alert('Iniciar Sesión', `Email: ${email}, Contraseña: ${password}`);
  };

  return (
    <View>
      <AuthForm isSignup={false} onSubmit={handleLogin} />
    </View>
  );
};

export default LoginScreen;

