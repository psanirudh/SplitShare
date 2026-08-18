 import "./../App.css";
 import { useState } from 'react';
 import { useNavigate } from "react-router";

const AddGroup = () => {
    const [groupName,setGroupName] = useState();
    let navigate = useNavigate();
    return (
    <div>
       <input placeholder='Enter a group Name' value={groupName} onChange={e =>setGroupName(e.target.value)} />
       <button onClick={()=>{ 
        fetch("https://localhost:7166/split/Group/Add?groupName="+groupName)
        .then((res)=> res.json())
        .then((result) => {console.log(result); navigate('/group/'+result) }); 
        }}>Add</button>
    </div>
    )
}
export default AddGroup;