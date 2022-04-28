package AccountDaoPkg;

import AccountModelPkg.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO implements EmployeeInterface {

    @Override
    public Employee getEmployee(int userId) {

        Connection connection = ConnectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE user_id = ?");
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                Employee e = new Employee();
                e.employeeId = rs.getInt("id");
                e.userId = rs.getInt("user_id");
                e.fName = rs.getString("first_name");
                e.lName = rs.getString("last_name");

                return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
