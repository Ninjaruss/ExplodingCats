package Routes;

import DTO.NoteObject;
import DTO.Response;

public class GetRoute extends RouteObject{
    public GetRoute(String _id) {
        try {
            // INSERT NOTE INTO DATABASE COMMAND
            NoteObject note = noteDatabase.getNote(_id);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(note._id)
                    .addResponse(note)
                    .setCode("Fetched note successfully.")
                    .build();
        } catch (Exception e) {
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to fetch note.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
