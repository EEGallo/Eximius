import { Tabs } from 'expo-router';
import { MaterialIcons } from '@expo/vector-icons';

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        headerShown: false,
        tabBarStyle: {
          position: 'absolute',
          backgroundColor: 'transparent',
          elevation: 0,
          borderBottomWidth: 0,
          marginBottom: 30,
          borderTopWidth: 0,
        },
        tabBarLabelStyle: {
          fontSize: 16,
          fontWeight: 'bold',
        },
        tabBarActiveTintColor: '#1E231E',
        tabBarInactiveTintColor: '#707070',
      }}
    >
      <Tabs.Screen
        name="signup"
        options={{
          tabBarLabel: 'Sign Up',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="person-add" color={color} size={size} />,
        }}
      />
      <Tabs.Screen
        name="login"
        options={{
          tabBarLabel: 'Login',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="login" color={color} size={size} />,
        }}
      />
    </Tabs>
  );
}
