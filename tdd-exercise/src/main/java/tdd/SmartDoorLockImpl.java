package tdd;

public class SmartDoorLockImpl implements SmartDoorLock{
    private static final int DEFAULT_PIN = 0;
    private int pin;

    public int getPin() {
        return pin;
    }

    public SmartDoorLockImpl() {
        this.pin = DEFAULT_PIN;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {

    }

    @Override
    public void lock() {

    }

    @Override
    public boolean isLocked() {
        return false;
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
