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

    public Customer(){

    }
    public Customer(int userId){
        this.userId = userId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setStreetAdd(String streetAdd) {
        this.streetAdd = streetAdd;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

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
