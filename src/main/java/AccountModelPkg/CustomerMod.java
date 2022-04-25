package AccountModelPkg;

import java.util.Date;

public class CustomerMod {
    int customerId;
    int user_id;
    String fName;
    String lName;
    String streetAdd;
    String city;
    String state;
    String zipCode;
    Date birthdate;

    @Override
    public String toString() {
        return "CustomerMod{" +
                "customerId=" + customerId +
                ", user_id=" + user_id +
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
