package tdd;

import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MinMaxStackImpl implements MinMaxStack{

    private Stack<Integer> stack;

    public Stack<Integer> getStack() {
        Object clone = this.stack.clone();
        return null;
    }

    public MinMaxStackImpl() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(int value) {
        this.stack.push(value);
    }

    @Override
    public int pop() {
        return 0;
    }

    @Override
    public int peek() {
        return 0;
    }

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
