package AccountModelPkg;

public class CustomerAccount {
    public int customerAccountId;
    public int accountNumber;
    public int accountTypeId;
    public double accountBalance;
    public int accountStatus;

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
