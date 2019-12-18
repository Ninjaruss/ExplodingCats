import './Card.css'
import React, { useState } from 'react';
import ws from './App';

const Card = ()=>{
    const [cardObject,setCardObject] = useState({
        name:"Yay" , 
        desc:"You did it"
    })

    // ws.current.onmessage = (message) => {
    // console.log(message);
    // setCardObject(String(message.data));
    // }
    const playThisCard=()=>{
        ws.current.send(cardObject);
        console.log("hello") //testing     
    }
   return(
        <div className = "card-element" 
          onClick={playThisCard}>
          <h1>{cardObject.name}</h1>
          <h2>{cardObject.desc}</h2>
      </div>
   );  
};
export default Card;     