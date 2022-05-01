package AccountDaoPkg;

import AccountModelPkg.Customer;
import AccountModelPkg.User;

import java.sql.*;

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

                return c;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Customer createCustomer(Customer customer){
        Connection connection = ConnectionManager.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Customer (user_id, first_name, last_name, street_address, city, cust_state, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?)");

            statement.setInt(1, customer.userId);
            statement.setString(2, customer.fName);
            statement.setString(3, customer.lName);
            statement.setString(4, customer.streetAdd);
            statement.setString(5, customer.city);
            statement.setString(6, customer.state);
            statement.setString(7, customer.zipCode);

            statement.execute();
            return getCustomer(customer.userId);

        } catch(SQLException e){

        }
        return null;

    }

    @Override
    public void updateCustomer(Customer customer) {

        Connection connection = ConnectionManager.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE customer " + "SET first_name= ?, last_name = ?, street_address = ?, city = ?, cust_state = ?, zip_code = ?"
                            + "WHERE id = ?");

            statement.setString(1, customer.fName);
            statement.setString(2, customer.lName);
            statement.setString(3, customer.streetAdd);
            statement.setString(4, customer.city);
            statement.setString(5, customer.state);
            statement.setString(6, customer.zipCode);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteCustomer(Customer customer) {

        Connection connection = ConnectionManager.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
            statement.setInt(1, customer.customerId);
            statement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }


    }
}
