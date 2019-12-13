import axios from 'axios';
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Card';

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
        fullWidth
        color="secondary"
        variant="outlined"
        onClick={clickCard}>
        playStack
    </Button>
  </Card>
  <br />
   </div>
   )
}

export default KittenCard;
//do all the moves on the gameboard and when done, send DTO