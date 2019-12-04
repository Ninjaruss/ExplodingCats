package GameObjects;

import Cards.*;
import java.util.*;

public class PlayStack {
    Stack<CardObject> stack = new Stack<CardObject>();

    public void execute(){
        while(!stack.isEmpty()){
            CardObject currentCard = stack.pop();
            currentCard.activate(stack);
        }
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
