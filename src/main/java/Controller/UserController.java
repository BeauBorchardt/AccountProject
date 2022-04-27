package Controller;

import AccountDaoPkg.UsersDAO;
import AccountModelPkg.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController {

    UsersDAO usersDAO;

    public UserController(Javalin app) {
        usersDAO = new UsersDAO();
        app.get("/users/{username}", getHandler);

    }

    public Handler getHandler = ctx -> {
        String username = ctx.pathParam("username");
        User user = usersDAO.getUser(username);
        ctx.json(user);
    };

}
