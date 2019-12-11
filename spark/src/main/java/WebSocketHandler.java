import GameObjects.Game;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import DTO.*;
import DAO.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebSocket
public class WebSocketHandler {
    // Store sessions if you want to, for example, broadcast a message to all users
    static Map<Session, Session> sessionMap = new ConcurrentHashMap<>();

    // Map of users who are logged in
    static Map<Session, User> userMap = new ConcurrentHashMap<>();

    // Map of games being hosted
    static Map<Integer, Game> gameList = new ConcurrentHashMap<>();

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static UserService userService = UserService.getDataBase();
    static String clickCountString = "0";

    public static void broadcastToAllUsers(String message) {
        sessionMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(message); // server to client
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void broadcastToGame(Integer id, String message) {
        for (Map.Entry<Integer, Game> entry : gameList.entrySet()){
            if (entry.getValue().id == id){
                List<User> userList = entry.getValue().getPlayerList();
                for (User u : userList){
                    try {
                        u.getSession().getRemote().sendString(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void hostGame(User u){
        Game game = new Game();
        game.addPlayer(u);
        gameList.put(game.id, game);
    }

    // Look for an open game to join, if none found make new game
    public static void findMatch(User u){
        for (Map.Entry<Integer, Game> entry : gameList.entrySet()){
            if (entry.getValue().getStatus() == Game.Status.WAITING){
                entry.getValue().addPlayer(u);

                Response newResponse = new Response.Builder()
                        .setCommand("foundGame")
                        .setGameResponse(entry.getValue())
                        .setCode("User has found and joined a game.")
                        .build();
                try {
                    u.getSession().getRemote().sendString(gson.toJson(newResponse));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        hostGame(u);
    }

    public static void startGame(Integer id){
        for (Map.Entry<Integer, Game> entry : gameList.entrySet()){
            if (entry.getKey() == id){
                entry.getValue().startGame();
            }
        }
    }

    public static void playCard(Integer id){

    }

    @OnWebSocketConnect
    public void connected(Session session) throws IOException {
        System.out.println("A client has connected");
        sessionMap.put(session, session);
        session.getRemote().sendString(clickCountString); // and send it back
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        System.out.println("A client has disconnected");
        sessionMap.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        Response response = gson.fromJson(message, Response.class);

        // connectUser("username") - Create new User if username isn't in the database, returns a user
        if (response.getCommand() == "connectUser"){
            User u = userService.getUser(response.getStringResponse());
            Response.Builder newResponse = new Response.Builder();

            if (u != null){
                        newResponse.setCode("User with name found! Data fetched.");
            } else {
                u = userService.addUser(response.getStringResponse());
                newResponse.setCode("New user created!");
            }

            u.setSession(session); // Sets the user's session
            userMap.put(session, u); // Binds session to user

            newResponse.setUserResponse(u);
            newResponse.setCommand("userConnected");
            Response finalResponse = newResponse.build();

            // Return a response message to the client
            session.getRemote().sendString(gson.toJson(finalResponse));
            findMatch(u);
        }

        // playCard(GameObjects.Game id, String cardname)
    }
}