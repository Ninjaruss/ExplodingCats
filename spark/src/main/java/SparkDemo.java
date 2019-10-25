import static spark.Spark.*;

public class SparkDemo {
  public static void main(String[] args) {
    port(1234);
    // calling get will make your app start listening for the GET path with the /hello endpoint
    get("/hello", (req, res) -> "Hello World");

    get("/api", (req, res) -> {
      return "Hello from spark!";
    });
  }
}
