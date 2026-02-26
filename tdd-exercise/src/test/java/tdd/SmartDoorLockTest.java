package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLockImpl lock;
    private final int newPin = 1234;
    private final int defaultPin = 0;

    @BeforeEach
    void beforeEach() {
        this.lock = new SmartDoorLockImpl();
        this.lock.setPin(defaultPin);
    }

//    @Test
//    void testDefaultPin() {
//        assertEquals(this.lock.getPin(), this.defaultPin);
//    }

    @Test
    void testPinSetting() {
        this.lock.setPin(newPin);
        assertEquals(this.lock.getPin(), newPin);
    }

    @Test
    void testDoorUnlock() {
        this.lock.unlock(defaultPin);
        assertFalse(this.lock.isLocked());
    }

    @Test
    void testDoorLock() {
        this.lock.lock();
        assertTrue(this.lock.isLocked());
    }


}
