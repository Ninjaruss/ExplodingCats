package Cards;

import GameObjects.*;
import java.util.*;

public class Favor extends CardObject{
    public Favor(){
        name = "Favor";
        id = 6;
        desc = "Forces another player to give you a card.";
    }

    public void activate(Game g){
        ArrayList<CardObject> userHand = g.getHand(playedUser);
        ArrayList<CardObject> targetHand = g.getHand(targetUser);
        Random rand = new Random();
        CardObject card = targetHand.get(rand.nextInt(targetHand.size()));
        targetHand.remove(card);
        userHand.add(card);
        g.updateHand(g.getUser(playedUser));
        g.tellClient(g.getUser(playedUser), "StoleCard", card.name, "User has stolen a card.");
        g.tellClient(g.getUser(targetUser), "CardStolen", card.name, "User has lost a card to Favor.");
        g.tellAllClients("cardActivated", this, "Favor activated.");
    }
}