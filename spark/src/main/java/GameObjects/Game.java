package DTO;

import GameObjects.*;
import Cards.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Game{
    // Counter for all games created after initialization
    private static int GAME_COUNT = 0;

    // This game's identifier
    public final int id;

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

    public void removePlayer(String username){
        for (Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            if (entry.getKey().name == username){
                players.remove(entry);
            }
        }
    }

    private void setStatus(){
        if (winner != null){
            status = Status.FINISHED;
        }
    }

    // DTO.Game loop //////////////////////////////
    public void startGame(){
        if (players.size() < playerMin){
            throw new RuntimeException("Not enough players to start the game.");
        }
        status = Status.IN_PROGRESS;

        deck.shuffle();
        for(Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()) {
            for (int i = 0; i < 7; i++) {
                entry.getValue().add(deck.draw());
            }
            entry.getValue().add(new Defuse());
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
            // play first player's turn

            // wait until player makes move or timer ends

            // place card in stack if card is played, allow other players to play cards on top
            // execute stack

            // draw card, explode if bomb (defuse bomb if defuse present)
            // check if only one player is left, if yes then end the game

            // go to next player's turn
        }
    }

    public void playTurn(User u){

    }

    public List<CardObject> getHand(String username){
        for(Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            if (entry.getKey().name == username){
                return entry.getValue();
            }
        }
        return null;
    }

    // Read-only functions ////////////////////////////

    public List<User> getPlayerList(){
        List<User> playerList = new ArrayList<User>();
        for (Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            playerList.add(entry.getKey());
        }
        return playerList;
    }

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
