package tdd;

public class CircularQueueImpl implements CircularQueue {

    private final int[] elements;
    private int head;
    private int tail;
    private int size;

    public CircularQueueImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.elements = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(int value) {
        elements[tail] = value;

        if (isFull()) {
            head = (head + 1) % elements.length;
        } else {
            size++;
        }

        tail = (tail + 1) % elements.length;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        int value = elements[head];
        head = (head + 1) % elements.length;
        size--;
        return value;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return elements.length;
    }
}
