import React from 'react';
import '../App.css'; // Import the CSS file for styling
import { Card, CardBody } from 'reactstrap';
import { Link } from 'react-router-dom';

function Sidebar  ({currentProfile}) {
  return (
    <div style={{
        background: "#93C572",  // Pista green color
        width: "250px",  // Fixed width for the sidebar
        padding: "20px",
        color: "white"
      }} className="sidebar">
        <Card><CardBody>
        <h2>INSTAGRAM</h2>
         <ul style={{ listStyleType: "none", padding: 0 }}>
        <li><Link to="/middleContent">HOME</Link></li>
        <li><Link to="/middleContent">{currentProfile}</Link></li>
        <li><Link  to="/addUserProfile">AddUserProfile</Link></li>
        <li><Link to="/middleContent">More</Link></li>

     </ul>
     </CardBody>
     </Card>
    </div>
  );
};


export default Sidebar;
