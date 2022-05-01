package Controller;

import AccountDaoPkg.CustomerAccountDAO;
import AccountDaoPkg.CustomerDAO;
import AccountModelPkg.Customer;
import AccountModelPkg.CustomerAccount;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController {

    CustomerAccountDAO account;

    public AccountController(Javalin app){
        account = new CustomerAccountDAO();
        app.get("/account/{id}", getHandler);
        app.post("/account", postHandler);
        app.put("/account/{id}", putHandler);

    }

    public Handler getHandler = ctx-> {

        String accountId = ctx.pathParam("id");
        CustomerAccount accountGet = account.getCustomerAccount(Integer.parseInt(accountId));
        ctx.json(accountGet);

    };

    public Handler postHandler = ctx -> {



    };

    public Handler putHandler = ctx -> {


    };

    public Handler deleteHandler = ctx -> {


    };


}
