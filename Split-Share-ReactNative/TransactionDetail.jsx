import { Text } from "react-native";
import React from "react";
import { View } from "react-native";
import { Button } from "react-native";

const sampleTransaction = {
    name: 'Boss baby movie',
    paidBy: 'Somu',
    totalAmount: 3000,
    paidToList: {'aadhi':1000,'somu':1000,'ani':1000}
}

const TransactionDetail = ({navigation}) =>{
    return (
     <View>
        <View>
            <Button title='Edit'/>
            <Button title='Delete'/>
        </View>
        <Text>{sampleTransaction.name}</Text>
        <Text>{`${sampleTransaction.paidBy} Paid ${sampleTransaction.totalAmount}`} </Text>

        {Object.entries(sampleTransaction.paidToList).map(([key, value], index) => (
                <Text key={index}>{`${key} owe ${value}`}</Text>
        ))}
        
      </View>
    );

};

export default TransactionDetail;