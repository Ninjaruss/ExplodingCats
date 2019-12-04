package Routes;

import DTO.NoteObject;
import DTO.Response;

public class DeleteRoute extends RouteObject{
    public DeleteRoute(String _id) {
        try {
            // INSERT NOTE INTO DATABASE COMMAND
            NoteObject note = noteDatabase.deleteNote(_id);

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            localRes = new Response.Builder()
                    .set_id(note._id)
                    .addResponse(note)
                    .setCode("Note deleted successfully.")
                    .build();
        } catch (Exception e) {
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to delete note.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
