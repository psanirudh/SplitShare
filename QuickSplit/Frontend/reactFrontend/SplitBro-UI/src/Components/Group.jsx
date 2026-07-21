import thumbNail from './../assets/thumbnail.png';
 import "./../App.css";
 import { useParams } from 'react-router';

const Group = ({name}) => {
    let {groupId} = useParams();
    return (
    <div className="card">
       <img src={thumbNail}/>
       <b>
         GroupName: {name} , groupId : {groupId}
       </b>
       <br/>
       <h1>You totally owe 0</h1>
    </div>
    )
}
export default Group;
// 2 params function but one with 3rd param default param