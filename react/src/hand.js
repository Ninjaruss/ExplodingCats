/** Player's hand can remove card from it when card is used.
 * play card by clicking card. send card to playStack. can play multiple cards in one turn
 * fills empty card components
 * 
 */

import KittenCard from "./KittenCard";
import CardGroup from 'react-bootstrap/CardGroup'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';

function hand(props){

return(
  <>
    <CardGroup>
     <KittenCard></KittenCard>
     <KittenCard></KittenCard>
     <KittenCard></KittenCard>
     <KittenCard></KittenCard>
     <KittenCard></KittenCard>
     <KittenCard></KittenCard>
     </CardGroup>
    )}
  </>
); 
}
export default hand;