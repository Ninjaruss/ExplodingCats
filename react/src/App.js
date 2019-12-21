import React from 'react';
import './App.css';
import Card from './Card'
import './Card.css'

const wsSession = new WebSocket(`ws://localhost:1234/ws`);
function App() {
  const [username,setUsername] = React.useState("")
  //const ws = React.useRef(new WebSocket(`ws://${window.location.host}/ws`));
  const ws = React.useRef(wsSession);
  const userResponse={
    command:"connectUser",
    stringResponse: username 
  }
  ws.current.send(userResponse)
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
      <header className="App-header">Welcome to Exploding Kittens</header>
        <input onSubmit={setUsername}>Enter your name</input>
      <body className="body">
        <Card></Card>
       </body>

    </div>
  );
}

export default App;
