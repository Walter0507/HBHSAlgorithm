package com.hbhs.algorithm.common;

/**
 * 
 * @author walter.xu
 *
 * @param <T>
 */
public class RedBlackTreeNode<T> {
	private boolean isRed  = false;
	private T key;
	private RedBlackTreeNode<T> left = null;
	private RedBlackTreeNode<T> right = null;
	private RedBlackTreeNode<T> parent = null;
	protected RedBlackTreeNode(){
		this.isRed = false;
		this.key = null;
	}
	protected RedBlackTreeNode(T key, RedBlackTreeNode<T> nullNode){
		if (key==null) {
			throw new IllegalArgumentException("Tree node object should not be null");
		}
		this.key = key;
		this.left = nullNode;
		this.right = nullNode;
	}
	
	public boolean isRed() {
		return isRed;
	}
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}
	public T getKey() {
		return key;
	}
	public void setKey(T key) {
		this.key = key;
	}
	public RedBlackTreeNode<T> getLeft() {
		if (left==null||left.getKey()==null) {
			return null;
		}
		return left;
	}
	public void setLeft(RedBlackTreeNode<T> left) {
		this.left = left;
	}
	public RedBlackTreeNode<T> getRight() {
		if (right==null||right.getKey()==null) {
			return null;
		}
		return right;
	}
	public void setRight(RedBlackTreeNode<T> right) {
		this.right = right;
	}
	public RedBlackTreeNode<T> getParent() {
		return parent;
	}
	public void setParent(RedBlackTreeNode<T> parent) {
		this.parent = parent;
	}
	
	public boolean isLeepNode(){
		if (left!=null&&right!=null) {
			return left.getKey()==null&&right.getKey()==null;
		}
		return false;
	}
	
	public String toString(){
		return key+"{isRed:"+isRed+", ["+(parent==null?null:parent.getKey())+", "+(left==null?null:left.getKey())+" :"+(right==null?null:right.getKey())+"]}";
	}
	
	// testing
	private int treeDepth=0;
	public String formatPrint(){
//		StringBuilder str
		return null;
	}

	public int getTreeDepth() {
		return treeDepth;
	}

	public void addTreeDepth() {
		if (parent!=null) {
			this.treeDepth = parent.getTreeDepth()+1;
		}
		if (this.left!=null) {
			left.addTreeDepth();
		}
		if (this.right!=null) {
			right.addTreeDepth();
		}
	}
	
	public String findHeadSort(){
		if (key==null) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		if (left!=null) {
			str.append(left.findHeadSort()).append(", ");
		}
		if (this.getParent()==null) {
			str.append("("+key+")").append(", ");
		}else{
			str.append(key).append(", ");
		}
		
		if (right!=null) {
			str.append(right.findHeadSort()).append(", ");
		}
		return str.toString();
	}
}
