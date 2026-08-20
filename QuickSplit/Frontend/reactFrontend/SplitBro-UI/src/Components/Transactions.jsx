import { useState } from "react";
import { Link } from "react-router";

const Transactions = ({transcs}) =>{

            console.log("transcs");
            console.log(transcs);
    return (
        <>
        <div>Transactions in a group</div>

          {
          transcs?.map(trans=> (
            
            <>
             <div>Total {trans.total} spent for {trans.descrption}</div>
             {/* <Link to={"/transaction/"+trans.id}>linkk</Link> */}
            </>
            
          ))
          }  
         
        </>)
}
export default Transactions;