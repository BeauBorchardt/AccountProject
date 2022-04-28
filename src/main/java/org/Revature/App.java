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
    private static AccessLevelInterface accessLevelDao = new AccessLevelDAO();
    private static AccountLinkInterface accountLinkDa0 = new AccountLinkDAO();
    private static AccountStatusInterface accountStatusDao = new AccountStatusDAO();
    private static AccountTypeInterface accountTypeDao = new AccountTypeDAO();
    private static CustomerInterface customerDao = new CustomerDAO();
    private static CustomerAccountInterface customerAccountDao = new CustomerAccountDAO();
    private static EmployeeInterface employeeDao = new EmployeeDAO();
    private static UsersInterface userDao = new UsersDAO();

    static AccessLevel al;
    static AccountLink link;
    static AccountStatus as;
    static AccountType at;
    static Customer c;
    static CustomerAccount ca;
    static Employee e;
    static User u;

    public static void main( String[] args )
    {
        Javalin app = Javalin.create().start(7079);
        UserController userController = new UserController(app);

        topMenu();
        if(menuChoice == 1){
            accountLogin();
        } else if (menuChoice == 2){
            signUpMenu();
        } else if (menuChoice == 3){
            testMenu();
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
        User user = new User();

        System.out.println("Welcome to the Banking app sign up page.");
        System.out.println("Please enter a username: ");
        user.setUsername(scan.next());
        System.out.println("Please enter a password: ");
        user.setPassword(scan.next());
        user = userDao.createUser(user);
        System.out.println("Please enter your first name: ");
        Customer customer = new Customer(user.getUserId());

        System.out.println("Enter your last name: ");
        System.out.println("Enter your street address:");
        System.out.println("Enter your city:");
        System.out.println("Enter your State:");
        System.out.println("Enter your zip code:");
        System.out.println("Enter your birthdate:");


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

    public static void testMenu(){

    }

    /*EmployeeInterface employeeDAO = new EmployeeDAO();
        Employee e = employeeDAO.getEmployee(6);

        System.out.println("Customer Username: " + u.username);
        System.out.println("password: " + u.password);

        System.out.println("Employee name:" + e.fName + " " + e.lName);*/


}
