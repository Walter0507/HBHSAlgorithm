package com.hbhs.algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.hbhs.algorithm.HBHSAssert;

/**
 * 二叉查找树排序
 * @author walter.xu
 *
 */
public class BinaryTreeSort {
	
	/**
	 * 二叉查找树排序<br>
	 * @param sequenceArray
	 */
	
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		//将数组第一个值作为树的根节点
		BinaryTree root = new BinaryTree(0);
		// 将数组其他元素组装到树种
		for (int i = 1; i < sequenceArray.length; i++) {
			addNodeIntoTree(sequenceArray, i, root);
		}
		// 获取到排序号的Index
		List<Integer> indexList = new ArrayList<Integer>();
		getSortedIndexFromTree(indexList, root);
		sortSequence(sequenceArray, indexList);
	}
	/**
	 * 添加节点到树种
	 * @param sequenceArray
	 * @param currentIndex
	 * @param root
	 */
	private static <T extends Comparable<? super T>> void addNodeIntoTree(T[] sequenceArray,
			int currentIndex, BinaryTree root){
		HBHSAssert.isTrue(currentIndex>0, "Index must more than 0");
		// 获取当前的比较Index
		int compareIndex = root.getCurrentIndex();
		if(compareIndex>=0){
			// 如果当前节点比待插入的节点小，则指定左树节点，否则指定右树节点
			if (sequenceArray[compareIndex].compareTo(sequenceArray[currentIndex])>0) {
				BinaryTree leftNode = root.getLeftNode();
				// 左树为空时，将当前节点作为左树，否则迭代左树
				if (leftNode==null) {
					root.setLeftNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getLeftNode());
				}
			}else{
				BinaryTree rightNode = root.getRightNode();
				// 右树为空时，将当前节点作为右树，否则迭代右树
				if (rightNode==null) {
					root.setRightNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getRightNode());
				}
			}
		}
	}
	
	/**
	 * 二叉查找树排序<br>
	 * @param sequenceArray
	 */
	
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		//将数组第一个值作为树的根节点
		BinaryTree root = new BinaryTree(0);
		// 将数组其他元素组装到树种
		for (int i = 1; i < sequenceArray.length; i++) {
			addNodeIntoTree(sequenceArray, i, root, c);
		}
		// 获取到排序号的Index
		List<Integer> indexList = new ArrayList<Integer>();
		getSortedIndexFromTree(indexList, root);
		sortSequence(sequenceArray, indexList);
	}
	/**
	 * 添加节点到树种
	 * @param sequenceArray
	 * @param currentIndex
	 * @param root
	 */
	private static <T> void addNodeIntoTree(T[] sequenceArray,
			int currentIndex, BinaryTree root, Comparator<T> c){
		HBHSAssert.isTrue(currentIndex>0, "Index must more than 0");
		// 获取当前的比较Index
		int compareIndex = root.getCurrentIndex();
		if(compareIndex>=0){
			// 如果当前节点比待插入的节点小，则指定左树节点，否则指定右树节点
			if (c.compare(sequenceArray[compareIndex], sequenceArray[currentIndex])>0) {
				BinaryTree leftNode = root.getLeftNode();
				// 左树为空时，将当前节点作为左树，否则迭代左树
				if (leftNode==null) {
					root.setLeftNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getLeftNode(), c);
				}
			}else{
				BinaryTree rightNode = root.getRightNode();
				// 右树为空时，将当前节点作为右树，否则迭代右树
				if (rightNode==null) {
					root.setRightNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getRightNode(), c);
				}
			}
		}
	}
	
	/**
	 * 二叉查找树排序<br>
	 * @param sequenceArray
	 */
	
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		//将数组第一个值作为树的根节点
		BinaryTree root = new BinaryTree(0);
		// 将数组其他元素组装到树种
		for (int i = 1; i < sequenceArray.size(); i++) {
			addNodeIntoTree(sequenceArray, i, root);
		}
		// 获取到排序号的Index
		List<Integer> indexList = new ArrayList<Integer>();
		getSortedIndexFromTree(indexList, root);
		sortSequence(sequenceArray, indexList);
	}
	/**
	 * 添加节点到树种
	 * @param sequenceArray
	 * @param currentIndex
	 * @param root
	 */
	private static <T extends Comparable<? super T>> void addNodeIntoTree(List<T> sequenceArray,
			int currentIndex, BinaryTree root){
		HBHSAssert.isTrue(currentIndex>0, "Index must more than 0");
		// 获取当前的比较Index
		int compareIndex = root.getCurrentIndex();
		if(compareIndex>=0){
			// 如果当前节点比待插入的节点小，则指定左树节点，否则指定右树节点
			if (sequenceArray.get(compareIndex).compareTo(sequenceArray.get(currentIndex))>0) {
				BinaryTree leftNode = root.getLeftNode();
				// 左树为空时，将当前节点作为左树，否则迭代左树
				if (leftNode==null) {
					root.setLeftNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getLeftNode());
				}
			}else{
				BinaryTree rightNode = root.getRightNode();
				// 右树为空时，将当前节点作为右树，否则迭代右树
				if (rightNode==null) {
					root.setRightNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getRightNode());
				}
			}
		}
	}
	
	/**
	 * 二叉查找树排序<br>
	 * @param sequenceArray
	 */
	
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		//将数组第一个值作为树的根节点
		BinaryTree root = new BinaryTree(0);
		// 将数组其他元素组装到树种
		for (int i = 1; i < sequenceArray.size(); i++) {
			addNodeIntoTree(sequenceArray, i, root, c);
		}
		// 获取到排序号的Index
		List<Integer> indexList = new ArrayList<Integer>();
		getSortedIndexFromTree(indexList, root);
		sortSequence(sequenceArray, indexList);
	}
	/**
	 * 添加节点到树种
	 * @param sequenceArray
	 * @param currentIndex
	 * @param root
	 */
	private static <T> void addNodeIntoTree(List<T> sequenceArray,
			int currentIndex, BinaryTree root, Comparator<T> c){
		HBHSAssert.isTrue(currentIndex>0, "Index must more than 0");
		// 获取当前的比较Index
		int compareIndex = root.getCurrentIndex();
		if(compareIndex>=0){
			// 如果当前节点比待插入的节点小，则指定左树节点，否则指定右树节点
			if (c.compare(sequenceArray.get(compareIndex), sequenceArray.get(currentIndex))>0) {
				BinaryTree leftNode = root.getLeftNode();
				// 左树为空时，将当前节点作为左树，否则迭代左树
				if (leftNode==null) {
					root.setLeftNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getLeftNode(), c);
				}
			}else{
				BinaryTree rightNode = root.getRightNode();
				// 右树为空时，将当前节点作为右树，否则迭代右树
				if (rightNode==null) {
					root.setRightNode(new BinaryTree(currentIndex));
				}else{
					addNodeIntoTree(sequenceArray, currentIndex, root.getRightNode(), c);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param sequenceArray
	 * @param indexList
	 */
	@SuppressWarnings("unchecked")
	private static <T> void sortSequence(T[] sequenceArray, List<Integer> indexList){
		// 待优化
		Object[] sortedSequence = new Object[sequenceArray.length];
		for (int i = 0; i < indexList.size(); i++) {
			sortedSequence[i] = sequenceArray[indexList.get(i)];
		}
		for (int i = 0; i < indexList.size(); i++) {
			sequenceArray[i] = (T)sortedSequence[i];
		}
	}
	/**
	 * 
	 * @param sequenceArray
	 * @param indexList
	 */
	@SuppressWarnings("unchecked")
	private static <T> void sortSequence(List<T> sequenceArray, List<Integer> indexList){
		// 待优化
		Object[] sortedSequence = new Object[sequenceArray.size()];
		for (int i = 0; i < indexList.size(); i++) {
			sortedSequence[i] = sequenceArray.get(indexList.get(i));
		}
		for (int i = 0; i < indexList.size(); i++) {
			sequenceArray.set(i, (T)sortedSequence[i]);
		}
	}
	
	/**
	 * 获取到树结构的所有index组合，排序方式为：前序遍历方式
	 * @param indexList
	 * @param root
	 */
	private static void getSortedIndexFromTree(List<Integer> indexList, BinaryTree root){
		if (root!=null) {
			getSortedIndexFromTree(indexList, root.getLeftNode());
			indexList.add(root.getCurrentIndex());
			getSortedIndexFromTree(indexList, root.getRightNode());
		}
	}
	 
}
/**
 * 临时树结构
 * @author walter.xu
 *
 */
class BinaryTree{
	// current index in array for tree node
	private int currentIndex;
	// left node
	private BinaryTree leftNode = null;
	// right node
	private BinaryTree rightNode = null;
	
	public BinaryTree(int index) {
		this.currentIndex = index;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public BinaryTree getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BinaryTree leftNode) {
		this.leftNode = leftNode;
	}

	public BinaryTree getRightNode() {
		return rightNode;
	}

	public void setRightNode(BinaryTree rightNode) {
		this.rightNode = rightNode;
	}

}
