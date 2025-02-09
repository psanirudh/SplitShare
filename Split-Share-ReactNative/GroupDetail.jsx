import { Text } from "react-native";
import React from "react";
import { View } from "react-native";

let transactions = ["ani", "bala", "chandu", "dinesh", "eswar", "faiyaz", "gopi", "hari", "ishan", "jai", "kiran", "lakshmi", "mani", "naveen", "omkar", "praveen", "qureshi", "raju", "sai", "teja", "uma", "vijay", "waseem", "xavier", "yash", "zakir"];

const GroupDetail = ({navigation}) =>{
    return (
        <View>
        {transactions.map((transaction, index) => (
          <Text onPress={() =>  {navigation.navigate('Transc')}} 
            key={index}>{transaction}</Text>
        ))}
        
        </View>
    );

};

export default GroupDetail;