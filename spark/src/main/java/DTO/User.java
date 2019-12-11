package DTO;
import org.eclipse.jetty.websocket.api.Session;

import java.util.*;

public class User {
    public final String _id;
    public final String name;
    public final Date date;
    public final int wins;
    private Session session;

    public User(String _id, String name, Date date, int wins){
        this._id = _id;
        this.name = name;
        this.date = date;
        this.wins = wins;
    }

    public void setSession(Session session){
        this.session = session;
    }

    public Session getSession(){
        return this.session;
    }
}
