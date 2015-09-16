package com.hbhs.algorithm.leecode;

import java.util.Stack;

/**
 * <B>Binary Search Tree Iterator<b><br>
 * <br>
 * <br>Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <br>
 * <br>Calling next() will return the next smallest number in the BST.
 * <br>
 * <br>Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class BinarySearchTreeIterator {
	private Stack<TreeNode> stack = new Stack<TreeNode>();
	private TreeNode currentNode = null;
	public BinarySearchTreeIterator(TreeNode root){
		if (root!=null) {
			stack.push(root);
			currentNode = root;
			while(currentNode.left!=null){
				currentNode = currentNode.left;
				stack.push(currentNode);
			}
		}
	}
	public boolean hasNext(){
		if (stack.isEmpty()) {
			return false;
		}
		return true;
	}
	public int next(){
		currentNode = stack.pop();
		int result = currentNode.val;
		if (currentNode.right!=null) {
			currentNode = currentNode.right;
			stack.push(currentNode);
			while(currentNode.left!=null){
				currentNode = currentNode.left;
				stack.push(currentNode);
			}
		}
		return result;
	}
}
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
}
