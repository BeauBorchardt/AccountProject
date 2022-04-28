package AccountModelPkg;

public class AccountLink {
    public int linkId;
    public int customerId;
    public int accountId;

    @Override
    public String toString() {
        return "AccountLinkMod{" +
                "linkId=" + linkId +
                ", customerId=" + customerId +
                ", accountId=" + accountId +
                '}';
    }
}
