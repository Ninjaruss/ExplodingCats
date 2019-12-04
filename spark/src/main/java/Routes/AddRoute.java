package Routes;

import DTO.NoteObject;
import DTO.Response;

public class StoreRoute extends RouteObject{
    public StoreRoute(String body){
        try{
            // INSERT NOTE INTO DATABASE COMMAND
            NoteObject note = noteDatabase.storeNote(body);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(note._id)
                    .addResponse(note)
                    .setCode("Note stored successfully.")
                    .build();
        }
        catch(Exception e){
            e.printStackTrace();
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to store note.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
