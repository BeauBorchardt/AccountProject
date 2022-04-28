package AccountDaoPkg;

import AccountModelPkg.AccountLink;

public interface AccountLinkInterface {
    AccountLink getLink(int id);
    AccountLink setLink(int accountId, int customerId);

}
