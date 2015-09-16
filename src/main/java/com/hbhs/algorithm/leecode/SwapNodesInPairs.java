package com.hbhs.algorithm.leecode;


/**
 * <b>Swap Nodes in Pairs</b><br>
 * <br>
 * <br>Given a linked list, swap every two adjacent nodes and return its head.
 * <br>
 * <br>For example,
 * <br>
 * <br>Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <br>Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * <br>
 * @author walter.xu
 *
 */
public class SwapNodesInPairs {

	public static void main(String[] args) {
		ListNode a = new ListNode(1).set(new ListNode(2).set(new ListNode(3)
		.set(new ListNode(4).set(new ListNode(5).set(new ListNode(6).
				set(new ListNode(7).set(new ListNode(8))))))));
		System.out.println(solution(a));
	}
	
	public static ListNode solution(ListNode head){
		ListNode parent = new ListNode(0);
		ListNode current = parent;
		ListNode temp = head;
		ListNode temp1 = null;
		while(temp!=null&&temp.next!=null){
			current.next = temp.next;
			temp1 = temp.next.next;
			current = current.next;
			current.next = temp;
			current = current.next;
			temp = temp1;
		}
		if (temp!=null) {
			current.next = temp;
		}else{
			current.next = null;
		}
		return parent.next;
	}
	
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
		public ListNode set(ListNode node){
			this.next = node;
			return this;
		}
		public String toString(){
			String value = val+"";
			if (next!=null) {
				value+=","+next;
			}
			return value;
		}
	}
}
