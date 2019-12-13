package DTO;

import java.util.*;

import Cards.CardObject;
import GameObjects.Game;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Response {
    private Date date;
    private String _id;
    private String responseCode;
    private String command;
    private User userResponse;
    private String stringResponse;
    private String stringResponse2;
    private CardObject cardResponse;
    private ArrayList<CardObject> handResponse;

    public static class Builder{
        private Date date = null;
        private String _id = null;
        private String responseCode = null;
        private String command = null;
        private User userResponse = null;
        private String stringResponse = null;
        private String stringResponse2 = null;
        private CardObject cardResponse = null;
        private ArrayList<CardObject> handResponse= null;

        // Sets a date when this object is created
        public Builder(){
            this.date = new Date();
        }

        public Builder set_id(String _id){
            this._id = _id;
            return this;
        }

        public Builder setCommand(String command){
            this.command = command;
            return this;
        }

        public Builder setUserResponse(User user){
            this.userResponse = user;
            return this;
        }

        public Builder setStringResponse(String str){
            this.stringResponse = str;
            return this;
        }

        public Builder setStringResponse2(String str){
            this.stringResponse2 = str;
            return this;
        }

        public Builder setCardResponse(CardObject card){
            this.cardResponse = card;
            return this;
        }

        public Builder setHandResponse(ArrayList<CardObject> hand){
            this.handResponse = hand;
            return this;
        }

        public Builder setCode(String responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Response build(){
            Response res = new Response();
            res.date = this.date;
            res._id = this._id;
            res.command = this.command;
            res.userResponse = this.userResponse;
            res.stringResponse = this.stringResponse;
            res.stringResponse2 = this.stringResponse2;
            res.cardResponse = this.cardResponse;
            res.handResponse = this.handResponse;
            res.responseCode = this.responseCode;
            return res;
        }
    }

    // Keeps response constructor private
    private Response(){

    }

    public String getCommand(){
        return this.command;
    }

    public String getStringResponse(){
        return this.stringResponse;
    }

    public String getStringResponse2(){
        return this.stringResponse2;
    }

    public User getUserResponse(){
        return this.userResponse;
    }

    public CardObject getCardResponse(){
        return this.cardResponse;
    }

    public ArrayList<CardObject> getHandResponse(){return this.handResponse;}

    public String getJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        if (json == null){
            return "null";
        }
        return json;
    }
}
