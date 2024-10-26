// app/(tabs)/shop.tsx
import React from 'react';
import { View, Text, TouchableOpacity, FlatList, StyleSheet, Image } from 'react-native';
import ProductCard from '@/components/ProductCard'; // Si ya tienes un componente de card
import { FontAwesome } from '@expo/vector-icons'; // Importa la librería de íconos
import { useRouter } from 'expo-router';

const products = [
  { id: '1', name: 'Producto 1', price: '199.990' },
  { id: '2', name: 'Producto 2', price: '299.990' },
  { id: '3', name: 'Producto 3', price: '399.990' },
];

export default function ShopScreen() {
  const router = useRouter();

  const handleFilterPress = () => {
    router.push('shop/filter'); // Abrir el modal
  };

  const renderProduct = ({ item }) => (
    <ProductCard name={item.name} price={item.price} />
  );

  return (
    <View style={styles.container}>
      <View style={styles.containerHeader}>
        <Image source={require('@/assets/images/logo-eximius_icon copia 3.png')} style={styles.logo}/>
        <Text style={styles.title}> Shop</Text>
      </View>
      <TouchableOpacity style={styles.button} onPress={handleFilterPress}>
        <FontAwesome name="filter" size={20} color="#BFD936" style={styles.icon} />
        <Text style={styles.buttonText}> Filtrar y Ordenar</Text>
      </TouchableOpacity>
      <FlatList
        data={products}
        renderItem={renderProduct}
        keyExtractor={item => item.id}
        style={styles.productList}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#080C03',
    padding: 10,
    marginTop: 60,
    alignContent: 'center',
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column'
  },

  containerHeader: {
    flexDirection: 'row',

  },
  logo: {
    maxHeight: 50,
    maxWidth: 150,
  },

  title: {
    fontFamily: 'BebasNeueRegular',
    fontSize: 25,
    color: '#BFD936',
    textAlign: 'center',
    marginVertical: 25,
  },
  productList: {
    marginTop: 10,
    paddingHorizontal: 50,
  },
  button: {
    flexDirection: 'row', // Coloca los elementos en fila
    alignItems: 'baseline', // Centra el ícono y el texto verticalmente
    backgroundColor: '#1E231E', // Color de fondo
    paddingVertical: 10, // Altura del botón
    paddingHorizontal: 20, // Ancho del botón
    borderRadius: 30, // Bordes redondeados
    justifyContent: 'center'
  },
  buttonText: {
    color: '#BFD936', // Color del texto
    fontFamily: 'BebasNeueLight',
    fontSize: 20
  },

  icon: {
    marginRight: 10, // Espacio entre el ícono y el texto
  }
});
