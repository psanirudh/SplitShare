import { useEffect, useState } from 'react';
import thumbNail from './../assets/thumbnail.png';

import { Link } from 'react-router';

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
        <>
        <div> Group List</div> 
        {groups?.map(grp=> (
          <div key={grp.id} className='card'>
            <img src={thumbNail}/>
            <b>GroupName: {grp.name} </b>
            <br/>
            <Link to={"/group/"+grp.id}>grp</Link> 
          </div>
        ))}
        </>
        )

       }

export default Groups;


