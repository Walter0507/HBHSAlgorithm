package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

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
public class MinStack {
	private List<element> list = new ArrayList<element>();
	private element minElement = null;
	public static void main(String[] args) {

		MinStack stack = new MinStack();
		stack.push(5);
		stack.push(1);
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.push(3);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.push(9);
		
		stack.push(6);
		stack.push(8);
		stack.push(2);
		stack.push(20);
		stack.push(-1);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
		stack.pop();
		System.out.println("MinValue: "+ stack.getMin()+", "+stack);
	}
	public void push(int x) {
		element current = new element(x);
		list.add(current);
		// 为空时 至二级赋值
		if (minElement == null) {
			minElement = current;
			return ;
		}
		// 如果当前值比栈中最小值还小，则直接设置最小值即可
		if (minElement.getKey() >= x) {
			minElement.setNextSmallElement(current);
			current.setNextBigElement(minElement);
			minElement = current;
			return ;
		}
		// 否则一步步寻找，知道找到一个指定节点，是x大于该节点，但是小于该节点的big父节点
		element tempElement = minElement;
		while(tempElement.getNextBigElement()!=null&&tempElement.getNextBigElement().getKey() < x){
			tempElement = tempElement.getNextBigElement();
		}
		if (tempElement.getNextBigElement()!=null) {
			tempElement.getNextBigElement().setNextSmallElement(current);
			current.setNextBigElement(tempElement.getNextBigElement());
		}
		tempElement.setNextBigElement(current);
		current.setNextSmallElement(tempElement);
    }

    public void pop() {
    	if (list.size()==0) {
			return ;
		}
    	element current = list.get(list.size()-1);
    	if (current == minElement) {
			minElement = current.getNextBigElement();
			if (minElement!=null) {
				minElement.setNextSmallElement(null);
			}
			
		}else{
			element pre = current.getNextBigElement();
			element tail = current.getNextSmallElement();
			if (pre!=null) {
				pre.setNextSmallElement(tail);
			}
			if (tail!=null) {
				tail.setNextBigElement(pre);
			}
			
		}
    	list.remove(list.size()-1);
    	System.out.println("After POP: "+this);
    }

    public int top() {
        return list.get(list.size()-1).getKey();
    }

    public int getMin() {
        return minElement==null?0:minElement.getKey();
    }
    public String toString(){
    	StringBuilder str = new StringBuilder("[");
    	if (list==null||list.size()==0) {
			
		}else{
			str.append(list.get(0).getKey());
			for (int i = 1; i < list.size(); i++) {
				str.append(", ").append(list.get(i).getKey());
			}
		}
    	str.append("]");
    	return str.toString();
    }
}
class element {
	private int key;
	private element nextBigElement;
	private element nextSmallElement;
	public element(int key){
		this.key = key;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public element getNextBigElement() {
		return nextBigElement;
	}
	public void setNextBigElement(element nextBigElement) {
		this.nextBigElement = nextBigElement;
	}
	public element getNextSmallElement() {
		return nextSmallElement;
	}
	public void setNextSmallElement(element nextSmallElement) {
		this.nextSmallElement = nextSmallElement;
	}
	
}