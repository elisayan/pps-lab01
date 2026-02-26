package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    private MinMaxStack stack;

    @BeforeEach
    void setUp() {
        stack = new MinMaxStackImpl();
    }

    @Test
    void newStackShouldBeEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void popOnEmptyStackShouldThrow() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void peekOnEmptyStackShouldThrow() {
        assertThrows(IllegalStateException.class, () -> stack.peek());
    }

    @Test
    void getMinOnEmptyStackShouldThrow() {
        assertThrows(IllegalStateException.class, () -> stack.getMin());
    }

    @Test
    void getMaxOnEmptyStackShouldThrow() {
        assertThrows(IllegalStateException.class, () -> stack.getMax());
    }

    @Test
    void singleElementShouldBeMinAndMax() {
        stack.push(10);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
        assertEquals(10, stack.getMin());
        assertEquals(10, stack.getMax());
    }

    @Test
    void pushAndPopShouldFollowLifo() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void increasingSequenceShouldUpdateMaxCorrectly() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(1, stack.getMin());
        assertEquals(3, stack.getMax());

        stack.pop(); // removes 3
        assertEquals(2, stack.getMax());
    }

    @Test
    void decreasingSequenceShouldUpdateMinCorrectly() {
        stack.push(3);
        stack.push(2);
        stack.push(1);

        assertEquals(1, stack.getMin());
        assertEquals(3, stack.getMax());

        stack.pop(); // removes 1
        assertEquals(2, stack.getMin());
    }

    @Test
    void mixedSequenceShouldMaintainCorrectMinAndMax() {
        stack.push(5);
        stack.push(1);
        stack.push(4);
        stack.push(2);

        assertEquals(1, stack.getMin());
        assertEquals(5, stack.getMax());

        stack.pop(); // 2
        stack.pop(); // 4

        assertEquals(1, stack.getMin());

        stack.pop(); // 1

        assertEquals(5, stack.getMin());
        assertEquals(5, stack.getMax());
    }

    @Test
    void duplicatesShouldBeHandledCorrectly() {
        stack.push(2);
        stack.push(1);
        stack.push(1);
        stack.push(3);

        assertEquals(1, stack.getMin());

        stack.pop(); // 3
        assertEquals(1, stack.getMin());

        stack.pop(); // 1
        assertEquals(1, stack.getMin());

        stack.pop(); // second 1
        assertEquals(2, stack.getMin());
    }

    @Test
    void interleavedPushPopShouldKeepConsistency() {
        stack.push(3);
        stack.push(1);
        stack.pop(); // removes 1
        stack.push(2);

        assertEquals(2, stack.peek());
        assertEquals(2, stack.getMin());
        assertEquals(3, stack.getMax());
    }

    @Test
    void shouldHandleNegativeNumbers() {
        stack.push(-5);
        stack.push(-1);
        stack.push(-10);

        assertEquals(-10, stack.getMin());
        assertEquals(-1, stack.getMax());
    }
}