package Controller;

import AccountDaoPkg.CustomerDAO;
import AccountModelPkg.Customer;
import AccountModelPkg.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CustomerController {
    CustomerDAO customer;


    public CustomerController(Javalin app){
        customer = new CustomerDAO();
        app.get("/customer/{id}", getHandler);
        app.post("/customer", postHandler);
        app.put("/customer/{id}", postHandler);


    }

    public Handler getHandler = ctx-> {

        String customerId = ctx.pathParam("id");
        Customer customerGet = customer.getCustomer(Integer.parseInt(customerId));
        ctx.json(customerGet);

    };

    public Handler postHandler = ctx -> {

        Customer customerPost = ctx.bodyAsClass(Customer.class);
        customer.createCustomer(customerPost);
        ctx.status(201);

    };

    public Handler putHandler = ctx -> {

        Customer customerPut = ctx.bodyAsClass(Customer.class);
        customer.updateCustomer(customerPut);
        ctx.status(200);


    };

    public Handler deleteHandler = ctx -> {

        Customer customerDelete = ctx.bodyAsClass(Customer.class);
        customer.deleteCustomer(customerDelete);
        ctx.status(200);

    };


}
