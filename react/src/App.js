import React from 'react';
import './App.css';
import Emoji from 'a11y-react-emoji'
import Card from './Card'
import './Card.css'

const wsSession = new WebSocket(`ws://localhost:1234/ws`);

function App() {
  //const ws = React.useRef(new WebSocket(`ws://${window.location.host}/ws`));
  const ws = React.useRef(wsSession);

  ws.current.onopen = () => {
    console.log('Connection open!')
  };

  ws.current.onclose = () => {
    console.log('connection closed');
  };

  ws.current.onerror = () => {
    console.log('ws error');
  };
  return (
    <div className="welcome-page">
      <header className="App-header">Welcome to Exploding Kittens
      <Emoji 
        className ="Title"
         label ="Exploding Kittens" 
         symbol ="🙀💣💥">
        </Emoji></header>
      <body className="body">
        <Card></Card>
       </body>

    </div>
  );
}

export default App;
