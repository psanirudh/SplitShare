import { useState } from "react"

const AddTransaction = ({members,groupId}) => {

const [transactionDetails,SetTransactionDetails] = useState({
    description:'',
    paidBy:'',
    amount:'',
    shares:{}
});

const handleChange = (e) =>{
   const {name,value} = e.target;

   

   if(name.startsWith('member_')){
     let memberId = name.split('_')[1];
     const finalResult = {
    ...transactionDetails,
    shares : {
        ...transactionDetails.shares,
        [memberId]: parseInt(value)
        }
    }
     SetTransactionDetails(finalResult);
   }
   else{
     const finalResult = {
    ...transactionDetails,
    [name]:value,
    shares : {
        ...transactionDetails.shares,
        }
    }
    SetTransactionDetails(finalResult);

   }
   
}

console.log('anii')
    console.log(members)
return (
<>
<p>Enter shares of each memeber</p>
    <input name="description" placeholder="enter a description" onChange={handleChange}/>
    <input name="paidBy" placeholder="paid by(user id)"  onChange={handleChange}/>
    <input name="amount" placeholder="enter amount"  onChange={handleChange}/>
    <p>Shares</p>
{members?.map(member=>(
    <>
    <div>{member.name}</div>
    <input name={"member_"+member.id} placeholder="enter an amount"  onChange={handleChange}/>
</>
))}
<button onClick={()=>{ console.log('anii add transaction'); console.log(transactionDetails);
    const API_OPTIONS = {
      method: 'POST',
      headers:{
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({...transactionDetails,groupId:groupId})
    };
    fetch("http://localhost:5100/split/AddTransaction"
        ,API_OPTIONS);

 }} >Add </button>
</>

)

} 
export default AddTransaction;