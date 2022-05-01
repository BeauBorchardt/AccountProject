package AccountDaoPkg;

import AccountModelPkg.AccountLink;
import AccountModelPkg.Customer;
import AccountModelPkg.User;

public interface CustomerInterface {
    public Customer getCustomer(int id);
    public Customer createCustomer(Customer customer);

    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
}
