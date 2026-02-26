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
        if (this.isFull()) {
            this.head = (this.head + 1) % this.elements.length;
        } else {
            this.size++;
        }
        this.elements[this.tail] = value;
        this.tail = (this.tail + 1) % this.elements.length;
    }

    @Override
    public int dequeue() {
        this.checkNotEmpty();
        int value = this.elements[head];
        this.head = (this.head + 1) % this.elements.length;
        this.size--;
        return value;
    }

    @Override
    public int peek() {
        this.checkNotEmpty();
        return this.elements[this.head];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.elements.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    private void checkNotEmpty() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
    }
}
