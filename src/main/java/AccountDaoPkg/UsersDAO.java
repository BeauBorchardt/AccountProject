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
            statement.setInt(3, user.accessLevel);

            statement.execute();
            return getUser(user.username);

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void updateUser(User user) {
        Connection connection = ConnectionManager.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " + "SET user_password = ?, access_level = ? " + "WHERE username = ?");

            statement.setString(1, user.password);
            statement.setInt(2, user.accessLevel);
            statement.setString(3, user.username);

            statement.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        Connection connection = ConnectionManager.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
            statement.setString(1, user.username);
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
