import React from 'react';
import { View, StyleSheet, Text, Image, Platform } from 'react-native';
import { MainCard } from '@/components/MainCard';
import { SmallCard } from '@/components/SmallCard';

export default function HomeScreen() {
  const handleViewDetails = () => {
    console.log('Go to shop');
    // Aquí rediriges a la pantalla de shop
  };

  return (
    <View style={styles.container}>
      <Image source={require('../../assets/images/logo-eximius_icon copia 3.png')} style={styles.logo}/>
      <Text style={styles.description}>Ofertas del día</Text>
      <MainCard productName="ZAPATILLAS MC'FLY" price="$199.990" onPress={handleViewDetails} />
      <View style={styles.smallCardContainer}>
        <SmallCard productName="ZAPATILLAS MC'FLY" price="$199.990" onPress={handleViewDetails} />
        <SmallCard productName="ZAPATILLAS MC'FLY" price="$199.990" onPress={handleViewDetails} />
        <SmallCard productName="ZAPATILLAS MC'FLY" price="$199.990" onPress={handleViewDetails} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#020301',
    padding: 10,
    marginTop: 80,
    alignContent: 'center',
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'column'
  },
  logo: {
    height: '5%',
    width: '30%',
    marginBottom: 30
  },
  smallCardContainer: {
    flexDirection: 'row',
    marginTop: 20,
    marginHorizontal: 20
  },
  description: {
      color: '#707070',
      fontFamily: 'BebasNeueBold',
      fontSize: 25     
  }
});
