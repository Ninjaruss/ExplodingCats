import static spark.Spark.*;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class SparkDemo {
  public static void main(String[] args) {
    port(1234);
    // calling get will make your app start listening for the GET path with the /hello endpoint
    get("/hello", (req, res) -> "Hello World");

    get("/api", (req, res) -> {
      System.out.println(req.queryMap().get("key").value());
      String value = req.queryMap().get("key").value();
      return "Hello " + value;
    });

    post("/postApi", (req, res) -> {
      System.out.println(req.body());
      NoteDto note = new NoteDto("oiergioergoij", "This is some note in mongo");
      NoteDto note2 = new NoteDto("oiergioergoij", "This is some more text in mongo");
      List<NoteDto> noteList = new ArrayList<>();
      noteList.add(note);
      noteList.add(note2);
      Gson gson = new Gson();
      return gson.toJson(noteList);
    });
  }
}
