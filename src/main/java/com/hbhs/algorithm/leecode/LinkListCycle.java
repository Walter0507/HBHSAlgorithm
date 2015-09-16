package com.hbhs.algorithm.leecode;

/**
 * <b>Linked List Cycle </b><br>
 * <br>
 * Given a linked list, determine if it has a cycle in it. <br>
 * Follow up: <br>
 * Can you solve it without using extra space? <br>
 * 
 * @author walter.xu
 *
 */
public class LinkListCycle {
	public static void main(String[] args) {

	}

	public static boolean solution(ListNode head) {
		if (head==null) {
			return false;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(true){
			if (fast==null||fast.next==null) {
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
	}
	@SuppressWarnings("unused")
	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
