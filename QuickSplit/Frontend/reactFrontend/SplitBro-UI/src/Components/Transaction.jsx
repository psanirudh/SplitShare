import { useParams } from "react-router"

const Transaction = () =>{
    let {transactionId} = useParams();
return (
    <div>
      <p> tid : {transactionId} </p>
      <p> name </p>
    </div>
)
}
export default Transaction