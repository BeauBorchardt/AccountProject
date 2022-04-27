package org.Revature;

import Controller.UserController;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import AccountModelPkg.*;
import AccountDaoPkg.*;

public class App 
{

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        Javalin app = Javalin.create().start(7072);
        UserController userController = new UserController(app);

        UsersInterface userDao = new UsersDAO();
        User u = userDao.getUser("SuperMario");

        EmployeeInterface employeeDAO = new EmployeeDAO();
        Employee e = employeeDAO.getEmployee(6);

        System.out.println("Customer Username: " + u.username);
        System.out.println("password: " + u.password);
        System.out.println("Access Level: " + u.accessLevel);

    }


}
