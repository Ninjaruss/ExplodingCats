package GameObjects;

import DAO.UserService;
import DTO.Response;
import DTO.User;
import Cards.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
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

    static UserService userService = UserService.getDataBase();

    private final Deck deck;
    private final PlayStack stack;
    private String winner;
    private boolean stillPlaying;
    private final int playerMin = 2;
    private final int playerMax = 4;
    private String currentPlayer;
    private boolean waitingInput;
    private boolean playWindow;
    private boolean skipActivated;
    private Thread thread;

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Game(){
        this.id = GAME_COUNT++;
        this.status = Status.WAITING;
        this.deck = new Deck().standard();
        deck.currentGame = this;
        this.stack = new PlayStack();
        stack.currentGame = this;
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
            if (entry.getKey().name.equals(username)){
                players.remove(entry);
                checkEmpty();
            }
        }
    }

    private void setStatus(){
        if (winner != null){
            status = Status.FINISHED;
        }
    }

    // GameObjects.Game loop //////////////////////////////
    public void startGame(){
        if (players.size() < playerMin){
            throw new RuntimeException("Not enough players to start the game.");
        }
        status = Status.IN_PROGRESS;

        // Set up the cards for each player in the game
        deck.shuffle();
        for(Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()) {
            for (int i = 0; i < 7; i++) {
                CardObject card = deck.draw();
                entry.getValue().add(card);
                tellClient(entry.getKey(), "cardDrawn", card, "Card has been drawn.");
                updateHand(entry.getKey());
            }
            entry.getValue().add(new Defuse());
            updateHand(entry.getKey());
        }
        deck.addNewCard("defuse", 2);
        deck.shuffle();

        // Shuffle player order
        /*
        List<Map.Entry<User, ArrayList<CardObject>>> entries = new ArrayList<>(players.entrySet());
        Collections.shuffle(entries);
        Map<User, ArrayList<CardObject>> shuffledMap = new LinkedHashMap<>();
        for (Map.Entry<User, ArrayList<CardObject>> entry : entries) {
            shuffledMap.put(entry.getKey(), entry.getValue());
        }
        */

        while (stillPlaying){
            for (Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
                // play first player's turn
                User u = entry.getKey();
                currentPlayer = u.name;
                playTurn(u);
                skipActivated = false;

                // wait until player makes move or timer ends
                int timeLeft = 60;
                waitingInput = true;
                while (waitingInput){
                    try {
                        thread.sleep(1000);
                        timeLeft += -1;
                        if (timeLeft == 0){
                            waitingInput = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // allow other players to play cards on top
                playCounterTurn();
                timeLeft = 30;
                playWindow = true;
                while (playWindow){
                    try {
                        thread.sleep(1000);
                        timeLeft += -1;
                        if (timeLeft == 0){
                            playWindow = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // execute stack
                stack.execute();
                stack.isExecuting = true;
                while (stack.isExecuting = true){
                    try {
                        thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // draw card, explode if bomb (defuse bomb if defuse present)
                if (skipActivated = false){
                    CardObject drawnCard = deck.draw();
                    if (drawnCard.name.equals("bomb")){
                        drawnCard.playedUser = u.name;
                    }
                    getHand(u.name).add(drawnCard);
                    drawnCard.onDraw(this);
                }

                // check if only one player is left, if yes then end the game
                if (players.keySet().size() == 1){
                    for (Map.Entry<User, ArrayList<CardObject>> entry2 : players.entrySet()){
                        winGame(entry2.getKey().name);
                        return;
                    }
                }
                // go to next player's turn
                currentPlayer = "";
            }
        }
    }

    public void winGame(String username){
        User u = getUser(username);
        if (u != null){
            // update win
            tellClient(u, "youWin", "", "You won the game.");
            userService.addWin(username);
        }
    }

    // Close empty game lobby
    public void checkEmpty(){
        if (players.size() == 0){
            status = Status.FINISHED;
        }
    }

    public void playerDefeat(String username){
        tellAllClients("playerLost", username, "A player has lost.");
        User u = getUser(username);
        tellClient(u, "youLose", username,"You lost!");
        removePlayer(username);
    }

    public void playTurn(User u){
        if (u.name.equals(currentPlayer)){
            tellClient(u, "playTurn", "", "Your turn.");
        }
    }

    public void playCounterTurn(){
        tellAllClients("playCounterTurn", "", "You can play a card.");
    }

    public void updateHand(User u){ tellClient(u, "updateHand", getHandArray(u.name), "Update hand.");}

    public void activateSkip(){
        skipActivated = true;
    }

    public CardObject drawFromDeck(String userName){
        CardObject card = deck.draw();
        if (card.name.equals("bomb")){
            card.playedUser = userName;
        }
        getHand(userName).add(card);
        card.onDraw(this);
        updateHand(getUser(userName));
        return card;
    }

    public void passTurn(User u){
        if (waitingInput){
            waitingInput = false;
        }
    }

    public void playCard(User u, int i, String target){
        ArrayList<CardObject> hand = getHand(u.name);
        CardObject card = hand.remove(i);
        card.playedUser = u.name;
        if (card.name.equals("Favor") || card.name.equals("Attack")){
            card.targetUser = target;
        }
        stack.push(card);
        tellAllClients("cardPlayed", card.name, "Card was played.");
        if (waitingInput){
            waitingInput = false;
        }
        if (playWindow){
            playWindow = false;
        }
        updateHand(u);
    }

    public void activateCard(String cardName){
        tellAllClients("cardActivated", cardName, "Card was activated.");
    }

    public void tellClient(User u, String command, String body, String code){
        Response response = new Response.Builder()
                .setCommand(command)
                .setStringResponse(body)
                .setCode(code)
                .build();
        try {
            u.getSession().getRemote().sendString(gson.toJson(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tellClient(User u, String command, CardObject card, String code){
        Response response = new Response.Builder()
                .setCommand(command)
                .setCardResponse(card)
                .setCode(code)
                .build();
        try {
            u.getSession().getRemote().sendString(gson.toJson(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tellClient(User u, String command, CardObject[] hand, String code){
        Response response = new Response.Builder()
                .setCommand(command)
                .setHandResponse(hand)
                .setCode(code)
                .build();
        try {
            u.getSession().getRemote().sendString(gson.toJson(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tellAllClients(String command, String body, String code){
        Response response = new Response.Builder()
                .setCommand(command)
                .setStringResponse(body)
                .setCode(code)
                .build();
        for (Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            try {
                entry.getKey().getSession().getRemote().sendString(gson.toJson(response));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void tellAllClients(String command, CardObject card, String code){
        Response response = new Response.Builder()
                .setCommand(command)
                .setCardResponse(card)
                .setCode(code)
                .build();
        for (Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            try {
                entry.getKey().getSession().getRemote().sendString(gson.toJson(response));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Read-only functions ////////////////////////////
    public List<User> getPlayerList(){
        List<User> playerList = new ArrayList<User>();
        for (Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            playerList.add(entry.getKey());
        }
        return playerList;
    }

    public User getUser(String username){
        for(Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()) {
            if (entry.getKey().name.equals(username)){
                return entry.getKey();
            }
        }
        return null;
    }

    public ArrayList<CardObject> getHand(String username){
        for(Map.Entry<User, ArrayList<CardObject>> entry : players.entrySet()){
            if (entry.getKey().name.equals(username)){
                return entry.getValue();
            }
        }
        return null;
    }

    public CardObject[] getHandArray(String username){
        ArrayList<CardObject> hand = getHand(username);
        CardObject[] array = hand.toArray(new CardObject[0]);
        return array;
    }

    public String getCurrentPlayer(){
        return currentPlayer;
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
