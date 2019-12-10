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
  const [card, setCard] = useState(null)
  const [executing, setExecuting] = useState(false)
  const [hasError, setHasError] = useState(false)
  

  const handleRemoveCard = name => {
    updateList(list.filter(item => card.name !== cardName))
  }

  if (shouldExecute) {
    setExecuting(true)
  }

  const getCardFromDeck = async () => {
    try {
      const drawnCard = await axios.get("/deck")
      setCard(card)
    } catch (error) {
      setHasError(true)
    }

    setExecuting(false)
  }

  useEffect(() => {
    if (shouldExecute) {
      getCardFromDeck()
    }
  }, [shouldExecute])

return(
  <>
    <CardGroup>
     <ul>
     <KittenCard></KittenCard>
     </ul>
      </CardGroup>
    )}
  </>
); 
}