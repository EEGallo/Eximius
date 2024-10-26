import axios from 'axios';

const API_URL = 'http://localhost:8081/api/auth'; // Cambia esta URL según tu configuración

export const loginUser = async (username: string, password: string) => {
  try {
    const response = await axios.post(`${API_URL}/login`, {
      username,
      password,
    });
    // Guarda el token JWT recibido del servidor
    return response.data; // Usualmente, aquí recibes el token JWT
  } catch (error) {
    console.error('Error en el login:', error);
    throw error; // Manejo de errores
  }
};
