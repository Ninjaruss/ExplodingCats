package Routes;

import DTO.*;

public class GetRoute extends RouteObject{
    public GetRoute(String name) {
        try {
            // INSERT NOTE INTO DATABASE COMMAND
            User user = userDataBase.getUser(name);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(user._id)
                    .setUserResponse(user)
                    .setCode("Fetched user successfully.")
                    .build();
        } catch (Exception e) {
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to fetch user.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
