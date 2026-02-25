package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock lock;
    private static final int VALID_PIN = 8888;
    private static final int WRONG_PIN = 9999;
    private static final int INVALID_PIN_SHORT = 123;
    private static final int INVALID_PIN_LONG = 12345;

    @BeforeEach
    void setUp() {
        this.lock = new SmartDoorLockImpl();
    }

    private void setValidPin() {
        this.lock.setPin(VALID_PIN);
        this.lock.lock();
    }

    private void makeLockBlocked(int lock, SmartDoorLock lock1) {
        for (int i = 0; i < lock; i++) {
            lock1.unlock(WRONG_PIN);
        }
    }

    @Test
    void doorShouldBeUnlockedAfterCreation() {
        assertFalse(this.lock.isLocked());
        assertFalse(this.lock.isBlocked());
    }

    @Test
    void shouldSetPinWhenNotLocked() {
        this.lock.setPin(VALID_PIN);
        assertFalse(this.lock.isLocked());
    }

    @Test
    void shouldThrowExceptionWhenSettingInvalidPin() {
        assertThrows(IllegalArgumentException.class, () -> this.lock.setPin(INVALID_PIN_LONG));
        assertThrows(IllegalArgumentException.class, () -> this.lock.setPin(INVALID_PIN_SHORT));
    }

    @Test
    void shouldLockOnAfterPinIsSet() {
        this.setValidPin();
        assertTrue(this.lock.isLocked());
    }

    @Test
    void shouldUnlockAfterDigitCorrectPin() {
        this.setValidPin();
        this.lock.unlock(VALID_PIN);

        assertFalse(this.lock.isLocked());
        assertEquals(0, this.lock.getFailedAttempts());
    }

    @Test
    void shouldNotUnlockWithWrongPin() {
        this.setValidPin();
        int initial = this.lock.getFailedAttempts();
        this.lock.unlock(WRONG_PIN);
        assertTrue(this.lock.isLocked());
        assertEquals(initial + 1, this.lock.getFailedAttempts());
    }

    @Test
    void shouldBlockAfterMaxFailedAttempts() {
        this.setValidPin();
        this.makeLockBlocked(this.lock.getMaxAttempts(), this.lock);
        assertTrue(this.lock.isBlocked());
        assertTrue(this.lock.isLocked());
    }

    @Test
    void shouldNotUnlockWhenBlocked() {
        this.setValidPin();
        this.makeLockBlocked(this.lock.getMaxAttempts(), this.lock);

        this.lock.unlock(VALID_PIN);
        assertTrue(this.lock.isLocked());
        assertTrue(this.lock.isBlocked());
    }

    @Test
    void shouldResetCompletely(){
        this.setValidPin();
        this.makeLockBlocked(this.lock.getMaxAttempts() - 1, this.lock);
        this.lock.reset();

        assertFalse(this.lock.isLocked());
        assertFalse(this.lock.isBlocked());
        assertEquals(0, this.lock.getFailedAttempts());
    }
}
