package AccountModelPkg;

public class AccountStatus {
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
