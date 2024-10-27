import logo from './logo.svg';
import './App.css';
import Sidebar from './components/Sidebar';
import MiddleContent from './components/MiddleContent';
import AddUserProfile from './components/AddUserProfile';
import {
     BrowserRouter as Router,
     Routes,
     Route,
     Link
   } from "react-router-dom";
import ExtraContent from './components/ExtraCotent';
   
function App() {
     return (
       <Router>
         <div style={{ display: "flex", width: "100vw", height: "100vh" }}>
           <Sidebar currentProfile="MyProfile" style={{ flex: "1 1 20%", backgroundColor: "#f0f0f0" }} />
           
           <div style={{ flex: "1 1 40%", display: "flex", flexDirection: "column" }}>
             <Routes style={{ flexGrow: 1 }}>
               <Route path="/middleContent" element={<MiddleContent />} />
               <Route path="/addUserProfile" element={<AddUserProfile />} />
             </Routes>
           </div>
           
           <ExtraContent style={{ flex: "1 1 40%", backgroundColor: "#e0e0e0" }} />
         </div>
       </Router>
     );
   }
export default App;
