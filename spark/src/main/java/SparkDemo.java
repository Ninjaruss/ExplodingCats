import static spark.Spark.*;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class SparkDemo {
  public static void main(String[] args) {
    port(1234);
    webSocket("/ws", WebSocketHandler.class);

    // calling get will make your app start listening for the GET path with the /hello endpoint
    get("/hello", (req, res) -> "Hello World");

    get("/api", (req, res) -> {
      System.out.println(req.queryMap().get("key").value());
      String value = req.queryMap().get("key").value();
      return "Hello " + value;
    });

    post("/postApi", (req, res) -> {
      //Gson gson = new Gson();
      //return gson.toJson(noteList);
      return null;
    });


  }
}
