import { Text } from "react-native";
import React from "react";
import { View } from "react-native";
import { Button } from "react-native-web";
import {StyleSheet} from 'react-native';

let transactions = ["ani", "bala", "chandu", "dinesh", "eswar", "faiyaz", "gopi", "hari", "ishan", "jai", "kiran", "lakshmi", "mani", "naveen", "omkar", "praveen", "qureshi", "raju", "sai", "teja", "uma", "vijay", "waseem", "xavier", "yash", "zakir"];

const GroupDetail = ({navigation,route}) =>{
    return (
        <View style={styles.container}>
            <Text style={styles.grp}>The group name is {route.params.title}</Text>
        {transactions.map((transaction, index) => (
          <Text onPress={() =>  {navigation.navigate('Transc')}} 
            key={index}>{transaction}</Text>
        ))}
       
        
        </View>
    );

};

export default GroupDetail;

const styles = StyleSheet.create({
    container: {
    },
    grp:{
       fontSize:30,
       fontWeight:'bold',    }
});
