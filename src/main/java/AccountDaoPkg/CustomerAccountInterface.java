package AccountDaoPkg;

import AccountModelPkg.AccountLink;
import AccountModelPkg.Customer;
import AccountModelPkg.CustomerAccount;

public interface CustomerAccountInterface {
    public CustomerAccount getCustomerAccount(int id);
    public CustomerAccount createCustomerAccount(int id);
}
