import './Card.css'
import React, { useState } from 'react';
import {wsSession} from './App';

const Card = ()=>{
    const [cardObject,setCardObject] = useState({
        command:"cardPlayed",
        name:"" , 
        desc:""
    })
	const ws = React.useRef(wsSession);
    ws.current.onmessage = (message) => {
     console.log(message);
     setCardObject(String(message.data));
    }
    const playThisCard=()=>{
        ws.current.send(cardObject);
        console.log("card object sent to server") //testing     
    }
   return(
        <div className = "card-element" 
          onClick={()=>{playThisCard()}}
          >
          <h1>{cardObject.name}</h1>
          <h2>{cardObject.desc}</h2>
      </div>
   );  
};
export default Card;     
