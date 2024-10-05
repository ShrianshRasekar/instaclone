import logo from './logo.svg';
import './App.css';
import Sidebar from './components/Sidebar';
import MiddleContent from './components/MiddleContent';
import AddUserProfile from './components/AddUserProfile';

function App() {
 return(
  <div  style={{ display: "flex", height: "100vh" }}>

       <Sidebar currentProfile="MyProfile"/>
       <MiddleContent/>
       <AddUserProfile/>
  </div>
 );
}

export default App;
