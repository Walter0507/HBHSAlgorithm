package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
	public static void main(String[] args) {
		List<ListNode> lists = new ArrayList<MergeKSortedLists.ListNode>();
		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(7);
		lists.add(list1);
		lists.add(new ListNode(5));
		lists.add(new ListNode(2));
		ListNode list2 = new ListNode(3);
		ListNode list21 = new ListNode(4);
		list2.next=list21;
		ListNode list22 = new ListNode(6);
		list21.next = list22;
		lists.add(list2);
		ListNode result = solution2(lists);
		System.out.println(result);
	}
	
	public static ListNode solution2(List<ListNode> lists){
		if (lists==null||lists.size()==0) {
			return null;
		}
		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i)!=null) {
				queue.add(lists.get(i));
			}
		}
		ListNode head=new ListNode(0);
        ListNode next=head;
        while(!queue.isEmpty()){
        	ListNode nextNode = queue.poll();
        	if (nextNode.next!=null) {
        		queue.add(nextNode.next);
			}
        	next.next = nextNode;
        	next = next.next;
        }
        return head.next;
	}
	
	

	public static ListNode solution(List<ListNode> lists) {
		if (lists==null) {
			return null;
		}
		for (int i = 0; i < lists.size(); ) {
			if (lists.get(i)==null) {
				lists.remove(i);
			}else{
				i++;
			}
		}
		if (lists.size()==0) {
			return null;
		}
		if (lists.size()==1) {
			return lists.get(0);					
		}
		boolean isAsc = isAsc(lists);
		ListNode result = lists.get(0);
		for (int i = 1; i < lists.size(); i++) {
			divideAndMerge(result, lists.get(i), isAsc);
		}
		return result;
	}

	public static void divideAndMerge(ListNode node1, ListNode node2,
			boolean isAsc) {
		ListNode mergedNode = null;
		ListNode currentNode = null;
		while (node1 != null && node2 != null) {
			if (isAsc) {
				if (node1.val < node2.val) {
					if (mergedNode == null) {
						mergedNode = node1;
						currentNode = node1;
					} else {
						currentNode.next = node1;
						currentNode = currentNode.next;
					}
					node1 = node1.next;
				} else {
					if (mergedNode == null) {
						mergedNode = node2;
						currentNode = node2;
					} else {
						currentNode.next = node2;
						currentNode = currentNode.next;
					}
					node2 = node2.next;
				}
			} else {
				if (node1.val > node2.val) {
					if (mergedNode == null) {
						mergedNode = node1;
						currentNode = node1;
					} else {
						currentNode.next = node1;
						currentNode = currentNode.next;
					}
					node1 = node1.next;
				} else {
					if (mergedNode == null) {
						mergedNode = node2;
						currentNode = node2;
					} else {
						currentNode.next = node2;
						currentNode = currentNode.next;
					}
					node2 = node2.next;
				}
			}
		}
		if (node1 != null) {
			currentNode.next = node1;
		}
		if (node2 != null) {
			currentNode.next = node2;
		}
		node1 = mergedNode;
	}

	public static boolean isAsc(List<ListNode> lists) {
		for (int i = 0; i < lists.size(); i++) {
			ListNode current = lists.get(i);
			while (current.next != null) {
				if (current.val > current.next.val) {
					return false;
				} else if (current.val < current.next.val) {
					return true;
				}
				current = current.next;
			}
		}
		return true;
	}

	/**
	 * 
	 * @author walter.xu
	 *
	 */
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
		public String toString(){
			return val+","+next;
		}
	}
}
