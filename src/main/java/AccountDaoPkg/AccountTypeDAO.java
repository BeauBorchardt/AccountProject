package AccountDaoPkg;

import AccountModelPkg.AccountStatus;
import AccountModelPkg.AccountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTypeDAO implements AccountTypeInterface{
    @Override
    public AccountType getAccountType(int id) {
        Connection connection = ConnectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bank_account_type WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                AccountType at = new AccountType();
                at.accountTypeId = rs.getInt("id");
                at.accountType = rs.getString("account_type");
                return at;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
