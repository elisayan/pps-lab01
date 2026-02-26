package tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a MinMaxStack that supports retrieving min and max in constant time
 * Uses two auxiliary stacks to track minimum and maximum values at each state
 */
public class MinMaxStackImpl implements MinMaxStack {

    private final List<Integer> stack;
    private final List<Integer> minStack;
    private final List<Integer> maxStack;

    public MinMaxStackImpl() {
        this.stack = new ArrayList<>();
        this.minStack = new ArrayList<>();
        this.maxStack = new ArrayList<>();
    }

    @Override
    public void push(int value) {
        this.stack.add(value);

        if (this.minStack.isEmpty() || value <= this.minStack.get(this.minStack.size() - 1)) {
            this.minStack.add(value);
        } else {
            this.minStack.add(this.minStack.get(this.minStack.size() - 1));
        }

        if (this.maxStack.isEmpty() || value >= this.maxStack.get(this.maxStack.size() - 1)) {
            this.maxStack.add(value);
        } else {
            this.maxStack.add(this.maxStack.get(this.maxStack.size() - 1));
        }
    }

    @Override
    public int pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot pop from empty stack");
        }

        int value = this.stack.remove(this.stack.size() - 1);
        this.minStack.remove(this.minStack.size() - 1);
        this.maxStack.remove(this.maxStack.size() - 1);

        return value;
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
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot get min from empty stack");
        }
        return this.minStack.get(this.minStack.size() - 1);
    }

    @Override
    public int getMax() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot get max from empty this.stack");
        }
        return this.maxStack.get(this.maxStack.size() - 1);
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