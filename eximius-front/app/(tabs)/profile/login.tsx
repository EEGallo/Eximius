// src/screens/profile/login.tsx
import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { useAuth } from '@/context/AuthContext';
import { useRouter } from 'expo-router';
import LoginForm from '@/components/LoginForm';


export default function LoginScreen() {


  const { isAuthenticated } = useAuth();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useAuth();
  const router = useRouter();

  if (isAuthenticated) {
    router.push('/'); // Si ya está autenticado, redirige a la página principal
    return null;
  }

  const handleLogin = async () => {
    await login(username, password);
    router.push('/'); // Redirige a la página de inicio después del login
  };

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