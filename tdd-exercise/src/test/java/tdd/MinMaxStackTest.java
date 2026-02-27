package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinMaxStackTest {

    private MinMaxStackImpl minMaxStack;
    private static final int[] SORTED_VALUES = {0,1,2,3,4,5};

    @BeforeEach
    void beforeEach() {
        this.minMaxStack = new MinMaxStackImpl();
    }

    @Test
    void testPush() {
        this.minMaxStack.push(SORTED_VALUES[0]);
        //assertEquals(0, this.minMaxStack.getStack().);
    }
}