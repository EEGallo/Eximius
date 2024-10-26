import axios from 'axios';

const API_URL = 'http://localhost:8081/api/auth'; // Cambia esta URL según tu configuración

export const registerUser = async (username: string, password: string) => {
  try {
    const response = await axios.post(`${API_URL}/register`, {
      username,
      password,
    });
    return response.data; // Regresa la respuesta exitosa
  } catch (error) {
    console.error('Error en la registración:', error);
    throw error; // Manejo de errores
  }
};
