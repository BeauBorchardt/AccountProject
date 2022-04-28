package AccountDaoPkg;

import AccountModelPkg.AccessLevel;
import AccountModelPkg.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessLevelDAO implements AccessLevelInterface {

    @Override
    public AccessLevel getAccess(int id) {
        Connection connection = ConnectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM access_level WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                AccessLevel al = new AccessLevel();
                al.accessId = rs.getInt("id");
                al.accessType = rs.getString("access_type");
                return al;
            }
        } catch (SQLException e) {

        }
        return null;
    }
}
