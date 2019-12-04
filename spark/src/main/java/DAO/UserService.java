package Users;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UserService {
    private static UserService dataBase;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    BufferedReader br;
    MongoClient mongoClient = null;
    MongoDatabase db = null;
    MongoCollection<Document> userList = null;

    public static UserService getDataBase(){
        if (dataBase == null){
            dataBase = new UserService();
        }
        return dataBase;
    }

    private UserService(){
        try {
            // Initializes the Mongodb
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("MyDatabase");
            userList = db.getCollection("UserList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User addUser(String name){
        Date createdDate = new Date();
        Document doc = new Document("name", name)
                .append("date", createdDate);
        userList.insertOne(doc);

        // Return a note object for response check
        ObjectId id = (ObjectId)doc.get( "_id" );
        User user = new User(doc.getObjectId("_id").toString(),
                doc.getString("name"),
                doc.getDate("date"));
        return user;
    }

    public User getUser(String _id){
        Document search = userList.find(eq("_id", new ObjectId(_id) )).first();
        User foundUser = new User(search.getObjectId("_id").toString(),
                search.getString("name"),
                search.getDate("date"));
        return foundUser;
    }

    public User deleteUser(String _id){
        User foundUser = getUser(_id);
        userList.deleteOne(eq("_id", _id));
        return foundUser;
    }

    public ArrayList<User> getAllUsers(){
        MongoCursor<Document> cursor = userList.find().iterator();
        ArrayList<User> list = new ArrayList<User>();
        try {
            while (cursor.hasNext()) {
                Document current = cursor.next();
                //String noteJson = cursor.next().toJson();
                //NoteObject note = gson.fromJson(noteJson, NoteObject.class);
                User user = new User(
                        current.getObjectId("_id").toString(),
                        current.getString("name"),
                        current.getDate("date"));
                list.add(user);
            }
        } finally {
            cursor.close();
        }
        return list;
    }
}
