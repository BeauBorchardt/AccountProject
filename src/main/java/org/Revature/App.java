package org.Revature;

import Controller.AccountController;
import Controller.CustomerController;
import Controller.UserController;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import AccountModelPkg.*;
import AccountDaoPkg.*;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class App 
{
    private static final Logger logger = LogManager.getLogger(App.class);
    static Scanner scan = new Scanner(System.in);
    protected static int menuChoice = 0;
    private static int accessLevel = 0;
    private static String username;
    private static String password;

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
        CustomerController customerController = new CustomerController(app);
        AccountController accountController = new AccountController(app);

        while(menuChoice != 3){

            topMenu();
            if(menuChoice == 1){
                accountLogin();
                u = userDao.getUser(username);
                if(u.accessLevel == 1){
                    customerMenu();
                } else if (u.accessLevel == 2){
                    employeeMenu();
                } else if (u.accessLevel == 3){
                    e = employeeDao.getEmployee(u.userId);
                    adminMenu();
                }
            } else if (menuChoice == 2){
                signUpMenu();
            } else if (menuChoice == 3){
                System.out.println("Session has ended ");
                System.exit(0);
            }

        }

    }

    public static void topMenu(){

        //Top menu that loads when application starts
        menuChoice = 0;
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
        System.out.println("Welcome to the Banking app Log In up page.");
        System.out.println("Please enter your username: ");
        username = scan.next();
        User user = userDao.getUser(username);
        while(null == user) {
            System.out.println("Incorrect username, reenter your username: ");
            username = scan.next();
            user = userDao.getUser(username);
        }
        System.out.println("Please enter your password: ");
        password = scan.next();


        while(!password.equals(user.getPassword())){
            System.out.println("Inccorect password, please reenter your password: ");
            password = scan.next();
        }
        u = user;

    }

    //Customer menu handling methods
    public static void customerMenu(){
        int customerMenuChoice = 0;
        c = customerDao.getCustomer(u.userId);
        System.out.println("Welcome to your Account " + c.fName + " " + c.lName);
        while(customerMenuChoice != 6){
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
            }
        }
    }
    private static void customerTransfer() {
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        Double balance = ca.getAccountBalance();
        System.out.println("Transfer money to another account");
        System.out.println("Your current Account Balance is : $" + balance);
        System.out.println("Enter the username of the account you would like to transfer to:");
        String transferToUser = scan.next();
        User tUser = userDao.getUser(transferToUser);
        Customer cTransfer = customerDao.getCustomer(tUser.userId);
        AccountLink al2 = accountLinkDa0.getLink(cTransfer.customerId);
        CustomerAccount ca2 = customerAccountDao.getCustomerAccountViaId(al2.accountId);
        Double transferAccountBalance = ca2.getAccountBalance();
        System.out.println("How much would you like to transfer to " + cTransfer.fName + " " + cTransfer.lName);
        Double transferAmount = scan.nextDouble();
        if(transferAmount > balance){
            while(transferAmount > balance){
                System.out.println("That amount is more than your current balance");
                System.out.println("Please enter a valid withdrawal amount: ");
                transferAmount = scan.nextDouble();
            }
        }
        ca.setAccountBalance(balance - transferAmount);
        customerAccountDao.updateAccountBalance(ca);
        ca2.setAccountBalance(transferAccountBalance + transferAmount);
        customerAccountDao.updateAccountBalance(ca2);
        System.out.println("You have transferred $" + transferAmount + " to " + cTransfer.fName + " " + cTransfer.lName);
        System.out.println();
        System.out.println("Press Enter 1 to return to Customer Menu");
        int x = scan.nextInt();
    }
    private static void customerDeposit() {
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        Double balance = ca.getAccountBalance();
        System.out.println("Your current Account Balance is : $"+ balance );
        System.out.println("Please enter amount you would like to deposit : ");
        double depositAmt = scan.nextDouble();
        if(depositAmt < 0){
            while(depositAmt < 0){
                System.out.println("Please enter a valid deposit amount: ");
                depositAmt = scan.nextDouble();
            }
        }
        ca.setAccountBalance(balance + depositAmt);
        customerAccountDao.updateAccountBalance(ca);

        System.out.println("You have deposited $" + depositAmt);
        System.out.println("Your current account balance is $" + ca.getAccountBalance());
        System.out.println();
        System.out.println("Press Enter 1 to return to Customer Menu");
        int x = scan.nextInt();

    }
    private static void customerWithdraw() {
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        Double balance = ca.getAccountBalance();
        System.out.println("Your current Account Balance is : $"+ balance );
        System.out.println("Please enter amount you would like to withdraw : ");
        double withdrawAmt = scan.nextDouble();
        if(withdrawAmt > balance){
            while(withdrawAmt > balance){
                System.out.println("That amount is more than your current balance");
                System.out.println("Please enter a valid withdrawal amount: ");
                withdrawAmt = scan.nextDouble();
            }
        }
        ca.setAccountBalance(balance - withdrawAmt);
        customerAccountDao.updateAccountBalance(ca);

        System.out.println("You have withdrawn $" + withdrawAmt);
        System.out.println("Your current account balance is $" + ca.getAccountBalance());
        System.out.println();
        logger.info("$" + withdrawAmt + " withdrawn from account " + ca.getAccountNumber());
        System.out.println();
        System.out.println("Press Enter 1 to return to Customer Menu");
        int x = scan.nextInt();
    }
    public static void customerInfo(){
        System.out.println("First Name: " + c.fName);
        System.out.println("Last Name: " + c.lName);
        System.out.println("Street Address: " + c.streetAdd);
        System.out.println("City: " + c.city + " State: " + c.state + " Zip Code: " + c.zipCode);
        System.out.println();
        System.out.println("Press Enter 1 to return to Customer Menu");
        int x = scan.nextInt();
    }
    public static void customerAccount(){
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        AccountStatus as = accountStatusDao.getAccountStatus(ca.accountStatus);
        AccountType at = accountTypeDao.getAccountType(ca.accountTypeId);

        System.out.println("Current Account information for " + c.fName + " " + c.lName );
        System.out.println("Account Status: " + as.statusType);
        System.out.println("Account Type: " + at.accountType);
        System.out.println("Account Number: " + ca.getAccountNumber());

        System.out.println();
        System.out.println("Press Enter 1 to return to Customer Menu");
        int x = scan.nextInt();

    }

    //sign up menu handling
    public static void signUpMenu(){
        User user = new User();
        User user2 = new User();
        int signUpChoice = 0;

        System.out.println("Welcome to the Banking App Application page.");
        System.out.println("Please enter a username for your account: ");
        user.setUsername(scan.next());
        System.out.println("Please enter a password for your account: ");
        user.setPassword(scan.next());
        user.setAccessLevel(1);
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

        System.out.println("Select what type of account you would like to apply for: ");
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
            System.out.println("You have applied for a checking account pending bank approval");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 1));
            logger.info("New checking account created for " + customer.fName + " " + customer.lName);
        } else if (accType == 1 && jointStatus == 1){
            System.out.println("You have applied for a joint checking account pending bank approval");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 2));
            logger.info("New joint checking account created for " + customer.fName + " " + customer.lName);
        } else if (accType == 2 && jointStatus == 2){
            System.out.println("You have applied for a saving account pending bank approval");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 3));
            logger.info("New savings account created for " + customer.fName + " " + customer.lName);
        } else if (accType == 2 && jointStatus == 1){
            System.out.println("You have applied for a joint saving account pending bank approval");
            ac = customerAccountDao.createCustomerAccount(new CustomerAccount(accountNumber, 4));
        }
        accountLinkDa0.setLink(ac.getCustomerAccountId(), customer.customerId);

        System.out.println();
        System.out.println("Press Enter 1 to return to Main Menu");
        int x = scan.nextInt();

    }

    //employee menu handling methods
    public static void employeeMenu(){
        int employeeMenuChoice = 0;
        e = employeeDao.getEmployee(u.userId);
        while(employeeMenuChoice != 3){
            System.out.println("Hello " + e.fName + " " + e.lName);
            System.out.println("Please select an option :");
            System.out.println("1. View Customer Account Information");
            System.out.println("2. See pending account applications for approval");
            System.out.println("3. Exit Application");
            employeeMenuChoice = scan.nextInt();
            if(employeeMenuChoice < 0 || employeeMenuChoice > 3){
                while(employeeMenuChoice < 0 || employeeMenuChoice >3){
                    System.out.println("Please Select a Valid Menu Option: ");
                    employeeMenuChoice = scan.nextInt();
                }
            }
            if(employeeMenuChoice == 1){
                //view customer account info
                employeeViewAllCustomerInfo();
            } else if (employeeMenuChoice == 2){
                //view customer information
                seePending();
            }
        }
    }


    public static void employeeViewAllCustomerInfo(){

        System.out.println("Enter the username of the Customer Information you would like to view: ");
        String userToView = scan.next();
        User viewUser = userDao.getUser(userToView);
        Customer cView = customerDao.getCustomer(viewUser.userId);
        AccountLink al2 = accountLinkDa0.getLink(cView.customerId);
        CustomerAccount caView = customerAccountDao.getCustomerAccountViaId(al2.accountId);
        AccountStatus as = accountStatusDao.getAccountStatus(caView.accountStatus);
        AccountType at = accountTypeDao.getAccountType(caView.accountTypeId);
        //Print customer info to screen
        System.out.println("Customer Name: " + cView.fName + " " + cView.lName);
        System.out.println("Customer Address: " + cView.streetAdd + " City: "
                + cView.city + " State: " + cView.state + " Zip Code: " + cView.zipCode);
        System.out.println("Account Number : " + caView.getAccountNumber() + "Account Status: " + as.statusType);
        System.out.println("Account Balance: $" + caView.getAccountBalance());

        System.out.println();
        System.out.println("Press Enter 1 to return to Employee Menu");
        int x = scan.nextInt();

    }


    //admin menu handling methods
    public static void adminMenu(){
        int adminMenuChoice = 0;
        while(adminMenuChoice != 5){
            System.out.println("Admin Access Menu ");
            System.out.println(e.fName + " " + e.lName + " You are logged in as admin");
            System.out.println("Please select an option :");
            System.out.println("1. View Customer Account Information");
            System.out.println("2. Deposit, Withdraw, Transfer ");
            System.out.println("3. See pending account applications ");
            System.out.println("4. Cancel an Account");
            System.out.println("5. Exit Application");
            adminMenuChoice = scan.nextInt();
            if(adminMenuChoice < 0 || adminMenuChoice > 5){
                while(adminMenuChoice < 0 || adminMenuChoice > 5) {
                    System.out.println("Please Select a Valid Menu Option: ");
                    adminMenuChoice = scan.nextInt();
                }
            }
            if(adminMenuChoice == 1){
                //view customer account info
                employeeViewAllCustomerInfo();

            } else if (adminMenuChoice == 2){
                //view account balance
                adminAccountAction();
            } else if (adminMenuChoice == 3){
                seePending();
            }
        }
    }

    public static void adminAccountAction(){
        int adminAction = 0;
        while(adminAction != 4){
            System.out.println("Please select an option :");
            System.out.println("1. Deposit funds to an account");
            System.out.println("2. Withdraw funds from an account");
            System.out.println("3. Transfer funds to another account");
            System.out.println("4. Return to admin main menu");
            adminAction = scan.nextInt();
            if(adminAction == 1){
                adminDesposit();
            } else if (adminAction == 2){
                adminWithdraw();
            } else if (adminAction == 3){
                adminTransfer();
            }
        }
    }

    private static void adminTransfer() {
        System.out.println("Enter the username of the Customer you want to withdraw funds for: ");
        String customerTransfer1 = scan.next();
        User withdrawTransfer = userDao.getUser(customerTransfer1);
        Customer c = customerDao.getCustomer(withdrawTransfer.userId);
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        Double balance1 = ca.getAccountBalance();
        System.out.println("The Account Balance for "+ c.fName + " " + c.lName + "  : $"+ balance1 );
        System.out.println("Please enter amount you would like to transfer from this account: ");
        double transferAmt = scan.nextDouble();
        if(transferAmt > balance1){
            while(transferAmt > balance1){
                System.out.println("That amount is more than the current balance");
                System.out.println("Please enter a valid transfer amount: ");
                transferAmt = scan.nextDouble();
            }
        }
        ca.setAccountBalance(balance1 - transferAmt);
        customerAccountDao.updateAccountBalance(ca);

        System.out.println("Enter the username of the Customer you want to transfer to: ");
        String customerTransfer2 = scan.next();
        User withdrawTransfer2 = userDao.getUser(customerTransfer2);
        Customer c2 = customerDao.getCustomer(withdrawTransfer2.userId);
        AccountLink al2 = accountLinkDa0.getLink(c2.customerId);
        CustomerAccount ca2 = customerAccountDao.getCustomerAccountViaId(al2.accountId);
        Double balance2 = ca2.getAccountBalance() + transferAmt;
        ca2.setAccountBalance(balance2);
        customerAccountDao.updateAccountBalance(ca2);


        System.out.println("You have transfered $" + transferAmt + " to " + c2.fName + " " + c2.lName );
        System.out.println("The new Account Balance for "+ c2.fName + " " + c2.lName + " is  : $"+ balance2 );

        System.out.println();
        System.out.println("Press Enter 1 to return to the Admin Menu");
        int x = scan.nextInt();

    }

    private static void adminWithdraw() {
        System.out.println("Enter the username of the Customer you want to withdraw funds for: ");
        String customerWithdraw = scan.next();
        User withdraw = userDao.getUser(customerWithdraw);
        Customer c = customerDao.getCustomer(withdraw.userId);
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        Double balance = ca.getAccountBalance();
        System.out.println("The Account Balance for "+ c.fName + " " + c.lName + "  : $"+ balance );
        System.out.println("Please enter amount you would like to withdraw this account: ");
        double withdrawAmt = scan.nextDouble();
        if(withdrawAmt > balance){
            while(withdrawAmt > balance){
                System.out.println("That amount is more than the current balance");
                System.out.println("Please enter a valid withdrawal amount: ");
                withdrawAmt = scan.nextDouble();
            }
        }
        ca.setAccountBalance(balance - withdrawAmt);
        customerAccountDao.updateAccountBalance(ca);

        System.out.println("You have withdrawn $" + withdrawAmt);
        System.out.println("The  current account balance for "+ c.fName + " " + c.lName + " is $" + ca.getAccountBalance());
        System.out.println();
        logger.info("$" + withdrawAmt + " withdrawn from account " + ca.getAccountNumber());
        System.out.println();
        System.out.println("Press Enter 1 to return to the Admin Menu");
        int x = scan.nextInt();

    }

    private static void adminDesposit() {

        System.out.println("Enter the username of the Customer you want to deposit funds for: ");
        String customerDeposit = scan.next();
        User deposit = userDao.getUser(customerDeposit);
        Customer c = customerDao.getCustomer(deposit.userId);
        AccountLink al = accountLinkDa0.getLink(c.customerId);
        CustomerAccount ca = customerAccountDao.getCustomerAccountViaId(al.accountId);
        Double balance = ca.getAccountBalance();
        System.out.println("The Account Balance for "+ c.fName + " " + c.lName + "  : $"+ balance );
        System.out.println("Please enter amount you would like to deposit : ");
        double depositAmt = scan.nextDouble();
        if(depositAmt < 0){
            while(depositAmt < 0){
                System.out.println("Please enter a valid deposit amount: ");
                depositAmt = scan.nextDouble();
            }
        }
        ca.setAccountBalance(balance + depositAmt);
        customerAccountDao.updateAccountBalance(ca);

        System.out.println("You have deposited $" + depositAmt);
        System.out.println("The  current account balance for "+ c.fName + " " + c.lName + " is $" + ca.getAccountBalance());
        System.out.println();
        System.out.println("Press press 1 and enter to return to admin Menu");
        int x = scan.nextInt();

    }

    public static void seePending(){
        List<CustomerAccount> customerAccountList = customerAccountDao.getCustomerAccountViaStatus(2);
        System.out.println("List of Pending Accounts");

        //customerAccountList.forEach();

        for(int i = 0; i < customerAccountList.size(); i++){

            System.out.println((i + 1) + ". Account#: " + customerAccountList.get(i).getAccountNumber());
        }
        System.out.println("Select the number of the account you would like to approve or deny");
        int x = scan.nextInt();

        System.out.println("Would you like to approve or deny this account?");
        System.out.println("1. Approve account");
        System.out.println("2.Deny account and delete information");
        int y = scan.nextInt();
        while(y < 1 || y > 2){
            System.out.println("Please enter a valid selection");
        }
        if(y == 1){
            System.out.println("Account with account number " +customerAccountList.get(x - 1).getAccountNumber() + " will be approved.");

            CustomerAccount account = customerAccountList.get(x-1);
            account.setAccountStatus(1);
            customerAccountDao.updateAccountStatus(account);

        } else if (y == 2){
            System.out.println("Account with account number " +customerAccountList.get(x - 1).getAccountNumber() + " will be closed.");
            CustomerAccount account = customerAccountList.get(x-1);
            account.setAccountStatus(3);
            customerAccountDao.updateAccountStatus(account);

        }

        System.out.println();
        System.out.println("Press Enter 1 to return to Customer Menu");
        int z = scan.nextInt();

    }

    public static void cancelAccount(){
        System.out.println("Enter the account number for the account you want to cancel:");
        String cancelAccount = scan.next();



    }



}






