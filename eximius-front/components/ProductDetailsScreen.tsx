// ProductDetailsScreen.tsx
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const ProductDetailsScreen = ({ route }) => {
  const { productId } = route.params;

  // Lógica para obtener los detalles del producto con productId

  return (
    <View style={styles.container}>
      <Text>Detalles del producto: {productId}</Text>
      {/* Mostrar la información detallada del producto */}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default ProductDetailsScreen;
