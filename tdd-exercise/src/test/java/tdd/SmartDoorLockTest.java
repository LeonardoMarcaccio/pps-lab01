package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLockImpl lock;
    private final int newPin = 1234;

    @BeforeEach
    void beforeEach() {
        lock = new SmartDoorLockImpl();
    }

    @Test
    void testPinSetting() {
        lock.setPin(newPin);
        assertEquals(this.lock.getPin(), newPin);
    }
}
