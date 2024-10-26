import { Tabs } from 'expo-router';
import { MaterialIcons } from '@expo/vector-icons';
import FontAwesome5 from '@expo/vector-icons/FontAwesome5';

export default function TabLayout() {
  return (
    <Tabs 
      screenOptions={{ 
        headerShown: false,
        tabBarStyle:{
          backgroundColor: '#2F3033',
          borderRadius: 40,
          marginBottom: 10,
          marginHorizontal: 20,
          height: 60,
          paddingVertical: 5,

        },
        tabBarActiveTintColor:'#BFD936',
        tabBarInactiveTintColor:'#707070',
        tabBarShowLabel:false,
        }}>


      <Tabs.Screen
        name="home"
        options={{
          tabBarLabel: 'Inicio',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="home" color={color} size={size} />,
        }}
      />
      <Tabs.Screen
        name="shop"
        options={{
          tabBarLabel: 'Tienda',
          tabBarIcon: ({ color, size }) => (
            <FontAwesome5 name="tshirt" size={20} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="cart"
        options={{
          tabBarLabel: 'Carrito',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="shopping-bag" color={color} size={size} />,
        }}
      />
      <Tabs.Screen
        name="profile"
        options={{
          tabBarLabel: 'Perfil',
          tabBarIcon: ({ color, size }) => <MaterialIcons name="person" color={color} size={size} />,
        }}
      />
    </Tabs>
  );
}
