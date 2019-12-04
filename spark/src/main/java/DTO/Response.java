package DTO;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Response {
    private Date date;
    private String _id;
    private String responseCode;
    private ArrayList<NoteObject> response;

    public static class Builder{
        private Date date = null;
        private String _id = "null";
        private String responseCode = "ERROR";
        private ArrayList<NoteObject> response = new ArrayList<NoteObject>();

        // Sets a date when this object is created
        public Builder(){
            this.date = new Date();
        }

        public Builder set_id(String _id){
            this._id = _id;
            return this;
        }

        public Builder addResponse(NoteObject note){
            this.response.add(note);
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
            res.response = this.response;
            res.responseCode = this.responseCode;
            return res;
        }
    }

    // Keeps response constructor private
    private Response(){

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
