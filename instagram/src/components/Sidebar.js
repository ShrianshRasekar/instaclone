import React from 'react';
import '../App.css'; // Import the CSS file for styling

function Sidebar  ({currentProfile}) {
  return (
    <div style={{
        background: "#93C572",  // Pista green color
        width: "250px",  // Fixed width for the sidebar
        padding: "20px",
        color: "white"
      }} className="sidebar">
     <h2>INSTAGRAM</h2>
     <ul style={{ listStyleType: "none", padding: 0 }}>
        <li><a>HOME</a></li>
        <li><a>{currentProfile}</a></li>

        <li><a>MORE</a></li>

     </ul>
      
    </div>
  );
};


export default Sidebar;
