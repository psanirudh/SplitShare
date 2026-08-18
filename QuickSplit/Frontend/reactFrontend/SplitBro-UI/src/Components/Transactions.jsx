import { useState } from "react";
import { Link } from "react-router";

const Transactions = () =>{
    const [transcs,setTranscs] = useState([{id:1,name:"coffe break",totalAmount:233},
        {id:2,name:"Team Lunch",totalAmount:50000}
    ])

    return (
        <>
        <div>Transactions in a group</div>

          {
          transcs.map(trans=> (
            
            <>
             <div>Total {trans.totalAmount} spent for {trans.name}</div>
             <Link to={"/transaction/"+trans.id}>linkk</Link>

            <div>hi</div>

            </>
            
          ))
          }  
         
        </>)
}
export default Transactions;