package example.model;

abstract class AbstractBankAccount implements BankAccount {

    public double balance;
    public final AccountHolder holder;

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
        if (checkUser(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount){
        if (checkUser(userID) && this.isWithdrawAllowed(amount)) {
            applyWithdrawal(amount);
        }
    }

    protected boolean checkUser(final int id) {
        return this.holder.id() == id;
    }

    protected abstract boolean isWithdrawAllowed(final double amount);

    protected abstract void applyWithdrawal(final double amount);
}
