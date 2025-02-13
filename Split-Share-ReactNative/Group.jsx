import { Button, TextInput } from 'react-native-web';
import Home from './Home';
const Group = ({navigation}) =>{
    return(
    <>
        <TextInput 
        placeholder='enter group name' />
        <Button
        title='AddMembers'
        onPress={()=>navigation.navigate()
        }/>
        <Button
        title='Create group'
        onPress={()=>navigation.navigate(Home)}/>
        </>
    );
};

export default Group;