/** Player's hand can remove card from it when card is used.
 * play card by clicking card. send card to playStack. can play multiple cards in one turn
 * fills empty card components
 * 
 */
import ws from './App'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Card';
import { useState } from 'react';

function hand(){
const [playerHand,setPlayerHand] = useState([{
  CardObject: {
    name:"",
    desc:""
  }  
}
])


//receive a hand from server. set hand to message
ws.current.onmessage()=(message) =>{
  console.log(message);
  if(message.command == "updateHand"){
    setPlayerHand(JSON(message.handResponse))
  }
}
const handItem = (
  <li>  <Card bg="primary" text="white" style={{ width: '18rem' }}>
  <Card.Header>
    {CardObject.name}
    </Card.Header>
  <Card.Body>
    <Card.Text>
      {CardObject.desc}
    </Card.Text>
  </Card.Body>
  <Button
      fullWidth
      color="secondary"
      variant="outlined"
      onClick={playThisCard}>
  </Button>
  </Card>
  </li>
 );

const createHand =
playerHand.cardObject.map((playerHand)=>
  <handItem 
  key = {playerHand.name}
  value = {playerHand.desc}></handItem>

);
return(
  <>
    <ul>{createHand}</ul>
  </>
);
} 
export default hand;