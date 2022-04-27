package AccountDaoPkg;

import AccountModelPkg.Employee;
import AccountModelPkg.User;

import java.sql.Connection;

public interface EmployeeInterface {


    public Employee getEmployee(int userId);

}
