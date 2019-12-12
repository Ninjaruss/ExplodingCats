/**DrawPile is just UI, 
 * when clicked, draws a card from GET /deck 
 * and POST it in /hand. Turn ends when card drawn unless exploding kitten.
 * TODO: 1. figure out how see the future is handled here
 .
 */
function drawPile(){

    // const endTurn =()=> {
    //   useEffect(() => {
    //    if (shouldExecute) {
    //     endTurn()
    //    }
    //   }, [shouldExecute])
<Button 
    fullWidth
    color="secondary"
    variant="outlined"
  //  onClick={endTurn}
>
    Draw Card
</Button>
}