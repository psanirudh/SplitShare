import Groups from './Components/Groups';
import { Link } from 'react-router';

const App = () => {
  return (
    <div>
      <div> hello react. This is the landing page</div> 
      <Groups/>
      <Link to={"/group/add"}>Add Group</Link>     
    </div>
  
  )
}

export default App

