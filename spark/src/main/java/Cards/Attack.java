package Cards;

import GameObjects.Game;

import java.util.Stack;

public class Attack extends CardObject{
    public Attack(){
        name = "Attack";
        id = 4;
        desc = "Force other player to draw.";
    }

    public void activate(Game g){
        CardObject drawnCard = g.drawFromDeck(targetUser);
        g.tellAllClients("cardActivated", this, "Attack activated.");
    }
}
