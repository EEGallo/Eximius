// src/screens/ProtectedScreen.tsx
import React from 'react';
import { View, Text, Button } from 'react-native';
import { useAuth } from '@/context/AuthContext';
import { useRouter } from 'expo-router';

export default function ProtectedScreen() {
  const { isAuthenticated, logout } = useAuth();
  const router = useRouter();

  if (!isAuthenticated) {
    router.push('./profile/login'); // Redirige al login si no está autenticado
    return null;
  }

  return (
    <View>
      <Text>Esta es una pantalla protegida</Text>
      <Button title="Cerrar sesión" onPress={logout} />
    </View>
  );
}
