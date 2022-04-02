package com.example.stack;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @org.junit.jupiter.api.Test
    void push() {
        Stack<String> my_stack = new Stack<String>(1);
        try {
            my_stack.push("a");
            Assertions.assertEquals(my_stack.peek(), "a");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void pop() {
        Stack<String> my_stack = new Stack<String>(1);
        try {
            my_stack.push("a");
            Assertions.assertEquals( my_stack.pop(), "a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void peek() {
    }

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        Stack<String> my_stack = new Stack<String>(0);
        try {
            Assertions.assertTrue(my_stack.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void isFull() {
        Stack<String> my_stack = new Stack<String>(2);
        try {
            my_stack.push("a");
            my_stack.push("b");
            Assertions.assertTrue(my_stack.isFull());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}