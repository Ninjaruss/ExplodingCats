import static spark.Spark.*;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import Routes.*;

public class SparkDemo {
  public static void main(String[] args) {
    // Starts up a server and links the WebSocketHandler class
    port(1234);
    webSocket("/ws", WebSocketHandler.class);


    // API DEBUGGING //
    get("/get", (req, res) -> {
      RouteObject route = new GetRoute(req.queryParams("name"));
      return route.localRes.getJson();
    });

    post("/delete", (req, res) ->{
      RouteObject route = new DeleteRoute(req.queryParams("name"));
      return route.localRes.getJson();
    });

    /*
    get("/list", (req, res) ->{
      RouteObject route = new ListRoute();
      System.out.println(route.localRes.getJson());
      return route.localRes.getJson();
    });
     */

    post("/add", (req, res) ->{
      RouteObject route = new AddRoute(req.body());
      System.out.println(route.localRes.getJson());
      return route.localRes.getJson();
    });


    get("/api", (req, res) -> {
      System.out.println(req.queryMap().get("key").value());
      String value = req.queryMap().get("key").value();
      return "Hello " + value;
    });

    /*
    post("/postApi", (req, res) -> {
      Gson gson = new Gson();
      return gson.toJson(noteList);
      return null;
    });
    */

  }
}
