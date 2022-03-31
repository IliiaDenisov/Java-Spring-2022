package com.example.stack;

public class Stack<T> {
    private T arr[];
    private int top;
    private int capacity;

    public Stack(int size)
    {
        arr = (T[]) new Object[size];
        capacity = size;
        top = -1;
    }

    // Utility function to add an element `x` to the stack
    public void push(T x) throws Exception {
        if (isFull())
        {
            throw new Exception("Overflow\nProgram Terminated\n");

        }

        System.out.println("Inserting " + x);
        arr[++top] = x;
    }

    // Utility function to pop a top element from the stack
    public T pop() throws Exception {
        // check for stack underflow
        if (isEmpty())
        {
            throw new Exception("Underflow\nProgram Terminated");
        }

        System.out.println("Removing " + peek());

        // decrease stack size by 1 and (optionally) return the popped element
        return arr[top--];
    }

    // Utility function to return the top element of the stack
    public T peek() throws Exception {
        if (!isEmpty()) {
            return arr[top];
        }
        else {
            throw new Exception("The Stack is empty\nProgram Terminated");
        }
    }

    // Utility function to return the size of the stack
    public int size() {
        return top + 1;
    }

    // Utility function to check if the stack is empty or not
    public boolean isEmpty() {
        return top == -1;               // or return size() == 0;
    }

    // Utility function to check if the stack is full or not
    public boolean isFull() {
        return top == capacity - 1;     // or return size() == capacity;
    }
}
