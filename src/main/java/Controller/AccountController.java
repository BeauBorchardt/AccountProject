package Controller;

import AccountDaoPkg.CustomerAccountDAO;
import AccountDaoPkg.CustomerDAO;
import AccountModelPkg.Customer;
import AccountModelPkg.CustomerAccount;
import AccountModelPkg.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController {

    CustomerAccountDAO account;

    public AccountController(Javalin app){
        account = new CustomerAccountDAO();
        app.get("/account/{id}", getHandler);
        app.post("/account", postHandler);
        app.put("/account/{id}", putHandler);
        app.delete("/account/{id}", deleteHandler);

    }

    public Handler getHandler = ctx-> {

        String accountId = ctx.pathParam("id");
        CustomerAccount accountGet = account.getCustomerAccountViaId(Integer.parseInt(accountId));
        ctx.json(accountGet);

    };

    public Handler postHandler = ctx -> {

        CustomerAccount customerAccount = ctx.bodyAsClass(CustomerAccount.class);
        account.createCustomerAccount(customerAccount);
        ctx.status(201);



    };

    public Handler putHandler = ctx -> {
        CustomerAccount customerAccount = ctx.bodyAsClass(CustomerAccount.class);
        account.updateCustomerAccount(customerAccount);
        ctx.status(200);


    };

    public Handler deleteHandler = ctx -> {


    };


}
