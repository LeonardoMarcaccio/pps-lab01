package tdd;

import java.util.NoSuchElementException;

public class SmartDoorLockImpl implements SmartDoorLock{
    private static final boolean DEFAULT_LOCK_STATUS = false;
    private static final int INVALID_PIN_VALUE = -1;
    private static final int MAX_ATTEMPTS = 3;
    private int pin;
    private boolean isPinSetStatus;
    private boolean isLockedStatus;
    private boolean isBlockedStatus;
    private int failedAttempts;

    public int getPin() {
        if (!this.isPinSetStatus) {
            throw this.pinNotSetException();
        }
        return this.pin;
    }

    public boolean isPinSet() {
        return this.isPinSetStatus;
    }

    public SmartDoorLockImpl() {
        this.pin = INVALID_PIN_VALUE;
        this.failedAttempts = 0;
        this.isPinSetStatus = false;
        this.isLockedStatus = false;
        this.isBlockedStatus = false;
    }

    @Override
    public void setPin(int pin) {
        if (!this.isBlockedStatus) {
            if (!this.isLockedStatus) {
                this.pin = pin;
                this.isPinSetStatus = true;
            } else {
                throw this.doorCurrentlyLockedException();
            }
        } else {
            throw this.doorCurrentlyBlockedException();
        }
    }

    @Override
    public void unlock(int pin) {
        if (!this.isBlockedStatus) {
            if (this.isLockedStatus) {
                if (this.isPinSetStatus) {
                    if (pin == this.pin) {
                        this.isLockedStatus = false;
                    } else {
                        this.failedAttempts = this.failedAttempts + 1; //I preferred to be more verbose than use ++ operator
                        if (this.failedAttempts >= MAX_ATTEMPTS) {this.isBlockedStatus = true;}
                    }
                } else {
                    throw this.pinNotSetException();
                }
            } else {
                throw this.doorAlreadyUnlockedException();
            }
        } else {
            throw this.doorCurrentlyBlockedException();
        }
    }

    @Override
    public void lock() {
        if (!this.isBlockedStatus) {
            if (!this.isLockedStatus) {
                this.isLockedStatus = true;
            } else {
                throw this.doorAlreadyLockedException();
            }
            if (!this.isPinSetStatus) {
                throw this.pinNotSetException();
            }
        } else {
            throw this.doorCurrentlyBlockedException();
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
        this.pin = INVALID_PIN_VALUE;
        this.failedAttempts = 0;
        this.isPinSetStatus = false;
        this.isLockedStatus = false;
        this.isBlockedStatus = false;
    }

    private NoSuchElementException pinNotSetException() {
        return new NoSuchElementException("Pin wasn't set");
    }

    private IllegalStateException doorAlreadyUnlockedException() {
        return new IllegalStateException("The door was already unlocked");
    }

    private IllegalStateException doorAlreadyLockedException() {
        return new IllegalStateException("The door was already locked");
    }

    private IllegalStateException doorCurrentlyBlockedException() {
        return new IllegalStateException("Door is blocked");
    }

    private IllegalStateException doorCurrentlyLockedException() {
        return new IllegalStateException("Door is locked");
    }
}
