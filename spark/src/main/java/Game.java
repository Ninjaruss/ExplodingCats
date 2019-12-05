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

    // Lobby status
    public enum Status{
        WAITING, IN_PROGRESS, FINISHED;
    }
    private Status status;

    // List of players with their corresponding hands
    private Map<User, ArrayList<CardObject>> players;

    private final Deck deck;
    private final PlayStack stack;
    private String winner;
    private boolean stillPlaying;
    private final int playerMin = 2;
    private final int playerMax = 4;

    public Game(){
        this.id = GAME_COUNT++;
        this.status = Status.WAITING;
        this.deck = new Deck().standard();
        this.stack = new PlayStack();
        this.stillPlaying = true;
        players = new ConcurrentHashMap<User, ArrayList<CardObject>>();
    }

    // Adds a new player to the game, giving them an empty hand
    public void addPlayer(User u){
        if (players.size() >= playerMax){
            throw new RuntimeException("Cannot add another player; game is full.");
        }

        players.put(u, new ArrayList<CardObject>());

        if (players.size() == playerMax){
            startGame();
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
        status = Status.IN_PROGRESS;

        deck.shuffle();
        for(ArrayList<CardObject> hand : players.values()) {
            for (int i = 0; i < 7; i++) {
                hand.add(deck.draw());
            }
            hand.add(new Defuse());
        }
        deck.addNewCard("defuse", 2);
        deck.shuffle();

        // Shuffle player order
        List<Map.Entry<User, ArrayList<CardObject>>> entries = new ArrayList<>(players.entrySet());
        Collections.shuffle(entries);
        Map<User, ArrayList<CardObject>> shuffledMap = new LinkedHashMap<>();
        for (Map.Entry<User, ArrayList<CardObject>> entry : entries) {
            shuffledMap.put(entry.getKey(), entry.getValue());
        }


        while (stillPlaying){

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
