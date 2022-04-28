package AccountDaoPkg;

import AccountModelPkg.Customer;
import AccountModelPkg.CustomerAccount;
import AccountModelPkg.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CustomerAccountDAO implements CustomerAccountInterface {

    @Override
    public CustomerAccount getCustomerAccount(int id) {
        Connection connection = ConnectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer_account WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                CustomerAccount ca = new CustomerAccount();
                ca.customerAccountId = rs.getInt("id");
                ca.accountNumber = rs.getInt("account_number");
                ca.accountTypeId = rs.getInt("account_type");
                ca.accountBalance = rs.getDouble("account_balance");
                ca.accountStatus = rs.getInt("account_status");

                return ca;
            }
            } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public CustomerAccount createCustomerAccount(CustomerAccount customerAccount) {
        Connection connection = ConnectionManager.getConnection();


        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO customer_account (account_number, account_type, account_balance, account_status) VALUES (?, ?, ?, ?)");

            statement.setInt(1, customerAccount.getAccountNumber());
            statement.setInt(2, customerAccount.getAccountTypeId());
            statement.setDouble(3, customerAccount.getAccountBalance());
            statement.setInt(4, customerAccount.getAccountStatus());

            statement.execute();
            return getCustomerAccount(customerAccount.customerAccountId);

        } catch(SQLException e){
            e.printStackTrace();

        }
        return null;

    }
}
