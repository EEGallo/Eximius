import { Tabs } from 'expo-router';
import { MaterialIcons } from '@expo/vector-icons';

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        headerShown: false,
        tabBarStyle: {
          position: 'absolute',
          backgroundColor: 'transparent', // Elimina el fondo de las tabs
          elevation: 0, // Elimina la sombra en Android
          borderBottomWidth: 0, // Elimina el borde inferior
          marginBottom: 30,
          borderTopWidth: 0, //

        },
        tabBarLabelStyle: {
          fontSize: 16, // Tamaño del texto de las tabs
          fontWeight: 'bold',
        },
        tabBarActiveTintColor: '#1E231E', // Color del icono y texto cuando está activo
        tabBarInactiveTintColor: '#707070', // Color del icono y texto cuando está inactivo
      }}
    >
      <Tabs.Screen
        name="signup"
        options={{
          tabBarLabel: 'Sign Up',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="person-add" color={color} size={size} />, // Puedes cambiar el icono
        }}
      />
      <Tabs.Screen
        name="login"
        options={{
          tabBarLabel: 'Login',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="login" color={color} size={size} />, // Puedes cambiar el icono
        }}
      />
    </Tabs>
  );
}
