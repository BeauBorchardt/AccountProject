package AccountDaoPkg;

import AccountModelPkg.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO implements UsersInterface {

    @Override
    public User getUser(String username) {

        Connection connection = ConnectionManager.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                User u = new User();
                u.userId = rs.getInt("id");
                u.username = rs.getString("username");
                u.password = rs.getString("user_password");
                u.accessLevel = rs.getInt("access_level");

                return u;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (username, user_password, access_level) VALUES (?, ?, ?)");

            statement.setString(1, user.username);
            statement.setString(2, user.password);
            statement.setInt(3, 1);

            statement.execute();
            return getUser(user.username);

        } catch(SQLException e){

        }
        return null;

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleterUser(User user) {

    }
}
