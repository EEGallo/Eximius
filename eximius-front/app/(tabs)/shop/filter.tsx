import { View, Text, Button } from 'react-native';
import { useRouter } from 'expo-router';
import FilterModal from '@/components/FilterModal';



export default function FilterScreen() {
  const router = useRouter();

  const handleClose = () => {
    router.back();
  };

  return (
    <View style={{ flex: 1 }}>
      <FilterModal onClose={handleClose} />
    </View>
  );
}

