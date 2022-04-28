package AccountDaoPkg;

import AccountModelPkg.Customer;
import AccountModelPkg.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO implements CustomerInterface {

    @Override
    public Customer getCustomer(int id) {
        Connection connection = ConnectionManager.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE user_id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                Customer c = new Customer();
                c.customerId = rs.getInt("id");
                c.userId = rs.getInt("user_id");
                c.fName = rs.getString("first_name");
                c.lName = rs.getString("last_name");
                c.streetAdd = rs.getString("street_address");
                c.city = rs.getString("city");
                c.state = rs.getString("cust_state");
                c.zipCode = rs.getString("zip_code");
                c.birthdate = rs.getDate("birthdate");

                return c;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Customer createCustomer(int id){
        Connection connection = ConnectionManager.getConnection();

        return null;

    }
}
