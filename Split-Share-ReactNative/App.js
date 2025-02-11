import { StatusBar } from 'expo-status-bar';
import { StyleSheet} from 'react-native';
import { database } from "./Firebase";
import { ref, onValue } from "firebase/database";
import { useEffect, useState } from 'react';
import GroupDetail from './GroupDetail';
import Home from './Home';
import TransactionDetail from './TransactionDetail';
import { NavigationContainer } from '@react-navigation/native';
import { ScreenStack } from 'react-native-screens';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import TranactionDetail from './TransactionDetail';
import Group from './Group';
import EditTranc from './EditTranc'

export default function App() {

  const Stack = createNativeStackNavigator();

  return (
  <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen name="Home" component={Home} options={{title: 'Home'}} />
      <Stack.Screen name="GroupDetail" component={GroupDetail} options={{title: 'GroupDetail'}} />
      <Stack.Screen name="Transc" component={TransactionDetail} options={{title: 'View Transaction Detail'}} />
      <Stack.Screen name="Group" component={Group} options={{title: 'Group'}}/>
      <Stack.Screen name="EditTranc" component={EditTranc} options={{title: 'Edit'}}/>
    </Stack.Navigator> 
    </NavigationContainer>
  );
}
