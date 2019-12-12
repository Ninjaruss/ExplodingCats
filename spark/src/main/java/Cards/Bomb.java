package Cards;

import GameObjects.Game;

import java.util.ArrayList;
import java.util.Iterator;

public class Bomb extends CardObject{
    public Bomb(){
        name = "Bomb";
        id = 1;
        desc = "When this card is drawn, you lose if no defuse card is played.";
    }

    public void onDraw(Game g){
        ArrayList<CardObject> hand = g.getHand(playedUser);
        Iterator<CardObject> i = hand.iterator();
        while (i.hasNext()){
            CardObject card = i.next();
            if (card.name == "defuse"){
                i.remove();
                hand.remove(hand.size()-1);
                return;
            }
        }
        g.playerDefeat(playedUser);
    }
}
