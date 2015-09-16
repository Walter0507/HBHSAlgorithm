package com.hbhs.algorithm.leecode;

import java.util.Stack;

/**
 * <b>Min Stack</b><br>
 * <br>Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <br>
 * <br>push(x) -- Push element x onto stack.
 * <br>pop() -- Removes the element on top of the stack.
 * <br>top() -- Get the top element.
 * <br>getMin() -- Retrieve the minimum element in the stack.
 * <br>
 * 
 * @author walter.xu
 *
 */
public class MinStack2 {
	private Stack<Integer> valueStack = new Stack<Integer>();
	private Stack<Integer> minStack  = new Stack<Integer>();
	public static void main(String[] args) {

		MinStack2 stack = new MinStack2();
		stack.push(512);
		stack.push(-1024);
		stack.push(-1024);
		stack.push(512);
		System.out.println(stack);
		stack.pop();
		System.out.println(stack.getMin());
		stack.pop();
		System.out.println(stack.getMin());
		stack.pop();
		System.out.println(stack.getMin());
	}
	public void push(int x) {
		valueStack.push(x);
		if (minStack.isEmpty()||(minStack.peek()>=x)) {
			minStack.push(x);
		}
    }

    public void pop() {
    	if (!valueStack.isEmpty()) {
    		System.out.println(minStack.peek()+", "+valueStack.peek());
			if (minStack.peek().intValue() == valueStack.peek().intValue()) {
				minStack.pop();
			}
			valueStack.pop();
		}
    }

    public int top() {
    	if (valueStack.isEmpty()) {
			return 0;
		}
        return valueStack.peek();
    }

    public int getMin() {
    	if (minStack.isEmpty()) {
			return 0;
		}
        return minStack.peek();
    }

}
