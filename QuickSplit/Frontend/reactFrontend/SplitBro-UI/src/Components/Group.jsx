import thumbNail from './../assets/thumbnail.png';
 import "./../App.css";

const Group = ({name}) => {

    return (
    <div className="card">
       <img src={thumbNail}/>
       <b>
         GroupName: {name}
       </b>
       <br/>
       <h1>You totally owe 0</h1>
    </div>
    )
}

export default Group;