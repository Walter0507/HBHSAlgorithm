package com.hbhs.algorithm.leecode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author walter.xu
 *
 */
public class CopyListWithRandomPointer {
	public static void main(String[] args) throws InterruptedException {
		// TODO
	}
	
	public static RandomListNode solution2(RandomListNode head){
		if (head==null) {
			return null;
		}
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode root = new RandomListNode(head.label);
		map.put(head, root);
		createNext(root, head, map);
		RandomListNode temp1 = head;
		RandomListNode temp2 = root;
		while(temp1!=null){
			temp2.random = map.get(temp1.random);
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		return root;
	}
	
	private static void createNext(RandomListNode target, RandomListNode last, Map<RandomListNode, RandomListNode> map){
		if (target==null||last==null) {
			return ;
		}
		if (last.next!=null) {
			RandomListNode nextNode = new RandomListNode(last.next.label);
			target.next = nextNode;
			map.put(last.next, nextNode);
			createNext(target.next, last.next, map);
		}
	}
	
	// DEEP COPY
	public static RandomListNode solution1(RandomListNode head) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(head);
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
//		bos.wri
		return (RandomListNode)ois.readObject();
	}

	private static class RandomListNode{
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	}
}
