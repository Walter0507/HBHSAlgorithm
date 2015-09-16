package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;


/**
 * 堆排序是一种数组对象，他可以被看成是一个完全二叉树结构。树的每一层都是填满的，随后一层可能除外。
 * <p>表示堆的数组A是一个具有两个属性的对象：
 * lenght[A]是数组中的元素个数，heap-size[A]是存放在A中的堆的元素个数。就是说，虽然A[1..length[A]]
 * 中都包含有效值，但A[heap-size[A]]之后的元素是不属于相应的堆的，此处heap-size[A]<=lenght[A].
 * </p>
 * <p>
 * 树根为A[1],给定的某节点小标i，其父节点PARENT(i),左儿子LEFT(i)和右儿子RIGHT(i)的下标可以简单的计算出来:
 * <p><code>PARENT(i): return [i/2]</code></p>
 * <p><code>LEFT(i): return 2i</code></p>
 * <p><code>RIGHT(i): return 2i+1</code></p>
 * </p>
 * <p>LEFT过程可以在一条指令内计算出2i，方法是将i的二进制表示左移1位。类似的RIGHT过程也可以通过将i的二进制左移1位并低位中加1，快速计算出2i+1
 * .
 * PARENT过程则可以通过把i右移1位而得到[i/2]。在堆排序算法中，这三个过程功过宏过程或者内联过程实现。
 * </p>
 * 
 * <p>
 * 二叉堆有两种：最大堆和最小堆。两种堆都需要满足堆特性，其细节则视堆的种类而定。
 * 在最大堆中，最大堆特性是指除了根意外的每个节点i，有<code>A[PARENT(i)>=A[i]]</code><br>
 * 在最小堆中，最小堆特性是指除了根意外的每个节点i，有<code>A[PARENT(i)<=A[i]]</code><br>
 * </p>
 */
public class HeapSort {
	
	/**
	 * <code><b>堆排序算法</b></code>
	 * <p>
	 * 开始时，堆排序算法先用BUILD-MAX-HEAP将数组A[1..n](此处n=lenght[A])构成一个最大堆，
	 * 因为数组中最大的元素在A[1]，则可以通过将他与A[n]呼唤来达到最终正确的位置。现在，如果从对中去掉节点n(通过减少heap-size[A]),可以
	 * 很容易的将A[1..n-1]建成最大堆。原来根的子女仍旧是最大堆，而新的根元素则可能违背了最大堆的性质，这个时候需要调用MAX-HEAPIFY(A,1)就保持了这一性质。
	 * 在A[1..n-1]中构造出最大堆。堆排序算法需要不断重复该过程，堆的大小由n-1一直降到2.
	 * </p>
	 * <code><b>HEAPSORT(A)</b></code>
	 * BUILD-MAX-HEAP(A)
	 * for i <- lenght[A] downto 2
	 *   do exchange A[1] <-> A[i]
	 *     heap-size[A] <- heap-size[A]-1
	 *     MAX-HEAPIFY[A,1]
	 *     <br>
	 * <B>堆排序时间复杂度为：</B><code>nlgn</code>
	 * @param heapSequence
	 * @return
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> heapSequence){
		// 创建最大堆
		buildMaxHeapify(heapSequence);
		
		for(int i = heapSequence.size()-1; i>0;i--){
			
			// 此时heapSequence[0]的值应该为最大值，与该堆最后一个值互换
			T key = heapSequence.get(0);
			heapSequence.set(0, heapSequence.get(i));
			heapSequence.set(i, key);
			maxHeapify(heapSequence,1,i);
		}
	}
	/**
	 * <code><b>堆排序算法</b></code>
	 * <p>
	 * 开始时，堆排序算法先用BUILD-MAX-HEAP将数组A[1..n](此处n=lenght[A])构成一个最大堆，
	 * 因为数组中最大的元素在A[1]，则可以通过将他与A[n]呼唤来达到最终正确的位置。现在，如果从对中去掉节点n(通过减少heap-size[A]),可以
	 * 很容易的将A[1..n-1]建成最大堆。原来根的子女仍旧是最大堆，而新的根元素则可能违背了最大堆的性质，这个时候需要调用MAX-HEAPIFY(A,1)就保持了这一性质。
	 * 在A[1..n-1]中构造出最大堆。堆排序算法需要不断重复该过程，堆的大小由n-1一直降到2.
	 * </p>
	 * <code><b>HEAPSORT(A)</b></code>
	 * BUILD-MAX-HEAP(A)
	 * for i <- lenght[A] downto 2
	 *   do exchange A[1] <-> A[i]
	 *     heap-size[A] <- heap-size[A]-1
	 *     MAX-HEAPIFY[A,1]
	 *     <br>
	 * <B>堆排序时间复杂度为：</B><code>nlgn</code>
	 * @param heapSequence
	 * @return
	 */
	public static <T> void sort(List<T> heapSequence, Comparator<T> c){
		// 创建最大堆
		buildMaxHeapify(heapSequence, c);
				
		for(int i = heapSequence.size()-1; i>0;i--){
					
			// 此时heapSequence[0]的值应该为最大值，与该堆最后一个值互换
			T key = heapSequence.get(0);
			heapSequence.set(0, heapSequence.get(i));
			heapSequence.set(i, key);
			maxHeapify(heapSequence,1,i, c);
		}
	}
	
	
	/**
	 * <code><B>创建堆</B></code>
	 * 对于创建一个HEAP，需要对每个非子节点调用MAX-HEAPIFY(A,i).
	 * BUILD-MAX-HEAP(A)
	 * heap-size[A] <- lenght[A]
	 * for i <- [lenght[A]/2] downto 1
	 *   do MAX-HEAPIFY(A,i)
	 * @param heapSequence
	 * @return
	 */
	private static <T extends Comparable<? super T>> void buildMaxHeapify(List<T> heapSequence){
		for(int i = heapSequence.size()/2 ; i>0;i--){
			maxHeapify(heapSequence, i);
		}
	}
	/**
	 * <code><B>创建堆</B></code>
	 * 对于创建一个HEAP，需要对每个非子节点调用MAX-HEAPIFY(A,i).
	 * BUILD-MAX-HEAP(A)
	 * heap-size[A] <- lenght[A]
	 * for i <- [lenght[A]/2] downto 1
	 *   do MAX-HEAPIFY(A,i)
	 * @param heapSequence
	 * @param c
	 * @return
	 */
	private static <T> void buildMaxHeapify(List<T> heapSequence, Comparator<T> c){
		for(int i = heapSequence.size()/2 ; i>0;i--){
			maxHeapify(heapSequence, i, c);
		}
	}

	/**
	 * 最大堆 MAX-HEAPIFY(A,i)
	 * MAX-HEAPIFY(A,i)
	 *   l <- LEFT(i)
	 *   r <- RIGHT(i)
	 *   if l <= heap-size[A] and A[l] > A[i]
	 *     then largest <- l
	 *     else largest <- i
	 *   if r <= heap-size[A] and A[r] > A[largest]
	 *     then largest <- r
	 *   if largest != i
	 *     then exchange A[i] <-> A[largest]
	 *        MAX-HEAPIFY(A,largest)
	 * @param heapSequence
	 * @param i
	 */
	private static <T extends Comparable<? super T>> void maxHeapify(List<T> heapSequence,int i){
		int leftNode = 2*i-1;
		int rightNode = 2*i;
		int largest = 0;
		if (leftNode<heapSequence.size()&&heapSequence.get(leftNode-1).compareTo(heapSequence.get(i-1)) > 0) {
			largest = leftNode;
		}else
			largest = i;
		
		if (rightNode<heapSequence.size()&&heapSequence.get(rightNode-1).compareTo(heapSequence.get(largest-1))>0) {
			largest = rightNode;
		}
		
		if (largest != i) {
			T key = heapSequence.get(i-1);
			heapSequence.set(i-1, heapSequence.get(largest-1));
			heapSequence.set(largest-1, key);
			maxHeapify(heapSequence, largest);
			
		}
	}
	/**
	 * 最大堆 MAX-HEAPIFY(A,i)
	 * MAX-HEAPIFY(A,i)
	 *   l <- LEFT(i)
	 *   r <- RIGHT(i)
	 *   if l <= heap-size[A] and A[l] > A[i]
	 *     then largest <- l
	 *     else largest <- i
	 *   if r <= heap-size[A] and A[r] > A[largest]
	 *     then largest <- r
	 *   if largest != i
	 *     then exchange A[i] <-> A[largest]
	 *        MAX-HEAPIFY(A,largest)
	 * @param heapSequence
	 * @param i
	 */
	private static <T> void maxHeapify(List<T> heapSequence,int i, Comparator<T> c){
		int leftNode = 2*i-1;
		int rightNode = 2*i;
		int largest = 0;
		if (leftNode<heapSequence.size()&&
				c.compare(heapSequence.get(leftNode-1), heapSequence.get(i-1)) > 0) {
			largest = leftNode;
		}else
			largest = i;
		
		if (rightNode<heapSequence.size()&&
				c.compare(heapSequence.get(rightNode-1), heapSequence.get(largest-1))>0) {
			largest = rightNode;
		}

		
		if (largest != i) {
			T key = heapSequence.get(i-1);
			heapSequence.set(i-1, heapSequence.get(largest-1));
//			heapSequence[i-1] = heapSequence[largest-1];
			heapSequence.set(largest-1, key);
//			heapSequence[largest-1] = key;
			maxHeapify(heapSequence, largest,c);
			
		}
	}
	/**
	 * 重新堆化节点I，使得该节点下为最大/小堆
	 * @param heapSequence
	 * @param i
	 * @param heapLenght
	 */
	private static <T extends Comparable<? super T>> void maxHeapify(List<T> heapSequence,int i, int heapLenght){
		int leftNode = 2*i;
		int rightNode = 2*i+1;
		int largest = 0;
		// Check if the leftNode is larger currentNode, if yes, mark leftNode as the largestNode.
		if (leftNode<=heapLenght&&heapSequence.get(leftNode-1).compareTo(heapSequence.get(i-1))>0) {
			largest = leftNode;
		}else
			largest = i;
		// Check if the rightNode is larger current largestNode, if yes, mark rightNode as the largestNode.
		if (rightNode<=heapLenght&&heapSequence.get(rightNode-1).compareTo(heapSequence.get(largest-1))>0) {
			largest = rightNode;
		}
		// Check if the largestNode is currentNode, if not, swap it. And in the same time, heapify the subNode again.
		if (largest != i) {
			T key = heapSequence.get(i-1);
			heapSequence.set(i-1, heapSequence.get(largest-1));
//			heapSequence[i-1] = heapSequence[largest-1];
			heapSequence.set(largest-1, key);
//			heapSequence[largest-1] = key;
			// Heapify the swaped subNode.
			maxHeapify(heapSequence, largest,heapLenght);
		}
	}
	/**
	 * 重新堆化节点I，使得该节点下为最大/小堆
	 * @param heapSequence
	 * @param i
	 * @param heapLenght
	 */
	private static <T> void maxHeapify(List<T> heapSequence,int i, int heapLenght, Comparator<T> c){
		int leftNode = 2*i;
		int rightNode = 2*i+1;
		int largest = 0;
		if (leftNode<=heapLenght&&c.compare(heapSequence.get(leftNode-1), heapSequence.get(i-1))>0) {
			
			largest = leftNode;
		}else
			largest = i;
		
		if (rightNode<=heapLenght&&c.compare(heapSequence.get(rightNode-1), heapSequence.get(largest-1))>0) {
			largest = rightNode;
		}

		
		if (largest != i) {
			T key = heapSequence.get(i-1);
			heapSequence.set(i-1, heapSequence.get(largest-1));
//			heapSequence[i-1] = heapSequence[largest-1];
			heapSequence.set(largest-1, key);
//			heapSequence[largest-1] = key;
			maxHeapify(heapSequence, largest,heapLenght,c);
			
		}
	}
	
}
