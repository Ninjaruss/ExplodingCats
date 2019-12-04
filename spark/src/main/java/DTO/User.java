package DTO;

import java.util.*;

public class User {
    public final String _id;
    public final String name;
    public final Date date;

    public User(String _id, String name, Date date){
        this._id = _id;
        this.name = name;
        this.date = date;
    }
}
