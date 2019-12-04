package Routes;

import DTO.Response;

public class ErrorRoute extends RouteObject{
    public ErrorRoute(){
        localRes = new Response.Builder()
                .setCode("ERROR: Route error; check route command.")
                .build();
    }
    public Response create(){
        return localRes;
    }
}
