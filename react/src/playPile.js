//This component contains cards that have been played from the hand.
/*TODO: 
1.figure out what happens when a card is noped from the playstack
*/

import KittenCard from "./KittenCard";
import CardGroup from 'react-bootstrap/CardGroup'
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';

function playPile(){
    const [playCard, setPlayCard] = useState(null)
    const [executing, setExecuting] = useState(false)
    const [hasError, setHasError] = useState(false)
    
    if (shouldExecute) {
      setExecuting(true)
    }
  
    const getCardFromPlayStack = async () => {
      try {
        const playedCard = await axios.get("/playStack")
        setPlayCard(playCard)
      } catch (error) {
        setHasError(true)
      }
  
      setExecuting(false)
    }
  
    useEffect(() => {
      if (shouldExecute) {
        getCardFromPlayStack()
      }
    }, [shouldExecute])
  
return(   
    <Button
        fullWidth
        color="secondary"
        variant="outlined"
        onClick={handleNope}>
        playStack
    </Button>
)
}
export default playPile;