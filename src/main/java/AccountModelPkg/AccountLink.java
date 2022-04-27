package AccountModelPkg;

public class AccountLink {
    int linkId;
    int customerId;
    int accountId;

    @Override
    public String toString() {
        return "AccountLinkMod{" +
                "linkId=" + linkId +
                ", customerId=" + customerId +
                ", accountId=" + accountId +
                '}';
    }
}
