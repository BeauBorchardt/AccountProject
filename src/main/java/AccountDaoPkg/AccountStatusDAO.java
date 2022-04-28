package AccountDaoPkg;

import AccountModelPkg.AccountLink;
import AccountModelPkg.AccountStatus;
import AccountModelPkg.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStatusDAO implements AccountStatusInterface {

    @Override
    public AccountStatus getAccountStatus(int id) {
        Connection connection = ConnectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account_status WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                AccountStatus as = new AccountStatus();
                as.statusId = rs.getInt("id");
                as.statusType = rs.getString("status_type");
                return as;
            }
        } catch (SQLException e) {

        }
        return null;
    }
}
