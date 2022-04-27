package org.Revature;

import Controller.UserController;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import AccountModelPkg.*;
import AccountDaoPkg.*;
import java.util.Scanner;

public class App 
{

    private static final Logger logger = LogManager.getLogger(App.class);
    static Scanner scan = new Scanner(System.in);
    private static int menuChoice = 0;
    private static String username;
    private static String password;

    private static int accessLevel;

    private static UsersInterface userDao = new UsersDAO();
    private static CustomerInterface customerDao = new CustomerDAO();

    private static EmployeeInterface employeeDao = new EmployeeDAO();
    static User u;
    static Customer c;

    static Employee e;


    public static void main( String[] args )
    {
        Javalin app = Javalin.create().start(7079);
        UserController userController = new UserController(app);

        topMenu();
        if(menuChoice == 1){
            accountLogin();
        } else if (menuChoice == 2){
            signUpMenu();
        }
        u = userDao.getUser(username);

        if(u.accessLevel == 1){
            c = customerDao.getCustomer(u.userId);
            customerMenu();
            if(menuChoice == 1){
                customerInfo();
            } else if (menuChoice == 2){
                customerAccount();
            }
        } else if (u.accessLevel == 2){
            e = employeeDao.getEmployee(u.userId);
            employeeMenu();
        } else if (u.accessLevel == 3){
            e = employeeDao.getEmployee(u.userId);
            adminMenu();
        }


    }

    public static void topMenu(){
        //Top menu that loads when application starts
        System.out.println("Welcome to the Banking app.");
        System.out.println("1. Go to Account Log In");
        System.out.println("2. Go to New Account Sign Up");
        menuChoice = scan.nextInt();
    }

    public static void accountLogin(){
        //Menu for logging into a user's account.
        System.out.println("Welcome to the Banking app sign up page.");
        System.out.println("Please enter your username: ");
        username = scan.next();
        System.out.println("Please enter your password: ");
        password = scan.next();

    }

    public static void signUpMenu(){
        System.out.println("Welcome to the Banking app sign up page.");
        System.out.println("Please enter a username: ");
        username = scan.next();
        System.out.println("Please enter a password: ");
        password = scan.next();
    }

    public static void customerMenu(){
        System.out.println("Welcome to your Account " + c.fName + " " + c.lName);
        System.out.println("Please select an option");
        System.out.println("1. view personal information");
        System.out.println("2. view account information");
        menuChoice = scan.nextInt();
    }

    public static void customerInfo(){
        System.out.println("First Name: " + c.fName);
        System.out.println("Last Name: " + c.lName);
        System.out.println("Street Address: " + c.streetAdd);
        System.out.println("City: " + c.city + " State: " + c.state + " Zip Code: " + c.zipCode);
        System.out.println("Date of Birth: " + c.birthdate);
    }

    public static void customerAccount(){
        System.out.println("Account info ");
    }

    public static void employeeMenu(){
        System.out.println("Hello " + e.fName + " " + e.lName);

    }
    public static void adminMenu(){
        System.out.println("Admin Access Menu ");
        System.out.println(e.fName + " " + e.lName + " You are logged in as admin");

    }

    /*EmployeeInterface employeeDAO = new EmployeeDAO();
        Employee e = employeeDAO.getEmployee(6);

        System.out.println("Customer Username: " + u.username);
        System.out.println("password: " + u.password);

        System.out.println("Employee name:" + e.fName + " " + e.lName);*/


}
