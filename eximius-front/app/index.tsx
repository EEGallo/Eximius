// app/index.tsx
import { Redirect } from 'expo-router';


export default function Index() {
  // Redirige automáticamente a las pestañas
  return <Redirect href="/(tabs)/home" />;
}
