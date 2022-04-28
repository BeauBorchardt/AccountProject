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
    private static int customerMenuChoice = 0;
    private static int employeeMenuChoice = 0;
    private static int adminMenuChoice = 0;
    private static int signupCheckingType =0;
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

        while(menuChoice != 3){
            topMenu();
            if(menuChoice == 1){
                accountLogin();
            } else if (menuChoice == 2){
                signUpMenu();
            } else if (menuChoice == 3){
                System.out.println("Session has ended ");
                System.exit(0);
            }
            u = userDao.getUser(username);
            if(u.accessLevel == 1){
                customerMenu();
            } else if (u.accessLevel == 2){
                employeeMenu();
            } else if (u.accessLevel == 3){
                e = employeeDao.getEmployee(u.userId);
                adminMenu();
            }
        }

    }

    public static void topMenu(){
        int topMenuCheck = 1;
        //Top menu that loads when application starts
        System.out.println("Welcome to the Banking app.");
        System.out.println("1. Go to Account Log In");
        System.out.println("2. Go to New Customer Account Sign Up");
        System.out.println("3. Exit application");
        menuChoice = scan.nextInt();
        if(menuChoice < 1 || menuChoice >3 ){
            while(menuChoice < 1 || menuChoice >3 ){
                System.out.println("Please enter a number 1, 2, or 3");
                menuChoice = scan.nextInt();
            }
        }
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
        int signUpChoice = 0;

        System.out.println("Welcome to the Banking app sign up page.");
        System.out.println("Please enter a username: for your account");
        user.setUsername(scan.next());
        System.out.println("Please enter a password for your account: ");
        user.setPassword(scan.next());
        user = userDao.createUser(user);
        System.out.println("Please enter your first name: ");
        Customer customer = new Customer(user.getUserId());
        customer.setfName(scan.next());
        System.out.println("Enter your last name: ");
        customer.setlName(scan.next());
        System.out.println("Enter your street address:");
        customer.setStreetAdd(scan.next());
        System.out.println("Enter your city:");
        customer.setCity(scan.next());
        System.out.println("Enter your State:");
        customer.setState(scan.next());
        System.out.println("Enter your zip code:");
        customer.setZipCode(scan.next());
        customer = customerDao.createCustomer(customer);

        System.out.println("Will this be a joint account?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        signUpChoice = scan.nextInt();
        if(signUpChoice < 0 || signUpChoice > 2){
            while(signUpChoice < 1 || signUpChoice > 2 ){
                System.out.println("Please enter 1 for Yes, 2 for no");
                signUpChoice = scan.nextInt();
            }
        }
        int jointStatus = signUpChoice;
        System.out.println("Select what type of account you would like to create");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        signUpChoice = scan.nextInt();
        if(signUpChoice < 0 || signUpChoice > 2){
            while(signUpChoice < 1 || signUpChoice > 2 ){
                System.out.println("Please enter 1 for Checking, 2 for Savings");
                signUpChoice = scan.nextInt();
            }
        }
        int accType = signUpChoice;
        if(accType == 1 && jointStatus == 2){
            System.out.println("You have signed up for a checking account");
        } else if (accType == 1 && jointStatus == 1){
            System.out.println("You have signed up for a joint checking account");
        } else if (accType == 2 && jointStatus == 2){
            System.out.println("You have signed up for a saving account");
        } else if (accType == 2 && jointStatus == 1){
            System.out.println("You have signed up for a joint saving account");
        }

    }

    public static void customerMenu(){
        c = customerDao.getCustomer(u.userId);
        System.out.println("Welcome to your Account " + c.fName + " " + c.lName);
        while(customerMenuChoice < 1 || customerMenuChoice > 3){
            System.out.println("Please select an option");
            System.out.println("1. view personal information");
            System.out.println("2. view account information");
            System.out.println("3. Exit to main menu");
            customerMenuChoice = scan.nextInt();
            if(customerMenuChoice < 1 || customerMenuChoice >3 ){
                while(customerMenuChoice < 1 || customerMenuChoice >3 ){
                    System.out.println("Please enter a number 1, 2, or 3");
                    customerMenuChoice = scan.nextInt();
                }
            }
        }

        if(menuChoice == 1){
            customerInfo();
        } else if (menuChoice == 2){
            customerAccount();
        }
    }

    public static void customerInfo(){
        System.out.println("First Name: " + c.fName);
        System.out.println("Last Name: " + c.lName);
        System.out.println("Street Address: " + c.streetAdd);
        System.out.println("City: " + c.city + " State: " + c.state + " Zip Code: " + c.zipCode);
    }

    public static void customerAccount(){
        System.out.println("Account info ");
    }

    public static void employeeMenu(){
        e = employeeDao.getEmployee(u.userId);
        System.out.println("Hello " + e.fName + " " + e.lName);

    }
    public static void adminMenu(){
        System.out.println("Admin Access Menu ");
        System.out.println(e.fName + " " + e.lName + " You are logged in as admin");

    }

    public static void testMenu(){

    }




}
