package Routes;

import DTO.*;

public class AddRoute extends RouteObject{
    public AddRoute(String name){
        try{
            // INSERT NOTE INTO DATABASE COMMAND
            User user = userDataBase.addUser(name);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(user._id)
                    .addResponse(user)
                    .setCode("User added successfully.")
                    .build();
        }
        catch(Exception e){
            e.printStackTrace();
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to add user.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
