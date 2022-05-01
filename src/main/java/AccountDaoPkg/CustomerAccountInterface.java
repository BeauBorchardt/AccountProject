package AccountDaoPkg;

import AccountModelPkg.AccountLink;
import AccountModelPkg.Customer;
import AccountModelPkg.CustomerAccount;
import AccountModelPkg.User;

import java.util.List;

public interface CustomerAccountInterface {
    CustomerAccount getCustomerAccount(int id);
    CustomerAccount createCustomerAccount(CustomerAccount customerAccount);
    void updateAccountBalance(CustomerAccount customerAccount);

    void updateAccountStatus(CustomerAccount customerAccount);
    CustomerAccount getCustomerAccountViaId(int id);
    void updateCustomerAccount(CustomerAccount customerAccount);
    void deleteCustomerAccount(CustomerAccount customerAccount);

    List<CustomerAccount> getCustomerAccountViaStatus(int id);
}
