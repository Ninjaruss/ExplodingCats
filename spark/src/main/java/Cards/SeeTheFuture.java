package Cards;

import GameObjects.*;
import java.util.Stack;

public class SeeTheFuture extends CardObject{
    public SeeTheFuture(){
        name = "See The Future";
        id = 8;
        desc = "Allows you to see the top 3 cards of the deck.";
    }

    public void activate(Game g){
        Deck deck = g.getDeck();
        String future = deck.peek(3);
        g.tellClient(g.getUser(playedUser), "SeeTheFuture", future, "User has seen the future.");
    }
}
