package com.hbhs.algorithm.common;

import java.util.Comparator;

/**
 * 红黑树<BR>
 * 红黑树必须满足如下条件<Br>
 * <UL>1. 每个节点是红色的或者黑色的</UL>
 * <UL>2. 根节点是黑色的</UL>
 * <UL>3. 每个叶子节点是黑色的</UL>
 * <UL>4. 如果一个节点是红色的，那么其两个儿子节点是黑的</UL>
 * <UL>5. 对于每个节点，从该节点到其子孙节点的所有路径上包含相同数目的黑节点</UL>
 * @author walter.xu
 *
 */
public class RedBlackTree<T> {
	private RedBlackTreeNode<T> nullNode = new RedBlackTreeNode<T>();
	private RedBlackTreeNode<T> root = null;
	private Comparator<T> comparator;
	
	public RedBlackTree(T root, Comparator<T> comparator){
		if (root==null||comparator==null) {
			throw new IllegalArgumentException("Root Node or comparator should not be null");
		}
		this.root = new RedBlackTreeNode<T>(root, nullNode);
		this.comparator = comparator;
	}
	
	public RedBlackTreeNode<T> getRoot(){
		return root;
	}
	
	public RedBlackTreeNode<T> findNode(T node){
		RedBlackTreeNode<T> target = root;
		while(target!=null){
			int compare = comparator.compare(target.getKey(), node);
			if (compare > 0) {
				target = target.getLeft();
			}else if(compare < 0){
				target = target.getRight();
			}else
				return target;
		}
		return null;
	}
	
	/**
	 * 
	 * @param key
	 */
	public void insert(T key){
		RedBlackTreeNode<T> keyNode = new RedBlackTreeNode<T>(key, nullNode);
		insert(keyNode);
	}
	
	/**
	 * 插入操作
	 * @param node
	 */
	private void insert(RedBlackTreeNode<T> node){
		// 待比较的节点
		RedBlackTreeNode<T> compareNode = root;
		// 临时保存的节点，该节点用于定义后续节点
//		RedBlackTreeNode<T> temNode = null;
		// 待比较的节点不为空时一直找到空节点
		while(!compareNode.isLeepNode()){
//			temNode = compareNode;
			// 待比较节点的左子节点为空时，或者该节点的值比带插入节点的值大时，则设置比较节点为左子节点，否则为右子节点
			if (comparator.compare(compareNode.getKey(), node.getKey())>0) {
				compareNode = compareNode.getLeft();
			}else{
				compareNode = compareNode.getRight();
			}
		}
		// 将带插入的节点父亲节点设置为找到的临时待插入的节点
		node.setParent(compareNode);
		if (compareNode.getKey()==null) {
			// 如果该节点为空，则设置其为根节点
			node.setParent(null);
			node.setRed(false);
			this.root = node;
		}else if (comparator.compare(compareNode.getKey(), node.getKey())>0) {
			compareNode.setLeft(node);
		}else{
			compareNode.setRight(node);
		}
		// 设置带插入节点的子节点为空，并设置其红黑标示为红
		node.setRed(true);
		// 同时调用空节点
		this.insertFixUp(node);
	}
	/**
	 * 
	 * @param node
	 */
	private void insertFixUp(RedBlackTreeNode<T> node){
		// 当前节点与父节点均为红色节点
		while (node.getParent()!=null&&node.getParent().isRed()) {
			if (node.getParent().getParent()==null) {
				break;
			}
			if (node.getParent()==node.getParent().getParent().getLeft()) {
				// 父节点为左节点
				// 获得该节点的叔叔节点(父亲的父亲节点的子节点)
				RedBlackTreeNode<T> uncleNode = node.getParent().getParent().getRight();
				if (uncleNode!=null&&uncleNode.isRed()) {
					// 如何叔叔节点为红色，表示父亲以及叔叔节点均为红色，则将这两个节点均设置为黑，并将爷爷节点设置为红，同时将节点移动到爷爷节点上，从而满足红黑树条件
					// 设置叔叔节点以及父节点均为黑色
					node.getParent().setRed(false);
					uncleNode.setRed(false);
					// 设置爷爷节点为红色
					node.getParent().getParent().setRed(true);
					// 移动到爷爷节点，并校验
					node = node.getParent().getParent();
				} else {
					// 如何叔叔节点为黑色，表示该红黑树仅影响这边的树结构，只需要把该子树结构整理成红黑树即可
					// 如果当前节点为有节点，则通过左旋操作变成左节点操作，同时为了方便，吧节点只想为左旋后的子节点位置
					if (node.getParent().getRight()!=null&&node == node.getParent().getRight()) {
						node = node.getParent();
						leftRotate(node);
					}
					// 设置父节点为黑
					node.getParent().setRed(false);
					// 设置爷爷节点为红
					node.getParent().getParent().setRed(true);
					// 右旋爷爷节点即可
					rightRotate(node.getParent().getParent());
				}
			}else{
				// 父节点为右节点
				// 获得该节点的叔叔节点(父亲的父亲节点的子节点)
				RedBlackTreeNode<T> uncleNode = node.getParent().getParent().getLeft();
				if (uncleNode!=null&&uncleNode.isRed()) {
					// 如何叔叔节点为红色，表示父亲以及叔叔节点均为红色，则将这两个节点均设置为黑，并将爷爷节点设置为红，同时将节点移动到爷爷节点上，从而满足红黑树条件
					// 设置叔叔节点以及父节点均为黑色
					node.getParent().setRed(false);
					uncleNode.setRed(false);
					// 设置爷爷节点为红色
					node.getParent().getParent().setRed(true);
					// 移动到爷爷节点，并校验
					node = node.getParent().getParent();
				} else {
					// 如何叔叔节点为黑色，表示该红黑树仅影响这边的树结构，只需要把该子树结构整理成红黑树即可
					// 如果当前节点为有节点，则通过左旋操作变成左节点操作，同时为了方便，吧节点只想为左旋后的子节点位置
					if (node.getParent().getLeft()!=null&&node == node.getParent().getLeft()) {
						node = node.getParent();
						rightRotate(node);
					}
					// 设置父节点为黑
					node.getParent().setRed(false);
					// 设置爷爷节点为红
					node.getParent().getParent().setRed(true);
					// 右旋爷爷节点即可
					leftRotate(node.getParent().getParent());
				}
			}
		}
		root.setRed(false);
	}
	
	public void delete(T node){
		RedBlackTreeNode<T> targetNode = findNode(node);
		delete(targetNode);
	}
	
	private void delete(RedBlackTreeNode<T> node){
		// 1 找到待删除节点的后继节点
		// 2 将后继节点的子节点全部都挂到后继节点的父节点上，
		// 3 将后继节点的值移到当前待删除节点上
		RedBlackTreeNode<T> compareNode = node;  // 待匹配节点，一般情况下为当前待删除节点或其后继节点(后集结点一般为叶子节点或者只有一个子节点)
		
		// 如果当前节点两个儿子节点均不为空，则找到当前节点的后继节点为待匹配的节点
		if (!node.isLeepNode()&&node.getLeft()!=null&&node.getRight()!=null) {
			compareNode = findSuccessorNode(node);
		}
		RedBlackTreeNode<T> subNode = nullNode;     // 定义后继节点的子节点 默认为空
		if (compareNode.getLeft()!=null) {
			subNode = compareNode.getLeft();
		}else{
			if (compareNode.getRight()!=null) {
				subNode = compareNode.getRight();
			}
		}
		// 如果待匹配节点的任何一个子节点不为空，则设置子节点的父节点为带匹配节点的父节点
		if (!subNode.isLeepNode()) {
			subNode.setParent(compareNode.getParent());
		}
		if (compareNode.getParent()==null) {
			// 为根节点
			compareNode.setRed(false);
			this.root = compareNode;
		}if (compareNode == compareNode.getParent().getLeft()) {
			compareNode.getParent().setLeft(subNode);
		}else{
			compareNode.getParent().setRight(subNode);
		}
		if (node!=compareNode) {
			node.setKey(compareNode.getKey());
		}
		if (!compareNode.isRed()) {
			deleteFixUp(subNode);
		}
		// 防止内存溢出 删除链接关系
		compareNode.setParent(null);
	}
	//TODO not done
	private void deleteFixUp(RedBlackTreeNode<T> targetNode){
		while(targetNode.getParent()!=null&&!targetNode.isRed()){
			if (targetNode == targetNode.getParent().getLeft()) {
				RedBlackTreeNode<T> brotherNode = targetNode.getParent().getRight();
				brotherNode = (brotherNode==null?nullNode:brotherNode);
				// case1 如果兄弟节点为红，则将兄弟节点设置为黑，并将父节点设置为红，再做左旋操作，得到匹配的红黑树，从而保证兄弟节点必须为黑
				if (brotherNode.isRed()) {
					brotherNode.setRed(false);
					brotherNode.getParent().setRed(true);
					leftRotate(targetNode);
					brotherNode = targetNode.getParent().getRight();
					brotherNode = (brotherNode==null?nullNode:brotherNode);
				}
				// 兄弟节点为黑
				// case2 如果兄弟节点的两个儿子均为红，则直接将当前节点设置为红，并依此迭代往上循环
				if (brotherNode.getLeft()!=null&&brotherNode.getRight()!=null&&
						!brotherNode.getLeft().isRed()&&!brotherNode.getRight().isRed()) {
					brotherNode.setRed(true);
					targetNode = targetNode.getParent();
				}else  {
					//case3 如果是兄弟节点的右节点为红
					if (brotherNode.getRight()!=null&&!brotherNode.getRight().isRed()) {
						// 做次旋转，变成case4
						brotherNode.getLeft().setRed(true);
						rightRotate(brotherNode);
						brotherNode = targetNode.getParent().getRight();
						brotherNode = (brotherNode==null?nullNode:brotherNode);
					}
					// CASE4 兄弟节点的左节点为红
					brotherNode.setRed(targetNode.getParent().isRed());
					targetNode.getParent().setRed(false);
					if (brotherNode!=nullNode&&!brotherNode.isLeepNode()) {
						brotherNode.getRight().setRed(false);
					}
					leftRotate(targetNode);
					// 退出循环
					targetNode = root;
				}
			}else{
				RedBlackTreeNode<T> brotherNode = targetNode.getParent().getLeft();
				brotherNode = (brotherNode==null?nullNode:brotherNode);
				// case1 如果兄弟节点为红，则将兄弟节点设置为黑，并将父节点设置为红，再做左旋操作，得到匹配的红黑树，从而保证兄弟节点必须为黑
				if (brotherNode.isRed()) {
					brotherNode.setRed(false);
					brotherNode.getParent().setRed(true);
					rightRotate(targetNode);
					brotherNode = targetNode.getParent().getLeft();
					brotherNode = (brotherNode==null?nullNode:brotherNode);
				}
				// 兄弟节点为黑
				// case2 如果兄弟节点的两个儿子均为红，则直接将当前节点设置为红，并依此迭代往上循环
				if (brotherNode!=nullNode&&!brotherNode.isLeepNode()&&
						!brotherNode.getLeft().isRed()&&!brotherNode.getRight().isRed()) {
					brotherNode.setRed(true);
					targetNode = targetNode.getParent();
				}else  {
					//case3 如果是兄弟节点的右节点为红
					if (brotherNode.getLeft()!=null&&!brotherNode.getLeft().isRed()) {
						// 做次旋转，变成case4
						brotherNode.getRight().setRed(true);
						leftRotate(brotherNode);
						brotherNode = targetNode.getParent().getLeft();
						brotherNode = (brotherNode==null?nullNode:brotherNode);
					}
					// CASE4 兄弟节点的左节点为红
					brotherNode.setRed(targetNode.getParent().isRed());
					targetNode.getParent().setRed(false);
					if (brotherNode!=nullNode&&!brotherNode.isLeepNode()) {
						brotherNode.getLeft().setRed(false);
					}
					rightRotate(targetNode);
					// 退出循环
					targetNode = root;
				}
			}
		}
		root.setRed(false);
	}
	/**
	 * 找到当前节点的后继节点
	 * @param node
	 * @return
	 */
	protected RedBlackTreeNode<T> findSuccessorNode(RedBlackTreeNode<T> node){
		if (node.getRight()!=null) {
			return findMinOfNode(node.getRight());
		}
		RedBlackTreeNode<T> parentNode = node.getParent();
		while(parentNode!=null&&node==parentNode.getRight()){
			node = node.getParent();
			parentNode = parentNode.getParent();
		}
		return parentNode;
	}
	/**
	 * 找到前驱节点
	 * @param node
	 * @return
	 */
	protected RedBlackTreeNode<T> findPresursorNode(RedBlackTreeNode<T> node){
		if (node.getLeft()!=null) {
			return findMaxOfNode(node.getLeft());
		}
		RedBlackTreeNode<T> parentNode = node.getParent();
		while(parentNode!=null&&node==parentNode.getLeft()){
			node = node.getParent();
			parentNode = parentNode.getParent();
		}
		return parentNode;
	}
	/**
	 * 查找当前节点下的最小节点
	 * @param node
	 * @return
	 */
	protected RedBlackTreeNode<T> findMinOfNode(RedBlackTreeNode<T> node){
		while(!node.isLeepNode()){
			node = node.getLeft();
		}
		return node;
	}
	/**
	 * 查找当前节点下的最大节点
	 * @param node
	 * @return
	 */
	protected RedBlackTreeNode<T> findMaxOfNode(RedBlackTreeNode<T> node){
		while(!node.isLeepNode()){
			node = node.getRight();
		}
		return node;
	}
	
	/**
	 * 左旋转
	 * @param node
	 */
	private void leftRotate(RedBlackTreeNode<T> node){
		// 获取到当前节点右节点
//		System.out.println("LEFT ROTATE at node: "+node.getKey()+", [BEFORE]: "+root.findHeadSort());
		RedBlackTreeNode<T> tempNode = node.getRight();
		if (tempNode == null) {
			return;
		}
		node.setRight(tempNode.getLeft()==null?nullNode:tempNode.getLeft());
		if (tempNode.getLeft()!=null) {
			tempNode.getLeft().setParent(node);
		}
		tempNode.setParent(node.getParent());
		if (node.getParent()==null) {
			tempNode.setParent(null);
			tempNode.setRed(false);
			root = tempNode;
		}else {
			if (node == node.getParent().getLeft()) {
				node.getParent().setLeft(tempNode);
			}else{
				node.getParent().setRight(tempNode);
			}
		}
		tempNode.setLeft(node);
		node.setParent(tempNode);
//		System.out.println("LEFT ROTATE at node: "+node.getKey()+", [AFTER]: "+root.findHeadSort());
	}
	/**
	 * 右旋转
	 * @param node
	 */
	private void rightRotate(RedBlackTreeNode<T> node){
//		System.out.println("RIGHT ROTATE at node: "+node.getKey()+", [BEFORE]: "+root.findHeadSort());
		// 获取到当前节点右节点
		RedBlackTreeNode<T> tempNode = node.getLeft();
		if (tempNode == null) {
			return;
		}
		node.setLeft(tempNode.getRight()==null?nullNode:tempNode.getRight());
		if (tempNode.getRight()!=null) {
			tempNode.getRight().setParent(node);
		}
		tempNode.setParent(node.getParent());
		if (node.getParent()==null) {
			tempNode.setParent(null);
			tempNode.setRed(false);
			root = tempNode;
		}else {
			if (node == node.getParent().getLeft()) {
				node.getParent().setLeft(tempNode);
			}else{
				node.getParent().setRight(tempNode);
			}
		}
		tempNode.setRight(node);
		node.setParent(tempNode);
//		System.out.println("RIGHT ROTATE at node: "+node.getKey()+", [AFTER]: "+root.findHeadSort());
	}
}