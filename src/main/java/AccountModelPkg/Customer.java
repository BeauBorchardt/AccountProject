package AccountModelPkg;

import java.util.Date;

public class Customer {
    public int customerId;
    public int userId;
    public String fName;
    public String lName;
    public String streetAdd;
    public String city;
    public String state;
    public String zipCode;
    public Date birthdate;

    @Override
    public String toString() {
        return "CustomerMod{" +
                "customerId=" + customerId +
                ", user_id=" + userId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", streetAdd='" + streetAdd + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
