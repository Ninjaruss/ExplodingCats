import React from 'react';
import './App.css';
import Emoji from 'a11y-react-emoji'

function App() {

  return (
    <div className="welcome-page">
      <header className="App-header">
        <h1>Welcome to Exploding Kittens</h1>
        <Emoji label ="Exploding Kittens" symbol ="🙀💣💥"></Emoji>
        <h2>Enter Your Name Here:</h2>
        <p>
          {responseText}
        </p>
        <input value={text} onChange={e => setText(e.target.value)} />
        <hr />
        <button onClick={handleClick}>Enter Game</button>
      </header>
    </div>
  );
}

export default App;
