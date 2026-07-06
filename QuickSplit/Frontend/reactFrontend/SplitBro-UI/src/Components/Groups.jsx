import { useEffect, useState } from 'react';
import Group from './Group';

const Groups = ({userId}) => {

    console.log(userId);
    
    const [groups,setGroups] = useState([]);


   

    useEffect(()=>{

       const API_OPTIONS = {
      method: 'GET',
      headers:{
        accept: 'application/json',
        Authorization: 'Bearer abc1234'
      }
    }


       async function fetchGroups() {
      try {
        const response = await fetch(
          "https://localhost:7166/split/groups/get"
        ,API_OPTIONS).then();
        const data = await response.json();
        
        setGroups(data);
      } catch (error) {
        console.error(error);
      }
    }


     fetchGroups();  
     },[]);

    return (
        <div>
        <div> Group List</div> 
        {groups?.map(grp=> ( <Group key={grp.id} name={grp.name} /> ))}
        </div>
    )
}

export default Groups;


