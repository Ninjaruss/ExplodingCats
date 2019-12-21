import { useState } from 'react';
import {wsSession} from './App';
const [cardObject,setCardObject] =useState({
    command:"getPLayStack",
    name:"",
    desc:""
})
const ws = React.useRef(wsSession);
ws.current.onmessage = (message) => {
 console.log(message);
 setCardObject(String(message.data));
}
(
    <div className = "play-stack">
          <h1>{cardObject.name}</h1>
          <h2>{cardObject.desc}</h2>
    </div>
)

export default playStack;