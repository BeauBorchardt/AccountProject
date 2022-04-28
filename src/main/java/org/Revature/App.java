package org.Revature;

import Controller.UserController;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import AccountModelPkg.*;
import AccountDaoPkg.*;

import java.util.Random;
import java.util.Scanner;

public class App 
{
    private static final Logger logger = LogManager.getLogger(App.class);
    static Scanner scan = new Scanner(System.in);
    private static int menuChoice = 0;
    private static int customerMenuChoice = 0;
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

    static Customer c;
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
        User user2 = new User();
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
        Customer customer2 = new Customer();
        if(jointStatus == 1){
            //create new customer account
            System.out.println("Please Enter information for joint user account");
            System.out.println("Please enter a username: for your account");
            user2.setUsername(scan.next());
            System.out.println("Please enter a password for your account: ");
            user2.setPassword(scan.next());
            user2 = userDao.createUser(user2);
            System.out.println("Please enter your first name: ");
            customer2 = new Customer(user.getUserId());
            customer2.setfName(scan.next());
            System.out.println("Enter your last name: ");
            customer2.setlName(scan.next());
            System.out.println("Enter your street address:");
            customer2.setStreetAdd(scan.nextLine());
            System.out.println("Enter your city:");
            customer2.setCity(scan.nextLine());
            System.out.println("Enter your State:");
            customer2.setState(scan.next());
            System.out.println("Enter your zip code:");
            customer2.setZipCode(scan.next());
            customer2 = customerDao.createCustomer(customer);
        }

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
        Random random = new Random();
        int accountNumber = random.nextInt(147483647);
        CustomerAccount ac = new CustomerAccount();

        if(accType == 1 && jointStatus == 2){
            System.out.println("You have signed up for a checking account");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 1));
        } else if (accType == 1 && jointStatus == 1){
            System.out.println("You have signed up for a joint checking account");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 2));
        } else if (accType == 2 && jointStatus == 2){
            System.out.println("You have signed up for a saving account");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 3));
        } else if (accType == 2 && jointStatus == 1){
            System.out.println("You have signed up for a joint saving account");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 4));
        }


        accountLinkDa0.setLink(ac.getCustomerAccountId(), customer.customerId);


    }
    public static void customerMenu(){
        c = customerDao.getCustomer(u.userId);
        System.out.println("Welcome to your Account " + c.fName + " " + c.lName);
        while(customerMenuChoice < 1 || customerMenuChoice > 3){
            System.out.println("Please select an option");
            System.out.println("1. view personal information");
            System.out.println("2. view account information");
            System.out.println("3. See Account Balance and Withdraw funds");
            System.out.println("4. See Account Balance and Deposit funds");
            System.out.println("5. See Account Balance and transfer funds");
            System.out.println("6. Exit to main menu");
            customerMenuChoice = scan.nextInt();
            if(customerMenuChoice < 1 || customerMenuChoice >6 ){
                while(customerMenuChoice < 1 || customerMenuChoice >6 ){
                    System.out.println("Please enter a valid number 1 - 6");
                    customerMenuChoice = scan.nextInt();
                }
            }
            if (customerMenuChoice == 1){
                customerInfo();
            } else if (customerMenuChoice == 2) {
                customerAccount();
            }else if (customerMenuChoice == 3) {
                customerWithdraw();
            }else if (customerMenuChoice == 4) {
                customerDeposit();
            }else if (customerMenuChoice == 5) {
                customerTransfer();
            }else if (customerMenuChoice == 6) {
                return;
            }
        }

    }
    private static void customerTransfer() {
        System.out.println("Your current Account Balance is : $");
    }
    private static void customerDeposit() {
        System.out.println("Your current Account Balance is : $");
        System.out.println("Please enter amount you would like to deposit : ");
        double depositAmt = scan.nextDouble();
        if(depositAmt < 0){
            while(depositAmt < 0){
                System.out.println("Please enter a valid deposit amount: ");
                depositAmt = scan.nextDouble();
            }
        }
        System.out.println("You have deposited $");
        System.out.println("Your current account balance is $");
    }
    private static void customerWithdraw() {
        System.out.println("Your current Account Balance is : $");
        System.out.println("Please enter amount you would like to Withdraw : ");
        double withdrawAmt = scan.nextDouble();
    }
    public static void customerInfo(){
        System.out.println("First Name: " + c.fName);
        System.out.println("Last Name: " + c.lName);
        System.out.println("Street Address: " + c.streetAdd);
        System.out.println("City: " + c.city + " State: " + c.state + " Zip Code: " + c.zipCode);
    }
    public static void customerAccount(){
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccount(al.customerId);
        AccountStatus as = accountStatusDao.getAccountStatus(ca.accountStatus);
        AccountType at = accountTypeDao.getAccountType(ca.accountTypeId);

        System.out.println("Current Account information for " + c.fName + " " + c.lName );
        System.out.println("Account Status: " + as.statusType);
        System.out.println("Account Type: " + at.accountType);
    }
    public static void employeeMenu(){
        int employeeMenuChoice = 0;
        e = employeeDao.getEmployee(u.userId);
        System.out.println("Hello " + e.fName + " " + e.lName);
        System.out.println("Please select an option :");
        System.out.println("1. View Customer Account");
        System.out.println("2. View Customer Information");
        System.out.println("3. View Account Balances");
        System.out.println("4. See pending accounts");
        System.out.println("5. Exit Application");
        employeeMenuChoice = scan.nextInt();
        if(employeeMenuChoice < 0 || employeeMenuChoice >5){
            while(employeeMenuChoice < 0 || employeeMenuChoice >5){
                System.out.println("Please Select a Valid Menu Option: ");
                employeeMenuChoice = scan.nextInt();
            }
        }
        if(employeeMenuChoice == 1){
            //view customer account info
        } else if (employeeMenuChoice == 2){
            //view customer information
        } else if (employeeMenuChoice == 3){
            //view account balance
        }else if (employeeMenuChoice == 4){
            //view pending accounts
        }else if (employeeMenuChoice == 5){
            //exits to main
        }

    }
    public static void adminMenu(){
        System.out.println("Admin Access Menu ");
        System.out.println(e.fName + " " + e.lName + " You are logged in as admin");

    }





}
