import React from 'react';
import '../App.css'; // Import the CSS file for styling

function ExtraContent  () {
  return (
    <div style={{
        background: "#93C572",  // Pista green color
        width: "250px",  // Fixed width for the sidebar
        padding: "20px",
        color: "white"
      }} className="sidebar">
     <h2>Current Profile</h2>
     <ul style={{ listStyleType: "none", padding: 0 }}>
        <li><a>Suggestions</a></li>
        

     </ul>
      
    </div>
  );
};


export default ExtraContent;
