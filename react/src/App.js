import React from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';

function App() {
  const [text, setText] = React.useState(''); // creates state variable, retuns tuple

  const handleClick = () => {
    axios.get('/api')
      .then(console.log)
      .catch(console.log)
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {text}
        </p>
        <input value={text} onChange={e => setText(e.target.value)} />
        <hr />
        <button onClick={handleClick}>Click Me</button>
      </header>
    </div>
  );
}

export default App;
