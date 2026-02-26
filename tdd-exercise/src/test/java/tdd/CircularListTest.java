package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularQueue queue;

    @BeforeEach
    void setUp() {
        queue = new CircularQueueImpl(3);
    }

    @Test
    void capacityMustBePositive() {
        assertThrows(IllegalArgumentException.class,
                () -> new CircularQueueImpl(0));
    }

    @Test
    void newQueueShouldBeEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertEquals(3, queue.capacity());
    }

    @Test
    void enqueueAndDequeueShouldFollowFifo() {
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(3);

        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void enqueueWhenFullShouldOverwriteOldest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(3, queue.size());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
    }

    @Test
    void shouldHandleWrapAroundCorrectly() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());

        queue.enqueue(4);

        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
    }

    @Test
    void peekShouldReturnOldestWithoutRemoving() {
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(10, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    void dequeueOnEmptyShouldThrow() {
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    @Test
    void peekOnEmptyShouldThrow() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }
}
