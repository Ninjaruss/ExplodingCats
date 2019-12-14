package Cards;

import GameObjects.*;
import java.util.Stack;

public class Nope extends CardObject{
    public Nope(){
        name = "Nope";
        id = 3;
        desc = "Denies the last card played in the stack.";
    }

    public void activate(Game g){
        // Removes the card on the top of the stack (under this card)
        PlayStack stack = g.getStack();
        stack.pop();
        g.tellAllClients("cardActivated", this, "Nope activated.");

        // g.tellClient(g.getUser(playedUser), "Nope", card.name, "User has stolen a card.");
    }
}
