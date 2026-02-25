import example.model.AccountHolder;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private SimpleBankAccount bankAccount;
    private static final double INITIAL_BALANCE = 0;
    private static final double DEPOSIT_AMOUNT = 100;
    private static final double WITHDRAW_AMOUNT = 70;
    private static final int INVALID_HOLDER_ID = 2;

    @BeforeEach
    void beforeEach() {
        this.accountHolder = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new SimpleBankAccount(this.accountHolder, INITIAL_BALANCE);
    }

    private void depositAs(final int id) {
        this.bankAccount.deposit(id, DEPOSIT_AMOUNT);
    }

    private void withdrawAs(final int id) {
        this.bankAccount.withdraw(id, WITHDRAW_AMOUNT);
    }

    @Test
    void shouldHaveInitialBalance() {
        assertEquals(INITIAL_BALANCE, this.bankAccount.getBalance());
    }

    @Test
    void shouldAcceptValidDeposit() {
        depositAs(this.accountHolder.id());
        assertEquals(DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void shouldMaintainBalanceOnUnauthorizedDeposit() {
        depositAs(this.accountHolder.id());
        depositAs(INVALID_HOLDER_ID);
        assertEquals(DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void shouldDecreaseBalanceOnValidWithdrawal() {
        depositAs(this.accountHolder.id());
        withdrawAs(this.accountHolder.id());
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void shouldIgnoreUnauthorizedWithdrawal() {
        depositAs(this.accountHolder.id());
        withdrawAs(INVALID_HOLDER_ID);
        assertEquals(DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }
}
