package Routes;

import DTO.NoteObject;
import DTO.Response;

import java.util.ArrayList;

public class ListRoute extends RouteObject{
    public ListRoute(){
        try{
            // FETCH NOTES AS ARRAY LIST COMMAND
            ArrayList<NoteObject> fetchedData = noteDatabase.listNotes();

            // IF DONE SUCCESSFULLY RETURN SUCCESSFUL RESPONSE
            Response.Builder localResBuild = new Response.Builder();
            for (NoteObject note : fetchedData){
                localResBuild.addResponse(note);
            }
            localResBuild.setCode("Fetched notes successfully.");
            localRes = localResBuild.build();
        }
        catch (Exception e){
            //RETURN FAILED RESPONSE
            localRes = new Response.Builder()
                    .setCode("ERROR: Failed to fetch notes.")
                    .build();
        }
    }

    public Response create(){
        return localRes;
    }
}
