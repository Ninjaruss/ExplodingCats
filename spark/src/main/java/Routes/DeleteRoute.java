package Routes;

import DTO.*;

public class DeleteRoute extends RouteObject{
    public DeleteRoute(String name) {
        try {
            // INSERT NOTE INTO DATABASE COMMAND
            User user = userDataBase.deleteUser(name);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(user._id)
                    .setUserResponse(user)
                    .setCode("User deleted successfully.")
                    .build();
        } catch (Exception e) {
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to delete user.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
