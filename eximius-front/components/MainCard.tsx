import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

interface MainCardProps {
  productName: string;
  price: string;
  onPress: () => void;
}

export const MainCard: React.FC<MainCardProps> = ({ productName, price, onPress }) => {
  return (
    <View style={styles.card}>
      <View style={styles.circle} />
      <View style={styles.textContainer}>
        <Text style={styles.productName}>{productName}</Text>
        <Text style={styles.price}>{price}</Text>
        <TouchableOpacity onPress={onPress}>
          <Text style={styles.detail}>vista detallada →</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#2D2D35',
    borderRadius: 15,
    padding: 20,
    marginTop: 20,
    marginHorizontal: 30,
    alignItems: 'center',
    justifyContent: 'center',
    width: 320,
    height:360,
  },
  circle: {
    width: 270,
    height: 270,
    backgroundColor: '#D8F53C',
    borderRadius: 200,
    position: 'relative',
    top: -10,
    left: -20,
  },
  textContainer: {
    marginTop: 5,
    alignSelf: 'flex-end',
    alignItems: 'flex-end',
    left: -10,
  },
  productName: {
    fontSize: 30,
    color: '#ABF732',
    fontFamily: 'BebasNeueBold',
  },
  price: {
    fontSize: 24,
    color: '#BFD936',
    fontFamily: 'BebasNeueBold',
  },
  detail: {
    fontSize: 16,
    color: '#ABF732',
    textAlign: 'center',
    fontFamily: 'BebasNeueBook',
  },
});
