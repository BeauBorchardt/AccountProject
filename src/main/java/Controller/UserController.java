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
        app.post("/users", postHandler);
        app.put("/users/{username}", putHandler);
        app.delete("/users/{username}", deleteHandler);

    }

    public Handler getHandler = ctx -> {
        String username = ctx.pathParam("username");
        User user = usersDAO.getUser(username);
        ctx.json(user);
    };

    public Handler postHandler = ctx -> {

        User user = ctx.bodyAsClass(User.class);
        usersDAO.createUser(user);
        ctx.status(201);

    };

    public Handler putHandler = ctx -> {

        User user = ctx.bodyAsClass(User.class);
        usersDAO.updateUser(user);
        ctx.status(200);

    };

    public Handler deleteHandler = ctx -> {

        User user = ctx.bodyAsClass(User.class);
        usersDAO.deleteUser(user);
        ctx.status(200);

    };

}
