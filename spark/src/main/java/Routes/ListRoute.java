package Routes;

import DTO.*;

import java.util.ArrayList;
import DTO.*;

public class ListRoute extends RouteObject{
    public ListRoute(){
        try{
            // FETCH NOTES AS ARRAY LIST COMMAND
            ArrayList<User> fetchedData = userDataBase.getAllUsers();

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            Response.Builder localResBuild = new Response.Builder();
            for (User user : fetchedData){
                localResBuild.addResponse(user);
            }
            localResBuild.setCode("Fetched all users successfully.");
            localRes = localResBuild.build();
        }
        catch (Exception e){
            //RETURN FAILED RESPONSE
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to fetch all users.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
