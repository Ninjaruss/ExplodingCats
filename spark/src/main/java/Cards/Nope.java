package Cards;

import java.util.Stack;

public class Nope extends CardObject{
    public Nope(){
        name = "Nope";
        id = 3;
        desc = "Denies the last card played in the stack.";
    }

    public void activate(Stack<CardObject> stack){

    }
}
