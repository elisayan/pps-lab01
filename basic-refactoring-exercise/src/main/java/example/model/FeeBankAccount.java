package example.model;

public class FeeBankAccount extends AbstractBankAccount {

    public static final double FEE_AMOUNT = 1.0;

    public FeeBankAccount(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    protected boolean isWithdrawAllowed(final double amount) {
        return this.getBalance() >= amount + FEE_AMOUNT;
    }

    @Override
    protected void applyWithdrawal(final double amount) {
        this.decreaseBalance(amount + FEE_AMOUNT);
    }

}
