package DTO;

import java.util.*;

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
    private JsonObject jsonResponse;
    private List<User> userListResponse;

    public static class Builder{
        private Date date = null;
        private String _id = null;
        private String responseCode = null;
        private String command = null;
        private User userResponse = null;
        private String stringResponse = null;
        private JsonObject jsonResponse = null;
        private List<User> userListResponse = null;

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

        public Builder addToUserListResponse(User user){
            this.userListResponse.add(user);
            return this;
        }

        public Builder setUserListResponse(List<User> users){
            this.userListResponse = users;
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

        public Builder setJsonResponse(JsonObject json){
            this.jsonResponse = json;
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
            res.userListResponse = this.userListResponse;
            res.stringResponse = this.stringResponse;
            res.jsonResponse = this.jsonResponse;
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

    public List<User> getUserListResponse(){
        return this.userListResponse;
    }

    public String getStringResponse(){
        return this.stringResponse;
    }

    public User getUserResponse(){
        return this.userResponse;
    }

    public JsonObject getJsonResponse(){
        return this.jsonResponse;
    }

    public String getJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        if (json == null){
            return "null";
        }
        return json;
    }
}
