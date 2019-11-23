package Cards;

import java.util.Stack;

public class Attack extends CardObject{
    public Attack(){
        name = "Attack";
        id = 4;
        desc = "Ends turn (w/ no draw), " +
                "then forces next player to take two turns.";
    }

    public void activate(Stack<CardObject> stack){

    }
}
