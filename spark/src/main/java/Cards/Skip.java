package Cards;

import java.util.Stack;

public class Skip extends CardObject{
    public Skip(){
        name = "Skip";
        id = 5;
        desc = "Ends your turn without drawing a card.";
    }

    public void activate(Stack<CardObject> stack){

    }
}