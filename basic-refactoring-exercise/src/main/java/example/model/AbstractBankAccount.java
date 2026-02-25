package example.model;

/**
 * This abstract class provides a base implementation of a BankAccount
 * It handles common operations like deposit, balance management, and user validation
 * Subclasses must define specific withdrawal policies
 */
abstract class AbstractBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;

    public AbstractBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (this.checkUser(userID)) {
            this.increaseBalance(amount);
        }
    }

    @Override
    public void withdraw(final int userID, final double amount){
        if (this.checkUser(userID) && this.isWithdrawAllowed(amount)) {
            this.applyWithdrawal(amount);
        }
    }

    private boolean checkUser(final int id) {
        return this.holder.id() == id;
    }

    protected void increaseBalance(final double amount) {
        this.balance += amount;
    }

    protected void decreaseBalance(final double amount) {
        this.balance -= amount;
    }

    protected abstract boolean isWithdrawAllowed(final double amount);

    protected abstract void applyWithdrawal(final double amount);
}
