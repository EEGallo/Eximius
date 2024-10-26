import React, { useState } from 'react';
import { View, TextInput, TouchableOpacity, Text } from 'react-native';
import { sharedStyles as styles } from '../styles/sharedStyles'; // Importa los estilos compartidos
import AsyncStorage from '@react-native-async-storage/async-storage'; // Asegúrate de importar AsyncStorage
import api from '../services/api'; // Importa la instancia configurada de axios


export default function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');


  const handleLogin = async () => {
    try {
        const response = await api.post('/login', { username, password });
        const { token } = response.data;
        // Guarda el token en AsyncStorage
        await AsyncStorage.setItem('jwtToken', token);
        console.log('Login exitoso, token guardado:', token);
      } catch (error) {
        setErrorMessage('Error en el login');
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
      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>LOGIN</Text>
      </TouchableOpacity>
    </View>
  );
}
