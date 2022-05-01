package AccountDaoPkg;

import AccountModelPkg.AccountLink;
import AccountModelPkg.Customer;
import AccountModelPkg.User;

public interface CustomerInterface {
    Customer getCustomer(int id);
    Customer createCustomer(Customer customer);

    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
}
