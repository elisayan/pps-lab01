package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  <br>
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  <br>
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Adds a value to the queue
     * If the queue is full, the oldest element is overwritten
     *
     * @param value the value to add
     */
    void enqueue(int value);

    /**
     * Removes and returns the oldest element in the queue
     *
     * @return the removed element
     * @throws IllegalStateException if the queue is empty
     */
    int dequeue();

    /**
     * Removes and returns the oldest element in the queue
     *
     * @return the removed element
     * @throws IllegalStateException if the queue is empty
     */
    int peek();

    /**
     * Checks whether the queue contains no elements
     *
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Checks whether the queue has reached its capacity
     *
     * @return true if full, false otherwise
     */
    boolean isFull();

    /**
     * Returns the number of elements currently stored
     *
     * @return the current size
     */
    int size();

    /**
     * Returns the fixed maximum capacity of the queue
     *
     * @return the capacity
     */
    int capacity();
}
