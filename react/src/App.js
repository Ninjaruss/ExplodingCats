import React from 'react';
import './App.css';
import Emoji from 'a11y-react-emoji'

function App() {
  const [text,setText] = React.useState('')

  // const initialResponse = {
  //   _id: '',
  //   command: '',
  //   gameResponse: Game, 
  //   userResponse: User, 
  //   stringResponse: '', 
  //   jsonResponse: '',  
  //   responseCode: ''
  // }
  
  // function reducer(state, action) {
  //   switch(action.type) {
  //     case 'set':
  //       return {
  //         ...state, talks: action.talks
  //       }
  //     case 'add':
  //       return {
  //         ...state,
  //         talks: [
  //           ...state.talks, action.talk
  //         ]
  //       }
  //     case 'error':
  //       return {
  //         ...state, error: true
  //       }
  //     case 'updateInput':
  //       return {
  //         ...state,
  //         [action.inputValue]: action.value
  //       }
  //     default:
  //       new Error()
  //   }
  // }
  const ws = React.useRef(new WebSocket(`ws://${window.location.host}/ws`));
  ws.current.onopen = () => {
    console.log('Connection open!')
  };

  ws.current.onmessage = (message) => {
    console.log(message);
    setText(String(message.data));
  };

  ws.current.onclose = () => {
    console.log('connection closed');
  };

  ws.current.onerror = () => {
    console.log('ws error');
  };


  const handleClick = () => {
    ws.current.send(text);
  };

  return (
    <div className="welcome-page">
      <header className="App-header">
        <h1>Welcome to Exploding Kittens</h1>
        <Emoji label ="Exploding Kittens" symbol ="🙀💣💥"></Emoji>
        <h2>Enter Your Name Here:</h2>
        <input value={text} onChange={e => setText(e.target.value)} />
        <hr />
        <button onClick={handleClick}>Enter Game</button>
      </header>
    </div>
  );
}

export default App;
