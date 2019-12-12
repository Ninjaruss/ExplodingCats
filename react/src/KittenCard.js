import axios from 'axios';
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Card';

const [cardName,setCardName] = useState('');
const [cardDescription,setCardDescription] = useState('');
const [cardBody, setCardBody]= useState({});
//1. get cardDTO from server using axios
//2. update state with cardDTO json info
  axios.get('/player')
      .then((res) => {
        setCardName(res.name);
        setCardDescription(res.desc);
      })
      .catch(console.log);
//   const moveCard = event => setCard(event.target.value)
      const playCard=()=>{
        const cardbody = {
 	      name:{cardName},
 	      desc:{cardDescription},
        };
        axios.post('/playStack', cardBody)
 	      .then(console.log)
 	      .catch(console.log)
     };
function KittenCard(){
    return( 	
    <div>
  <Card bg="primary" text="white" style={{ width: '18rem' }}>
    <Card.Header>
      {cardName}
      </Card.Header>
    <Card.Body>
      <Card.Title>🤻😺🤻</Card.Title>
      <Card.Text>
        {cardDescription}
      </Card.Text>
    </Card.Body>
    <Button
    onClick = {playCard}
    >Use card</Button>
  </Card>
  <br />
   </div>
   )
}

export default KittenCard;
//do all the moves on the gameboard and when done, send DTO