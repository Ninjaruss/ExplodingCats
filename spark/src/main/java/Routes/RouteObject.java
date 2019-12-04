package Routes;

import DAO.NoteHandler;
import DTO.Response;

public class RouteObject {
    public Response localRes;
    protected NoteHandler noteDatabase = NoteHandler.getDataBase();

    public Response create(){
        return null;
    }
}
