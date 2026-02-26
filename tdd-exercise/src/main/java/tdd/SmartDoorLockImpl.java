package tdd;

/**
 * Implementation of a smart door lock with PIN protection
 */
public class SmartDoorLockImpl implements SmartDoorLock {

    public static final int MAX_ATTEMPTS = 3;
    private static final int PIN_LENGTH = 4;

    private boolean locked = false;
    private Integer pin = null;
    private int failedAttempts = 0;

    @Override
    public void setPin(int pin) {
        if (this.isLocked()) {
            throw new IllegalStateException("Cannot set PIN while locked");
        }
        if (this.isBlocked()) {
            throw new IllegalStateException("Cannot set PIN while blocked");
        }
        if (String.valueOf(pin).length() != PIN_LENGTH) {
            throw new IllegalArgumentException("PIN must be " + PIN_LENGTH + " digits");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == null) {
            throw new IllegalStateException("PIN not set");
        }

        if (pin == this.pin && !this.isBlocked()) {
            this.locked = false;
            this.failedAttempts = 0;
        } else {
            this.failedAttempts++;
        }
    }

    @Override
    public void lock() {
        if (this.pin == null) {
            throw new IllegalStateException("Cannot lock: PIN not set");
        }
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.failedAttempts >= MAX_ATTEMPTS;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.pin = null;
        this.locked = false;
        this.failedAttempts = 0;
    }
}
