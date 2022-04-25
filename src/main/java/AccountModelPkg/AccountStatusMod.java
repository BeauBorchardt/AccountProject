package AccountModelPkg;

public class AccountStatusMod {
    int statusId;
    String statusType;

    @Override
    public String toString() {
        return "AccountStatusMod{" +
                "statusId=" + statusId +
                ", statusType='" + statusType + '\'' +
                '}';
    }
}
