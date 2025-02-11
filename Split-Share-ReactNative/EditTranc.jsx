import { Button, TextInput } from "react-native-web"

const EditTranc = ({navigation})=>{
    return(
    <>
    <TextInput placeholder="Edit Name"/>
    <input type="int"  placeholder="Edit amount"/>
    <Button title="Save"/>
    </>
    );
}
export default EditTranc;