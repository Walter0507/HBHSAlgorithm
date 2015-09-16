package com.hbhs.algorithm.leecode;

/**
 * <b>Binary Tree Maximum Path Sum</b><br>
 * <br>
 * <br>
 * Given a binary tree, find the maximum path sum. <br>
 * <br>
 * The path may start and end at any node in the tree. <br>
 * <br>
 * <br>
 * <br>
 * 
 * @author walter.xu
 *
 */
public class BinaryTreeMaximumPathSum {
	public static void main(String[] args) {
		TreeNode node = new TreeNode(-1);
		node.left = new TreeNode(4);
		node.right = new TreeNode(-1);
		node.right.left = new TreeNode(-1);
		node.right.right = new TreeNode(4);
		
		System.out.println(solution(node));
	}
	
	public static int solution(TreeNode root){
		if (root==null) {
			return 0;
		}
		int[] maxCurrent = caculateMaxBelowNode(root);
		
		return maxCurrent[0]>maxCurrent[1]?maxCurrent[0]:maxCurrent[1];
	}
	
	public static int[] caculateMaxBelowNode(TreeNode node){
		if (node==null) {
			return null;
		}
		int[] maxCurrentLeftNode = caculateMaxBelowNode(node.left);
		int[] maxCurrentRightNode = caculateMaxBelowNode(node.right);
		int maxCurrent = node.val, maxChoose = 0;
		if (maxCurrentLeftNode!=null) {
			maxCurrent += maxCurrentLeftNode[0];
			if (maxCurrentLeftNode[0] > 0) {
				maxChoose = maxCurrentLeftNode[0]>maxChoose?maxCurrentLeftNode[0]:maxChoose;
			}
		}
		if (maxCurrentRightNode!=null) {
			maxCurrent += maxCurrentRightNode[0];
			if (maxCurrentRightNode[0] > 0) {
				maxChoose = maxCurrentRightNode[0]>maxChoose?maxCurrentRightNode[0]:maxChoose;
			}
		}
		if (maxCurrentLeftNode!=null) {
			maxCurrent = maxCurrent>maxCurrentLeftNode[1]?maxCurrent:maxCurrentLeftNode[1];
		}
		if (maxCurrentRightNode!=null) {
			maxCurrent = maxCurrent>maxCurrentRightNode[1]?maxCurrent:maxCurrentRightNode[1];
		}
		
		System.out.println(node+", currentChoose: "+(maxChoose+node.val)+", maxCurrent: "+maxCurrent);
		return new int[]{maxChoose+node.val, maxCurrent};
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
		public String toString(){
			return "["+val+"]";
		}
	}
}
