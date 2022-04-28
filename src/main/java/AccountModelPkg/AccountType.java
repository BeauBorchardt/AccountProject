package AccountModelPkg;

public class AccountType {
    public int accountTypeId;
    public String accountType;

    @Override
    public String toString() {
        return "AccountTypeMod{" +
                "accountTypeId=" + accountTypeId +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
