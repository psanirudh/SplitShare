import React from "react";
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image, TextInput, Button, ScrollView } from 'react-native';
import { database } from "./Firebase";
import { ref, onValue } from "firebase/database";
import { useEffect, useState } from 'react';
import GroupDetail from './GroupDetail';

// const [data, setData] = useState([]);

//   useEffect(() => {
//     const userRef = ref(database, "groups");

//     // Listen for changes in the database
//     const unsubscribe = onValue(userRef, (snapshot) => {
//       if (snapshot.exists()) {
//         console.log(snapshot.val());
//         setData(snapshot.val());
//       } else {
//         console.log("No data available");
//       }
//     });

//     // Cleanup listener when the component unmounts
//     return () => unsubscribe();
//   }, []);

  let arr = [
    {title: 'John', desc: "adfghjklcvbnm,dfghjkl;dfgjl"},
    {title: 'Doe', desc: "adfghjklcvbnm,dfghjkl;dfgjl"},
    {title: 'Smith', desc: "adfghjklcvbnm,dfghjkl;dfgjl"}
  ]


const Home = ({navigation}) =>{
    return (
        <View style={styles.container}>
        <StatusBar />
        <View>
          <TextInput placeholder='Enter adhi samomu Group Name' style={styles.groupInput}/>
          <Button title='Create Group' 
            onPress={() =>  {console.log('Create Group'); navigation.navigate('GroupDetail')}}/> 
        </View>
        {/* <ScrollView>
        {
          Object.values(data).map((item, index) => {
            console.log(item);
            return (
             <View  key={index} style={{flexDirection: 'row', gap: 10, margin: 10}}>
                <Image style={styles.app} source={require('./assets/favicon.png')}/>
                <View>
                  <Text>{item.name}</Text>
                  <Text>descri</Text>
                </View>
              </View>
            )
          })
        }
        </ScrollView> */}
      </View>
    );

};

export default Home;

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    margin: 15
  },
  app:{
   backgroundColor: 'blue'
  },
  groupInput:{
    borderWidth: 1,
    borderColor: 'black',
    padding: 10,
    margin: 10,
    borderRadius: 4
  }
});