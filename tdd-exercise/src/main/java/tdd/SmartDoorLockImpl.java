package tdd;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public class SmartDoorLockImpl implements SmartDoorLock{
    private static final boolean DEFAULT_LOCK_STATUS = false;
    private Optional<Integer> pin;
    private boolean isLockedStatus;

    public int getPin() {
        return this.pin.orElseThrow(pinNotSetException());
    }

    public SmartDoorLockImpl() {
        this.pin = Optional.empty();
    }

    @Override
    public void setPin(int pin) {
        this.pin = Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        if (this.isLockedStatus) {
           throw new IllegalStateException("Door was already unlocked");
        } else if (pin == this.pin.orElseThrow(pinNotSetException())) {
            this.isLockedStatus = false;
        }
    }

    @Override
    public void lock() {
        if (!this.isLockedStatus) {
            this.isLockedStatus = true;
        } else {
            throw new IllegalStateException("Door was already locked");
        }
        if (this.pin.isEmpty()) {
            throw pinNotSetException().get();
        }
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

    private Supplier<NoSuchElementException> pinNotSetException() {
        return () -> new NoSuchElementException("Pin wasn't set");
    }

}
