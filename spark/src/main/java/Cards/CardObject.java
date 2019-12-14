package Cards;

import GameObjects.Game;
import java.util.Stack;

public class CardObject {
    public String name = "";
    public int id = 0;
    public String desc = "";
    public String playedUser = "";
    public String targetUser = "";

    // Need to replace "stack" with the "game", then use the game's stack
    public void activate(Game g){
        System.out.println("No activate() implemented.");
    }

    public void onDraw(Game g){
        System.out.println("No onDraw() implemented.");
    }


}