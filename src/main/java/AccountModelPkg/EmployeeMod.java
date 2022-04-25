package AccountModelPkg;

public class EmployeeMod {
    int employeeId;
    int userId;
    String fName;
    String lName;

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
