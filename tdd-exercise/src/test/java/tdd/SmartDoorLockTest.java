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
        lock = new SmartDoorLockImpl();
    }

    @Test
    void testDefaultPin() {
        assertEquals(this.lock.getPin(), this.defaultPin);
    }

    @Test
    void testPinSetting() {
        lock.setPin(newPin);
        assertEquals(this.lock.getPin(), newPin);
    }
}
