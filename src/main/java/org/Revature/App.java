package org.Revature;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import AccountModelPkg.*;
import AccountDaoPkg.*;

public class App 
{

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        System.out.println("Helloooooo!");
        UsersDAO userDao = new UsersImp();
        User u = userDao.getUser("SuperMario");

        System.out.println("Username: " + u.username);
        System.out.println("password: " + u.password);
        System.out.println("Access Level: " + u.accessLevel);

    }


}
