package AccountModelPkg;

public class AccountType {
    int accountTypeId;
    String accountType;

    @Override
    public String toString() {
        return "AccountTypeMod{" +
                "accountTypeId=" + accountTypeId +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}