import React from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';

function App() {
  const [text, setText] = React.useState(''); // creates state variable, retuns tuple
  const [responseText, setResponseText] = React.useState('');

  const handleClick = () => {
    axios.get(`/api?key=${text}`) // promise
      .then((res) => {
        setResponseText(res.data);
      })
      .catch(console.log);
  };

  const testPost = () => {
    const body = {
      _id: 'abc',
      data: text,
    };
    axios.post('/postApi', body)
      .then(console.log)
      .catch(console.log);
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {responseText}
        </p>
        <input value={text} onChange={e => setText(e.target.value)} />
        <hr />
        <button onClick={handleClick}>Click Me</button>
        <button onClick={testPost}>Test Post</button>
      </header>
    </div>
  );
}

export default App;
