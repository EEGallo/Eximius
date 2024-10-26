import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import { AntDesign } from '@expo/vector-icons'; // Para el ícono de cierre

export default function FilterModal({ onClose }: { onClose: () => void }) {
  return (
    <View style={styles.modalContainer}>
      {/* Filtros Aplicados */}
      <View style={styles.filterHeader}>
        <Text style={styles.filterTitle}>FILTROS APLICADOS</Text>
        <View style={styles.appliedFilters}>
          <TouchableOpacity style={styles.filterTag}>
            <Text style={styles.filterText}>CALZADO</Text>
            <AntDesign name="close" size={14} color="black" />
          </TouchableOpacity>
          <TouchableOpacity style={styles.filterTag}>
            <Text style={styles.filterText}>XS</Text>
            <AntDesign name="close" size={14} color="black" />
          </TouchableOpacity>
          <TouchableOpacity style={styles.filterTag}>
            <Text style={styles.filterText}>HOMBRE</Text>
            <AntDesign name="close" size={14} color="black" />
          </TouchableOpacity>
        </View>
        <TouchableOpacity onPress={onClose}>
          <AntDesign name="close" size={24} color="#BFD936" />
        </TouchableOpacity>
      </View>

      {/* Categorías, Talle y Precio */}
      <ScrollView contentContainerStyle={styles.filterContent}>
        <View style={styles.filterSection}>
          <Text style={styles.sectionTitle}>CATEGORÍA DE PRODUCTO</Text>
          <TouchableOpacity><Text style={styles.optionText}>ROPA</Text></TouchableOpacity>
          <TouchableOpacity><Text style={styles.optionText}>CALZADO</Text></TouchableOpacity>
          <TouchableOpacity><Text style={styles.optionText}>ACCESORIO</Text></TouchableOpacity>
          <TouchableOpacity><Text style={styles.optionText}>DEPORTIVO</Text></TouchableOpacity>
          <TouchableOpacity><Text style={styles.optionText}>INFORMAL</Text></TouchableOpacity>
        </View>

        <View style={styles.filterSection}>
          <Text style={styles.sectionTitle}>TALLE</Text>
          <View style={styles.sizeOptions}>
            {['XS', 'S', 'M', 'L', 'XL', 'XXL'].map((size) => (
              <TouchableOpacity key={size}>
                <Text style={styles.sizeText}>{size}</Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>

        <View style={styles.filterSection}>
          <Text style={styles.sectionTitle}>PRECIO</Text>
          {/* Aquí agregarías un slider para seleccionar el rango de precios */}
          <Text style={styles.priceRange}>$10,999 - $182,999</Text>
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  modalContainer: {
    flex: 1,
    backgroundColor: '#1E231E',
    borderRadius: 10,
    padding: 20,
    marginTop: 40,
  },
  filterHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  filterTitle: {
    fontSize: 18,
    color: '#BFD936',
    fontFamily: 'BebasNeueBold',
  },
  appliedFilters: {
    flexDirection: 'row',
  },
  filterTag: {
    backgroundColor: '#BFD936',
    flexDirection: 'row',
    alignItems: 'center',
    padding: 8,
    borderRadius: 20,
    marginRight: 10,
  },
  filterText: {
    marginRight: 5,
    fontSize: 12,
    fontFamily: 'BebasNeueBook',
  },
  filterContent: {
    paddingBottom: 20,
  },
  filterSection: {
    marginBottom: 20,
  },
  sectionTitle: {
    fontSize: 16,
    color: '#BFD936',
    fontFamily: 'BebasNeueBold',
    marginBottom: 10,
    borderBottomColor: '#BFD936',
    borderBottomWidth: 1,
    paddingBottom: 5,
  },
  optionText: {
    fontSize: 16,
    color: '#FFF',
    marginBottom: 10,
    fontFamily: 'BebasNeueBook',
  },
  sizeOptions: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 10,
  },
  sizeText: {
    fontSize: 16,
    color: '#FFF',
    fontFamily: 'BebasNeueBook',
  },
  priceRange: {
    fontSize: 16,
    color: '#FFF',
    fontFamily: 'BebasNeueBook',
    marginTop: 10,
  },
});
