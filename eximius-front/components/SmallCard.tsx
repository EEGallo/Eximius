import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

interface SmallCardProps {
  productName: string;
  price: string;
  onPress: () => void;
}

export const SmallCard: React.FC<SmallCardProps> = ({ productName, price, onPress }) => {
  return (
    <View style={styles.card}>
      <View style={styles.circle} />
      <View style={styles.textContainer}>
        <Text style={styles.productName}>{productName}</Text>
        <Text style={styles.price}>{price}</Text>
        <TouchableOpacity onPress={onPress}>
          <Text style={styles.detail}>vista detallada</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#2D2D35',
    borderRadius: 10,
    padding: 10,
    alignItems: 'center',
    justifyContent: 'center',
    margin: 5,
    width: 'auto',
  },
  circle: {
    width: 80,
    height: 80,
    backgroundColor: '#D8F53C',
    borderRadius: 40,
  },
  textContainer: {
    marginTop: 10,
    alignSelf: 'flex-end',
    alignItems: 'flex-end',
  },
  productName: {
    fontSize: 12,
    color: '#ABF732',
    fontFamily: 'BebasNeueBold',
  },
  price: {
    fontSize: 12,
    color: '#D8F53C',
    fontFamily: 'BebasNeueBold',
  },
  detail: {
    fontSize: 10,
    color: '#ABF732',
    textAlign: 'center',
    fontFamily: 'BebasNeueBook',
  },
});
