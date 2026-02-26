package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLockImpl lock;
    private static final int DEFAULT_PIN = 1111;
    private static final int NEW_PIN = 1234;
    private static final int WRONG_PIN = 2222;
    private static final String DOOR_ALREADY_UNLOCKED = "The door was already unlocked";
    private static final String DOOR_ALREADY_LOCKED = "The door was already locked";
    private static final String PIN_NOT_SET = "Pin wasn't set";

    /* QUESTION TO THE PROFESSOR
     * The main issue I found during this exercise was how to decide if I can call a method
     * or not inside the beforeEach(). How should I approach this?
     *
     * I.E.
     * By Default the value of the Pin for the lock is not given so it needs to be set
     * everytime the lock is created, this means that each test needs to be previously
     * set a Pin, creating code duplication.
     * But, due to the fact that setPin() is a function to be tested, sounds to me wrong
     * to put it inside the beforeEach() method
     *
     * To avoid this my choice was to repeat the line of code for a better readability
     */

    @BeforeEach
    void beforeEach() {
        this.lock = new SmartDoorLockImpl();
    }

    @Test
    void testDefaultLock() {
        assertFalse(this.lock.isLocked());
        assertFalse(this.lock.isBlocked());
        Exception exception = assertThrows(NoSuchElementException.class, () -> this.lock.getPin());
        assertEquals(PIN_NOT_SET, exception.getMessage());
    }

    @Test
    void testPinSetting() {
        this.lock.setPin(DEFAULT_PIN);
        assertEquals(DEFAULT_PIN, this.lock.getPin());
    }

    @Test
    void testDoorUnlock() {
        this.lock.setPin(DEFAULT_PIN);
        Exception exception = assertThrows(
            IllegalStateException.class,
            () -> this.lock.unlock(DEFAULT_PIN)
        );
        assertEquals(DOOR_ALREADY_UNLOCKED, exception.getMessage());
        assertFalse(this.lock.isLocked());
    }

    @Test
    void testDoorLock() {
        this.lock.setPin(DEFAULT_PIN);
        this.lock.lock();
        assertTrue(this.lock.isLocked());
    }

    @Test
    void testDoorLockUnlock() {
        this.lock.setPin(DEFAULT_PIN);
        this.lock.lock();
        this.lock.unlock(DEFAULT_PIN);
        assertFalse(this.lock.isLocked());
    }

    @Test
    void testWrongPinUnlock() {
        this.lock.setPin(DEFAULT_PIN);
        this.lock.lock();
        this.lock.unlock(WRONG_PIN);
        assertEquals(1, this.lock.getFailedAttempts());
    }

    @Test
    void testWrongUntilBlock() {
        this.lock.setPin(DEFAULT_PIN);
        this.lock.lock();
        for (int i = 0; i < this.lock.getMaxAttempts(); i++) {
            this.lock.unlock(WRONG_PIN);
        }
        assertTrue(this.lock.isBlocked());
    }
}
