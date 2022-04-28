package AccountDaoPkg;

import AccountModelPkg.AccessLevel;
import AccountModelPkg.AccountLink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountLinkDAO implements AccountLinkInterface {
    @Override
    public AccountLink getLink(int id) {
        Connection connection = ConnectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account_link WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                AccountLink al = new AccountLink();
                al.linkId = rs.getInt("id");
                al.customerId = rs.getInt("customer_id");
                al.accountId = rs.getInt("account_id");
                return al;
            }
        } catch (SQLException e) {

        }
        return null;
    }
}
