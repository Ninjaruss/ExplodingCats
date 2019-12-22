import React from 'react';
import './App.css';
import Card from './Card'
import './Card.css'

export const wsSession = new WebSocket(`ws://localhost:1234/ws`);

function App() {
  const [username,setUsername] = React.useState("")
  //const ws = React.useRef(new WebSocket(`ws://${window.location.host}/ws`));
  const ws = React.useRef(wsSession);
  const t = "ahhhh";

 
  const userInfo=(event)=> {
    if (event.keyCode === 13) {
      console.log('enter')
    }
  }

  const userResponse={
    command:"connectUser",
    stringResponse: username 
  }
  
  ws.current.onopen = () => {
    console.log('Connection open!')
  };

  ws.current.onclose = () => {
    console.log('connection closed');
  };

  ws.current.onerror = () => {
    console.log('ws error');
  };
  
  const connectUser = (e) =>{
	e.preventDefault();
	console.log('connectUser called');
  	ws.current.send(JSON.stringify(userResponse));
  }

  return (
    <div className="welcome-page">
      <header className="App-header">Welcome to Exploding Kittens</header>
   
    <div className="Name">
        <h1>Enter your name</h1>
        <input  type='text' onKeyDown={(e) => userInfo(e) }/>
    </div>

	{/* <form onSubmit = {connectUser}>
	  <label> Username
			<input type = "text" name = "username" onChange={e => setUsername(e.target.value)} />
	  </label>
	</form> */}
      <body className="body">
        <Card></Card>
       </body>

    </div>
  );
}

export default App;
