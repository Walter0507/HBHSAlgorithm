package com.hbhs.algorithm.leecode;

/**
 * <b>Add Two Numbers</b><br>
 * <br>
 * <br>You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <br>
 * <br>Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * <br>Output: 7 -> 0 -> 8
 * <br>
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class AddTwoNumbers {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2, new ListNode(6, new ListNode(5)));
		ListNode l2 = new ListNode(2, new ListNode(6, new ListNode(4)));
		System.out.println(solution1(l1, l2));
	}
	
	public static ListNode solution1(ListNode l1, ListNode l2){
		if (l1 == null&&l2==null) {
			return null;
		}
		if (l1==null||l2==null) {
			return l1==null?l2:l1;
		}
		int sum = 0;
		int nextAdd = 0;
		int currentValue = 0;
		ListNode result = new ListNode(-1);
		
		ListNode node1 = l1;
		ListNode node2 = l2;
		ListNode temp = result;
		while(node1!=null||node2!=null){
			sum = nextAdd;
			if (node1!=null) {
				sum += node1.val;
				node1 = node1.next;
			}
			if (node2!=null) {
				sum += node2.val;
				node2 = node2.next;
			}
			nextAdd = sum / 10;
			currentValue = sum %10;
			ListNode node = new ListNode(currentValue);
			temp.next = node;
			temp = node;
		}
		if (nextAdd>0) {
			temp.next = new ListNode(nextAdd);
		}
		return result.next;
	}
}
class ListNode {
	int val;
	ListNode next = null;
	ListNode(int x) {
		val = x;
	}
	ListNode(int x, ListNode next){
		this.val = x;
		this.next = next;
	}
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(val);
		if (next!=null) {
			str.append(", ").append(next);
		}
		return str.toString();
	}
}