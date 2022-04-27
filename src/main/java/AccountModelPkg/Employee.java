package AccountModelPkg;

public class Employee {
    public int employeeId;
    public int userId;
    public String fName;
    public String lName;

    @Override
    public String toString() {
        return "EmployeeMod{" +
                "employeeId=" + employeeId +
                ", userId=" + userId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                '}';
    }
}
