package Routes;

import DAO.UserService;
import DTO.Response;

public class RouteObject {
    public Response localRes;
    protected UserService userDataBase = UserService.getDataBase();

    public Response create(){
        return null;
    }
}
