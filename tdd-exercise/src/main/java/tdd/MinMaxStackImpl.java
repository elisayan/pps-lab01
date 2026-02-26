package tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a MinMaxStack that supports retrieving min and max in constant time
 */
public class MinMaxStackImpl implements MinMaxStack {

    private final List<Integer> stack;

    public MinMaxStackImpl() {
        this.stack = new ArrayList<>();
    }

    @Override
    public void push(int value) {
        this.stack.add(value);
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot pop from empty stack");
        }
        return this.stack.remove(this.stack.size() - 1);
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot peek empty stack");
        }
        return this.stack.get(this.stack.size() - 1);
    }

    @Override
    public int getMin() {
        return this.stack.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new IllegalStateException("Cannot get min from empty stack"));
    }

    @Override
    public int getMax() {
        return this.stack.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("Cannot get max from empty stack"));
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}