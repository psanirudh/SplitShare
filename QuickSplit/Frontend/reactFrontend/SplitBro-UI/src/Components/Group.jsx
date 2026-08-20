import thumbNail from './../assets/thumbnail.png';
 import "./../App.css";
 import { useParams } from 'react-router';
import Transactions from './Transactions';
import { useEffect, useState } from 'react';
import AddTransaction from './AddTransaction';




const Group = () => {
    let {groupId} = useParams();
    const [group,setGroup] = useState({});
    const [users,setUsers] = useState([]);
    const [shouldShowMembers,setshouldShowMembers] = useState(false);

    const showMembers = () =>{
      if(users.length == 0 || Object.keys(group)==0)
          return <div>Loading..</div>;
      else if(shouldShowMembers){
           return( users.map(user => (
            <>
            <div> {user.name}</div> 
            {(!group.members.some(member => member.id === user.id )) 
            ? <button onClick={()=>{
              fetch("http://localhost:5100/split/AddMember?groupId="+group.id+"&memberId="+user.id)

            }}>Add</button>
            : <p>member</p>
            }
            </>
            )) );
          }
}

    useEffect(()=>{
      fetch("http://localhost:5100/split/group/get?groupId="+groupId)
      .then((resp)=> resp.json())
      .then((result)=>{
        console.log(result);
        setGroup(result);
      });

      fetch("http://localhost:5100/split/Users/Get")
      .then((resp) => resp.json())
      .then((resultJson)=>{
          setUsers(resultJson);
      });
        
      },[]);

    return (
    <div className="card">
       <img src={thumbNail}/>
       <b>
         GroupName: {group.name} , groupId : {group.id}
       </b>
       <br/>
       <div onClick={()=> { 
       setshouldShowMembers(!shouldShowMembers);}}>Members
       </div>
       
       {
        showMembers()
       } 
       <h1>You totally owe 0</h1>
       <Transactions transcs={group.transactions}/>
        <p>Add Tranasction</p>
          <AddTransaction groupId={group.id} members={group.members}/>
    </div>
    )
}
export default Group;
// 2 params function but one with 3rd param default param