import { StyleSheet } from 'react-native';

export const sharedStyles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#ABF732',
    padding: 1,
    maxHeight: '100%',
    maxWidth: '100%',
  },
  title: {
    fontFamily: 'BebasNeueBold',
    fontSize: 50,
    color: '#707070',
    marginBottom: 40,
  },
  input: {
    fontFamily: 'BebasNeueBook',
    width: 250,
    height: 50,
    backgroundColor: '#1E231E',
    borderRadius: 40,
    paddingLeft: 95,
    marginBottom: 20,
    fontSize: 18,
    color: '#FFD700',
  },
  button: {
    width: 180,
    height: 50,
    backgroundColor: '#1E231E',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 12,
    marginTop: 20,
  },
  buttonText: {
    color: '#FFF',
    fontSize: 18,
    fontWeight: 'bold',
  },
  errorText: {
    color: 'red',
    marginBottom: 10,
  },
});