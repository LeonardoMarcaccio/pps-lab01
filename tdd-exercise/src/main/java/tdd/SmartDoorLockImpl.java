package tdd;

public class SmartDoorLockImpl implements SmartDoorLock{
    private static final int DEFAULT_PIN = 0;
    private static final boolean DEFAULT_LOCK_STATUS = false;
    private int pin;
    private boolean isLockedStatus;

    public int getPin() {
        return pin;
    }

    public SmartDoorLockImpl() {
        this.pin = DEFAULT_PIN;
        this.isLockedStatus = DEFAULT_LOCK_STATUS;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.isLockedStatus) {
           throw new IllegalStateException("Door was already open");
        } else if (this.pin == pin) {
            this.isLockedStatus = false;
        }
    }

    @Override
    public void lock() {

    }

    @Override
    public boolean isLocked() {
        return this.isLockedStatus;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }

}
