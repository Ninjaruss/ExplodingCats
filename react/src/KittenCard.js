//import axios from 'axios';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';

//const [cardName,setCardName] = useState('');
//const [cardDescription,setCardDescription] = useState('');
//1. get cardDTO from server using axios
//2. update state with cardDTO json info
// axios.get('/cardDTO')
//     .then((res) => {
//       setCardName(res.name);
//       setCardDescription(res.desc);
//     })
//     .catch(console.log);

    const cardName = 'SKIP';//temporary info until connect to backend
    const cardDescription = 'Skip a players turn';

function KittenCard(){
    return( 	
    <div>
  <Card bg="primary" text="white" style={{ width: '18rem' }}>
    <Card.Header>{cardName}</Card.Header>
    <Card.Body>
      <Card.Title>🤻😺🤻</Card.Title>
      <Card.Text>
        {cardDescription}
      </Card.Text>
    </Card.Body>
  </Card>
  <br />
   </div>
   )
}

export default KittenCard;
//do all the moves on the gameboard and when done, send DTO