package AccountModelPkg;

public class AccountStatus {
    public int statusId;
    public String statusType;

    @Override
    public String toString() {
        return "AccountStatusMod{" +
                "statusId=" + statusId +
                ", statusType='" + statusType + '\'' +
                '}';
    }
}
