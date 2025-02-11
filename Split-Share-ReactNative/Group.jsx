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
        </>
    );
};

export default Group;