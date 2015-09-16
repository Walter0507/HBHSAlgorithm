package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <b>Binary Tree Right Side View</b><br>
 * <br>Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <br>For example:
 * <br>Given the following binary tree,
 * <br>
 * <br>   1            <---
 * <br> /   \
 * <br>2     3         <---
 * <br> \     \
 * <br>  5     4       <---
 * <br>
 * <br>You should return [1, 3, 4].
 * <br>
 * <br>
 * <br>
 * @ClassName: BinaryTreeRightSideView 
 * @author Walter.xu
 * @date 2015年6月19日 下午4:30:36
 */
public class BinaryTreeRightSideView {
	
	public static void main(String[] args) {
		
	}
	
	public List<Integer> rightSideView(TreeNode root) {
		if (root == null) {
			return null;
		}
		Stack<TreeNode> leftNodeStack = new Stack<TreeNode>();
        List<Integer> resultList = new ArrayList<Integer>();
        int depts = 1;
        TreeNode current = root;
        while(leftNodeStack.size()>0){
        	if (current!=null) {
				resultList.add(current.val);
				depts++;
				if (current.left!=null) {
					leftNodeStack.push(current.left);
				}
			}
        }
        return resultList;
    }
	
	public TreeNode findNextNode(Stack<TreeNode> stack, TreeNode root, int depts, List<Integer> resultList){
		if (root!=null) {
			resultList.add(root.val);
			if (root.left!=null) {
				stack.add(root.left);
			}else{
				stack.add(new TreeNode(Integer.MIN_VALUE-1));
			}
		}
		
		return null;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
