package AccountModelPkg;

public class CustomerAccount {
    public int customerAccountId;
    public int accountNumber;
    public int accountTypeId;
    public double accountBalance;
    public int accountStatus;

    public CustomerAccount() {

    }
    public CustomerAccount(int accountNumber, int accountTypeId){
        this.accountNumber = accountNumber;
        this.accountTypeId = accountTypeId;
        accountBalance = 00.00;
        accountStatus = 2;
    }

    public int getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(int customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

}


