package GameObjects;

import Cards.*;
import java.util.*;

public class PlayStack {
    Stack<CardObject> stack = new Stack<CardObject>();
    Game currentGame;
    boolean isExecuting;

    public void execute(){
        while(!stack.isEmpty()){
            CardObject currentCard = stack.pop();
            currentCard.activate(currentGame);
            currentGame.activateCard(currentCard.name);
        }
        isExecuting = false;
    }

    public void push(CardObject card){
        stack.push(card);
    }

    public void pop(){
        stack.pop();
    }

    public void flush(){
        stack.clear();
    }
}
