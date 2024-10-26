// app/components/ProductCard.tsx
import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

export default function ProductCard({ name, price }) {
  return (
    <TouchableOpacity style={styles.card}>
      <View style={styles.cardContent}>
        <Text style={styles.productName}>{name}</Text>
        <Text style={styles.productPrice}>${price}</Text>
      </View>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#1E231E',
    borderRadius: 10,
    marginBottom: 20,
    padding: 15,
  },
  cardContent: {
    alignItems: 'center',
  },
  productName: {
    color: '#BFD936',
    fontSize: 18,
    fontWeight: 'bold',
  },
  productPrice: {
    color: '#FFF',
    fontSize: 16,
  },
});
