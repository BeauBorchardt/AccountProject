package AccountDaoPkg;

import AccountModelPkg.AccountLink;
import AccountModelPkg.Customer;

public interface CustomerInterface {
    public Customer getCustomer(int id);
    public Customer createCustomer(Customer customer);

}
