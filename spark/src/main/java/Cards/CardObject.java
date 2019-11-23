package Cards;

import java.util.Stack;

public class CardObject {
    public String name = "";
    public int id = 0;
    public String desc = "";

    // Need to replace "stack" with the "game", then use the game's stack
    public void activate(Stack<CardObject> stack){
        System.out.println("No activate() implemented.");
    }

    public void onDraw(){
        System.out.println("No onDraw() implemented.");
    }
}