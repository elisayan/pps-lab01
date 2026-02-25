package example.model;

/**
 * This class represent a particular instance of a BankAccount
 * In particular, a Fee Bank Account applies a fixed fee on each withdrawal operation
 * The withdrawal is allowed only if the balance is greater or equal the withdrawal amount plus the fee
 */
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
