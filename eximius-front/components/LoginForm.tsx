import React, { useState } from 'react';
import { View, TextInput, TouchableOpacity, Text } from 'react-native';
import { sharedStyles as styles } from '../styles/sharedStyles';
import AsyncStorage from '@react-native-async-storage/async-storage';
import apiLogin from '../services/LoginAPI'; // Importamos la función apiLogin
import { useAuth } from '@/context/AuthContext';
import { useRouter } from 'expo-router';

export default function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const { login } = useAuth();
  const router = useRouter();

  const handleLogin = async () => {
    try {
      const response = await apiLogin(username, password);
      console.log('Response:', response);
      const userRole = response.data?.roles?.[0];
  
      const token = response.data.accessToken;
      if (!token) {
        console.error('Token no recibido o vacío:', token);
        throw new Error('No token received');
      }
  
      await AsyncStorage.setItem('jwtToken', token);

      // Redirigir basado en el rol
      if (userRole === 'ADMIN') {
        router.push('/(drawer)/adminProfile/adminProfileScreen'); // Redirigir al perfil de administrador
      } else if (userRole === 'USER') {
        // router.push('/profile/roleProfile/userProfile/userProfileScreen'); // Redirigir al perfil de usuario
        router.push('/(drawer)/userProfile/userProfileScreen'); // Redirigir al perfil de usuario

      }

    } catch (error) {
      if (error instanceof Error) {
        if ((error as any).response) {
          console.error('Error response status:', (error as any).response.status);
          console.error('Error response headers:', (error as any).response.headers);
          console.error('Error response data:', (error as any).response.data);
          setErrorMessage((error as any).response.data.message || 'Credenciales inválidas');
        } else if ((error as any).request) {
          console.error('Error request:', (error as any).request);
          setErrorMessage('Error de red. No se pudo conectar al servidor.');
        } else {
          console.error('Error en la configuración de la solicitud:', error.message);
          setErrorMessage('Error en la configuración de la solicitud.');
        }
      } else {
        console.error('Error desconocido:', error);
        setErrorMessage('Ocurrió un error desconocido.');
      }
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>LOGIN</Text>
      <TextInput
        style={styles.input}
        placeholder="User Name"
        placeholderTextColor="#BFD936"
        value={username}
        onChangeText={setUsername}
      />
      <TextInput
        style={styles.input}
        placeholder="Password"
        placeholderTextColor="#BFD936"
        secureTextEntry
        value={password}
        onChangeText={setPassword}
      />
      {errorMessage ? <Text style={styles.errorText}>{errorMessage}</Text> : null}
      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>LOGIN</Text>
      </TouchableOpacity>
    </View>
  );
}
