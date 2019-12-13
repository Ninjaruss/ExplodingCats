package Cards;

import GameObjects.*;
import java.util.Stack;

public class Skip extends CardObject{
    public Skip(){
        name = "Skip";
        id = 5;
        desc = "Ends current player's turn without drawing a card.";
    }

    public void activate(Game g){
        g.activateSkip();
        g.tellAllClients("cardActivated", this, "Skip activated.");
    }
}