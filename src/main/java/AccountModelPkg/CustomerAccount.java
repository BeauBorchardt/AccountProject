package AccountModelPkg;

public class CustomerAccount {
    int customerAccountId;
    int accountNumber;
    int accountTypeId;
    double accountBalance;
    int accountStatus;

    @Override
    public String toString() {
        return "CustomerAccountMod{" +
                "customerAccountId=" + customerAccountId +
                ", accountNumber=" + accountNumber +
                ", accountTypeId=" + accountTypeId +
                ", accountBalance=" + accountBalance +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
