import React, { createContext, useState, useEffect, useContext, ReactNode } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { jwtDecode } from 'jwt-decode';
import apiLogin from '../services/LoginAPI'; // Supón que tienes el API para validar token

type AuthContextType = {
  user: { username: string; role: string } | null;
  isAuthenticated: boolean;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
};

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth debe ser usado dentro de AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<{ username: string; role: string } | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Verificar si el token JWT está almacenado cuando la app se carga
  useEffect(() => {
    const checkToken = async () => {
      const token = await AsyncStorage.getItem('jwtToken');
      if (token) {
        try {
          const decodedToken: any = jwtDecode(token); // Se corrigió la sintaxis
          const currentTime = Math.floor(Date.now() / 1000); // Tiempo en segundos
    
          if (decodedToken.exp && decodedToken.exp > currentTime) {
            setIsAuthenticated(true);
            setUser({ username: decodedToken.sub, role: decodedToken.role });
            console.log('Usuario autenticado con token válido');
          } else {
            console.log('Token expirado');
            await AsyncStorage.removeItem('jwtToken');
          }
        } catch (error) {
          console.error('Error al decodificar el token', error);
          await AsyncStorage.removeItem('jwtToken'); // Eliminar token si es inválido
        }
      }
    };
    checkToken();
  }, []);

const login = async (username: string, password: string) => {
  try {
    const response = await apiLogin(username, password);
    const token = response.data.accessToken;
    if (!token) {
      throw new Error('No token received');
    }

    console.log('Token recibido:', token);
    await AsyncStorage.setItem('jwtToken', token);

    const decodedToken: any = jwtDecode(token);
    const currentTime = Math.floor(Date.now() / 1000);

    if (decodedToken.exp < currentTime) {
      console.error('El token ha expirado');
      return;
    }

    setUser({ username: decodedToken.sub, role: decodedToken.role });
    setIsAuthenticated(true);
  } catch (error) {
    console.error('Error al iniciar sesión', error);
    throw error;
  }
};

  const logout = async () => {
    await AsyncStorage.removeItem('jwtToken'); // Eliminar el token al cerrar sesión
    setUser(null);
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, isAuthenticated }}>
      {children}
    </AuthContext.Provider>
  );
};
