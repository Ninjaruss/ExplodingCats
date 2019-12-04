import GameObjects.*;
import Cards.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import DTO.*;


public class Game{
    // Counter for all games created after initialization
    private static int GAME_COUNT = 0;

    // This game's identifier
    private final int id;

    public enum Status{
        WAITING, IN_PROGRESS, FINISHED;
    }
    private Status status;

    // List of players with their corresponding hands
    private Map<User, ArrayList<CardObject>> players;

    private final Deck deck;
    private final PlayStack stack;
    private final int playerMin = 2;
    private final int playerMax = 4;
    private String winner;

    public Game(){
        this.id = GAME_COUNT++;
        this.deck = new Deck().standard();
        this.stack = new PlayStack();
        players = new ConcurrentHashMap<User, ArrayList<CardObject>>();
    }

    // Adds a new player to the game, giving them an empty hand
    public void addPlayer(User u){
        if (players.size() >= playerMax){
            throw new RuntimeException("Cannot add another player; game is full.");
        }

        players.put(u, new ArrayList<CardObject>());

        if (players.size() == playerMax){
            status = Status.IN_PROGRESS;
        }
    }

    private void setStatus(){
        if (winner != null){
            status = Status.FINISHED;
        }
    }

    // Game loop //////////////////////////////
    public void startGame(){
        if (players.size() < playerMin){
            throw new RuntimeException("Not enough players to start the game.");
        }

    }

    // Read-only functions ////////////////////////////
    public Deck getDeck(){
        return deck;
    }

    public PlayStack getStack(){
        return stack;
    }

    public Status getStatus(){
        return status;
    }
}
