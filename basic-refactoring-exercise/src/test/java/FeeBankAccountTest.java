import example.model.AccountHolder;
import example.model.FeeBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeeBankAccountTest {

    private AccountHolder holder;
    private FeeBankAccount account;
    private static final double INITIAL_BALANCE = 0;
    private static final double DEPOSIT_AMOUNT = 100;
    private static final double WITHDRAW_AMOUNT = 70;
    private static final int INVALID_HOLDER_ID = 2;

    @BeforeEach
    void setUp() {
        this.holder = new AccountHolder("Mario", "Rossi", 1);
        this.account = new FeeBankAccount(this.holder, INITIAL_BALANCE);
    }

    private void depositAmount(final int id) {
        this.account.deposit(id, DEPOSIT_AMOUNT);
    }

    private void withdrawAmount(final int id) {
        this.account.withdraw(id, WITHDRAW_AMOUNT);
    }

    @Test
    void shouldInitializeWithCorrectBalance() {
        assertEquals(INITIAL_BALANCE, this.account.getBalance());
    }

    @Test
    void shouldIncreaseBalanceWhenDepositIsValid() {
        depositAmount(this.holder.id());
        assertEquals(DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    void shouldMaintainBalanceWhenSecondaryDepositFails() {
        depositAmount(this.holder.id());
        depositAmount(INVALID_HOLDER_ID);
        assertEquals(DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    void shouldApplyFeeWhenWithdrawalIsValid() {
        depositAmount(this.holder.id());
        withdrawAmount(this.holder.id());
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT - FeeBankAccount.FEE_AMOUNT, this.account.getBalance());
    }

    @Test
    void shouldNotWithdrawWhenBalanceDoesNotCoverAmountPlusFee(){
        withdrawAmount(this.holder.id());
        assertEquals(INITIAL_BALANCE, this.account.getBalance());
    }
}
