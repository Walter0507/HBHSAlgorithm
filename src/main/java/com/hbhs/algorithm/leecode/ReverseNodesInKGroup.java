package com.hbhs.algorithm.leecode;


/**
 * <B>Reverse Nodes in k-Group</B><BR>
 * <BR>
 * <BR>
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list. <BR>
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is. <BR>
 * You may not alter the values in the nodes, only nodes itself may be changed. <BR>
 * Only constant memory is allowed. <BR>
 * For example, <BR>
 * Given this linked list: 1->2->3->4->5 <BR>
 * For k = 2, you should return: 2->1->4->3->5 <BR>
 * For k = 3, you should return: 3->2->1->4->5 <BR>
 * <BR>
 * 
 * @author walter.xu
 *
 */
public class ReverseNodesInKGroup {
	public static void main(String[] args) {
		ListNode a = new ListNode(1).set(new ListNode(2).set(new ListNode(3)
		.set(new ListNode(4).set(new ListNode(5).set(new ListNode(6).
				set(new ListNode(7).set(new ListNode(8).set(new ListNode(9)))))))));
		System.out.println(solution(a,3));
	}

	public static ListNode solution(ListNode head, int k){
		ListNode root = new ListNode(0);
		ListNode current = root;
		ListNode start = head;
		ListNode last = findNextK(start, k);
		ListNode nextStart = null;
		while(last!=null){
			nextStart = last.next;
			// start to end
			int index = k;
			ListNode temp = null;
			while(index>0){
				temp = start.next;
				start.next = current.next;
				current.next = start;
				index--;
				start = temp;
			}
			while(index !=k){
				current = current.next;
				index++;
			}
			
			start = nextStart;
			last = findNextK(start, k);
		}
		if (start!=null) {
			current.next = start;
		}else{
			current.next = null;
		}
		return root.next;
	}
	
	public static ListNode findNextK(ListNode node, int k){
		ListNode temp = node;
		while(k>1){
			if (temp==null) {
				return null;
			}
			temp = temp.next;
			k--;
		}
		return temp;
	}
	
	public static class ListNode {
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
				value +=","+next;
			}
			return value;
		}
	}
	
}
