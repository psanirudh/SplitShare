import { Button, TextInput } from "react-native"

const EditTranc = ({navigation})=>{
    return(
    <>
    <TextInput placeholder="Edit Name"/>
    <TextInput inputMode="numeric" keyboardType="numeric"  placeholder="Edit amount"/>
    <Button title="Save"
    onPress={()=>navigation.navigate('Transc')}/>
    </>
    );
}
export default EditTranc;