package example.model;

public class FeeBankAccount extends AbstractBankAccount{

    public static final double FEE_AMOUNT = 1;

    public FeeBankAccount(final AccountHolder holder, final double balance) {
        super(holder,balance);
    }

    @Override
    protected boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount + FEE_AMOUNT;
    }

    @Override
    protected void applyWithdrawal(final double amount) {
        this.balance -=amount + FEE_AMOUNT;
    }

}
