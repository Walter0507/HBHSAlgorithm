package com.hbhs.algorithm.leecode;

/**
 * <b>Linked List Cycle II </b><br>
 * <br>
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null. <br>
 * Follow up: <br>
 * Can you solve it without using extra space? <br>
 * 
 * @author walter.xu
 *
 */
public class LinkListCycleII {
	public static void main(String[] args) {

	}
	/**
	 * 1 判断是否有环，没有则返回空<BR>
	 * 2 可以通过数学验证，如果一个链表的环长度为b, 那么可以肯定，一快一慢两个指针的交接点i肯定满足：i=kn(k为正整数)，即交接点为环长度的倍数<BR>
	 * 3 由此可知，通过这种方式，我们可以分别定义首节点以及匹配到的i节点，分别查找下一步，知道找到共同的节点，那么这个节点即是环的起点
	 * @param head
	 * @return
	 */
	public static ListNode solution(ListNode head) {
		if (head==null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast!=null&&fast.next!=null){
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				break;
			}
		}
		if (fast==null||fast.next==null) {
			return null;
		}
		slow = head;
		while(slow!=fast){
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
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
