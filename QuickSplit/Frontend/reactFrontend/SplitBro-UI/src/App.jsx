import { useEffect, useState } from 'react';
import './App.css'
import thumbNail from './assets/thumbnail.png';



    

const Greeting = ({userr,dept}) => {

  const baseUrl = "";
   
  const [hasLiked,setHasLiked] = useState(false);

  
 
 useEffect(()=>{console.log("effect uh");},[hasLiked]);
  console.log("ani ",userr);
  return(
    <div className="card">
       <img src={thumbNail}/>

      <b  onClick={() => alert("confirm")}>

         how do you do? {userr} from {dept}
      </b>
      <br/>
      <button onClick={() => setHasLiked(!hasLiked)}> 
        {hasLiked? "🗻" : "hii" }
      </button>

    </div>
  )
}

const App = () => {
 
    const [users,setUsers] = useState([]);

    const API_OPTIONS = {
      method: 'GET',
      headers:{
        accept: 'application/json',
        Authorization: 'Bearer abc1234'
      }
    }

  async function fetchUsers() {
      try {
        const response = await fetch(
          "https://localhost:7166/split/users/get"
        ,API_OPTIONS);
        const data = await response.json();
        console.log('anii ',data);
        
        setUsers(data);
      } catch (error) {
        console.error(error);
      }
    }
  
 
  useEffect(()=>{
     fetchUsers();
     
},[]);

  return (
    <div>
      <div> hello react</div> 
      {users?.map(usr=> { return <Greeting key={usr.id} userr={usr.name} /> })}
           
    </div>
  
  )
}

export default App


// {{users.map(usr=> ( <Greeting userr={usr} key={usr.id} /> ))} 
