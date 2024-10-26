import { Stack } from 'expo-router';

export default function ShopLayout() {
  return (
    <Stack screenOptions={{
        headerShown: false
}}>
      <Stack.Screen name="index" options={{ title: 'Shop' }} />
      <Stack.Screen name="filter" options={{ presentation: 'modal', title: 'Filter & Sort' }} />
    </Stack>
  );
}
