const AddTransaction = ({members,groupId}) => {
console.log('anii')
    console.log(members)
return (
<>
<p>Enter shares of each memeber</p>
    <input placeholder="enter a description"/>
    <input placeholder="paid by(user id)"/>
    <input placeholder="enter amount"/>
    <p>Shares</p>
{members?.map(member=>(
    <>
    <div>{member.name}</div>
    <input placeholder="enter an amount"/>
</>
))}
<button onClick={()=>{}} >Add </button>
</>

)

} 
export default AddTransaction;