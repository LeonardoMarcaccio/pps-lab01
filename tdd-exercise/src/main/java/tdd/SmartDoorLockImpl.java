package tdd;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public class SmartDoorLockImpl implements SmartDoorLock{
    private static final boolean DEFAULT_LOCK_STATUS = false;
    private static final int MAX_ATTEMPTS = 3;
    private Optional<Integer> pin;
    private boolean isLockedStatus;
    private boolean isBlockedStatus;
    private int failedAttempts;

    public int getPin() {
        return this.pin.orElseThrow(pinNotSetException());
    }

    public SmartDoorLockImpl() {
        this.pin = Optional.empty();
        this.failedAttempts = 0;
        this.isLockedStatus = false;
        this.isBlockedStatus = false;
    }

    @Override
    public void setPin(int pin) {
        if (!this.isBlockedStatus) {
            if (!this.isLockedStatus) {
                this.pin = Optional.of(pin);
            } else {
                throw new IllegalStateException("Door is locked");
            }
        } else {
            throw new IllegalStateException("The door is blocked");
        }
    }

    @Override
    public void unlock(int pin) {
        if (!this.isBlockedStatus) {
            if (this.isLockedStatus) {
                if (pin == this.pin.orElseThrow(pinNotSetException())) {
                    this.isLockedStatus = false;
                } else {
                    this.failedAttempts = this.failedAttempts + 1; //I preferred to be more verbose than use ++ operator
                    if (this.failedAttempts >= MAX_ATTEMPTS) {this.isBlockedStatus = true;}
                }
            } else {
                throw new IllegalStateException("The door was already unlocked");
            }
        } else {
            throw new IllegalStateException("The door is blocked");
        }
    }

    @Override
    public void lock() {
        if (!this.isBlockedStatus) {
            if (!this.isLockedStatus) {
                this.isLockedStatus = true;
            } else {
                throw new IllegalStateException("The door was already locked");
            }
            if (this.pin.isEmpty()) {
                throw pinNotSetException().get();
            }
        } else {
            throw new IllegalStateException("The door is blocked");
        }
    }

    @Override
    public boolean isLocked() {
        return this.isLockedStatus;
    }

    @Override
    public boolean isBlocked() {
        return this.isBlockedStatus;
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
        this.pin = Optional.empty();
        this.isLockedStatus = false;
        this.failedAttempts = 0;
        this.isBlockedStatus = false;
    }

    private Supplier<NoSuchElementException> pinNotSetException() {
        return () -> new NoSuchElementException("Pin wasn't set");
    }

}
