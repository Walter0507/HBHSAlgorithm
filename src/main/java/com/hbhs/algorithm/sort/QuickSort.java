package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * <p><B><CODE>QUICK-SORT</CODE> 快速排序</B>。快速排序也是基于分治模式的</p>
 * <p>快速排序是一种排序算法，对包含n个数的输入数组，最坏情况运行时间为(n*n)，但是其通常是用于排序的最佳选择，这是因为其平均性能非常好，
 * 期望的运行时间为(nlgn),且该运行时间的隐含的常数因子很小，其还能就地排序，在虚存环境下也能很好的工作</p>
 * <code>
 * QUICKSORT(A,p,r)
 *   if p < r
 *     then q <- PARTITION(A,p,r)
 *       QUICKSORT(A,p,q-1)
 *       QUICKSORT(A,q+1,r)
 * </code>
 * 快速排序的重点是对子数组A[p,r]做就地重排操作
 * <code>
 * PARTITION(A,p,r)
 * x <- A[r]
 * i <- p-1
 * for j <- p to r-1
 *   do if A[j] <= x
 *     then i <- i+1
 *       exchange A[i] <-> A[j]
 * exchange A[i+1] <-> A[r]
 * return i+1
 * </code>
 * <P></P>
 * 
 * @author walter-xu
 *
 */
public class QuickSort {
	/**
	 * Quick sort
	 * @param sequenceForSort
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceForSort){
		quickSort(sequenceForSort, 0, sequenceForSort.length-1);
	}
	
	/**
	 * <B>QUICKSORT</B>
	 * <code>
     * QUICKSORT(A,p,r)
     *   if p < r
     *     then q <- PARTITION(A,p,r)
     *       QUICKSORT(A,p,q-1)
     *       QUICKSORT(A,q+1,r)
     * </code>
	 * @param sequenceForSort
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T extends Comparable<? super T>> void quickSort(T[] sequenceForSort, int startIndex, int endIndex){
		if (startIndex < endIndex) {
			int areaElement = partition(sequenceForSort, startIndex, endIndex);
			quickSort(sequenceForSort, startIndex, areaElement-1);
			quickSort(sequenceForSort, areaElement+1, endIndex);
		}
	}
	/**
	 * <p><B><code>PARTITION(A,p,r)</code></B> PARTITION对数组进行就地重排，他总是选择一个x=A[r]作为主元(pivot element),
	 * 并围绕它来划分子数组A[p,r],随着其过程执行，他总是会产生四个(有可能为空)的区域(a 小于A[r]的key区域，b 大于A[r]的key区域，c 
	 * 未比较区域, d 主元区域A[r]). 具体区分流程如下,空格表示区分的区域</p>
	 * <p></p>
	 * A[ 2,8,7,1,3,5,6, 4] 设置主元A[r]，key为4
	 * A[2, 8,7,1,3,5,6, 4] 比较A[1],key为2，比主元小，放左边区域
	 * A[2, 8, 7,1,3,5,6, 4]
	 * A[2, 8,7, 1,3,5,6, 4]
	 * A[2,1, 7,8, 3,5,6, 4]
	 * A[2,1,3, 8,7, 5,6, 4]
	 * A[2,1,3, 8,7,5, 6, 4]
	 * A[2,1,3, 8,7,5,6, 4] 全部比较完成，区分为3快 左边为小于主元的组，中间为大于主元的组
	 * 
     * <code>
     * PARTITION(A,p,r)
     * x <- A[r]
     * i <- p-1
     * for j <- p to r-1
     *   do if A[j] <= x
     *     then i <- i+1
     *       exchange A[i] <-> A[j]
     * exchange A[i+1] <-> A[r]
     * return i+1
     * </code>
	 * @param sequenceForSort 待分组数组
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partition(T[] sequenceForSort,int startIndex,int endIndex){
		T keyElement = sequenceForSort[endIndex];  //主元
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j < endIndex; j++) {
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (sequenceForSort[j].compareTo(keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort[j];
				sequenceForSort[j] = sequenceForSort[areaDevide];
				sequenceForSort[areaDevide] = currentKey;
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort[areaDevide+1];
		sequenceForSort[areaDevide+1] = sequenceForSort[endIndex];
		sequenceForSort[endIndex] = currentKey;

		return areaDevide+1;
	}
	
	/**
	 * Quick sort
	 * @param sequenceForSort
	 */
	public static <T> void sort(T[] sequenceForSort, Comparator<T> c){
		quickSort(sequenceForSort, 0, sequenceForSort.length-1, c);
	}
	
	/**
	 * <B>QUICKSORT</B>
	 * <code>
     * QUICKSORT(A,p,r)
     *   if p < r
     *     then q <- PARTITION(A,p,r)
     *       QUICKSORT(A,p,q-1)
     *       QUICKSORT(A,q+1,r)
     * </code>
	 * @param sequenceForSort
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T> void quickSort(T[] sequenceForSort, int startIndex, int endIndex, Comparator<T> c){
		if (startIndex < endIndex) {
			int areaElement = partition(sequenceForSort, startIndex, endIndex, c);
			quickSort(sequenceForSort, startIndex, areaElement-1, c);
			quickSort(sequenceForSort, areaElement+1, endIndex, c);
		}
	}
	/**
	 * <p><B><code>PARTITION(A,p,r)</code></B> PARTITION对数组进行就地重排，他总是选择一个x=A[r]作为主元(pivot element),
	 * 并围绕它来划分子数组A[p,r],随着其过程执行，他总是会产生四个(有可能为空)的区域(a 小于A[r]的key区域，b 大于A[r]的key区域，c 
	 * 未比较区域, d 主元区域A[r]). 具体区分流程如下,空格表示区分的区域</p>
	 * <p></p>
	 * A[ 2,8,7,1,3,5,6, 4] 设置主元A[r]，key为4
	 * A[2, 8,7,1,3,5,6, 4] 比较A[1],key为2，比主元小，放左边区域
	 * A[2, 8, 7,1,3,5,6, 4]
	 * A[2, 8,7, 1,3,5,6, 4]
	 * A[2,1, 7,8, 3,5,6, 4]
	 * A[2,1,3, 8,7, 5,6, 4]
	 * A[2,1,3, 8,7,5, 6, 4]
	 * A[2,1,3, 8,7,5,6, 4] 全部比较完成，区分为3快 左边为小于主元的组，中间为大于主元的组
	 * 
     * <code>
     * PARTITION(A,p,r)
     * x <- A[r]
     * i <- p-1
     * for j <- p to r-1
     *   do if A[j] <= x
     *     then i <- i+1
     *       exchange A[i] <-> A[j]
     * exchange A[i+1] <-> A[r]
     * return i+1
     * </code>
	 * @param sequenceForSort 待分组数组
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T> int partition(T[] sequenceForSort,int startIndex,int endIndex, Comparator<T> c){
		T keyElement = sequenceForSort[endIndex];  //主元
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j < endIndex; j++) {
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (c.compare(sequenceForSort[j], keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort[j];
				sequenceForSort[j] = sequenceForSort[areaDevide];
				sequenceForSort[areaDevide] = currentKey;
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort[areaDevide+1];
		sequenceForSort[areaDevide+1] = sequenceForSort[endIndex];
		sequenceForSort[endIndex] = currentKey;

		return areaDevide+1;
	}
	
	/**#############################################
	 * list
	 * ###################################
	 */
	/**
	 * Quick sort
	 * @param sequenceForSort
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceForSort){
		quickSort(sequenceForSort, 0, sequenceForSort.size()-1);
	}
	
	/**
	 * <B>QUICKSORT</B>
	 * <code>
     * QUICKSORT(A,p,r)
     *   if p < r
     *     then q <- PARTITION(A,p,r)
     *       QUICKSORT(A,p,q-1)
     *       QUICKSORT(A,q+1,r)
     * </code>
	 * @param sequenceForSort
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T extends Comparable<? super T>> void quickSort(List<T> sequenceForSort, int startIndex, int endIndex){
		if (startIndex < endIndex) {
			int areaElement = partition(sequenceForSort, startIndex, endIndex);
			quickSort(sequenceForSort, startIndex, areaElement-1);
			quickSort(sequenceForSort, areaElement+1, endIndex);
		}
	}
	/**
	 * <p><B><code>PARTITION(A,p,r)</code></B> PARTITION对数组进行就地重排，他总是选择一个x=A[r]作为主元(pivot element),
	 * 并围绕它来划分子数组A[p,r],随着其过程执行，他总是会产生四个(有可能为空)的区域(a 小于A[r]的key区域，b 大于A[r]的key区域，c 
	 * 未比较区域, d 主元区域A[r]). 具体区分流程如下,空格表示区分的区域</p>
	 * <p></p>
	 * A[ 2,8,7,1,3,5,6, 4] 设置主元A[r]，key为4
	 * A[2, 8,7,1,3,5,6, 4] 比较A[1],key为2，比主元小，放左边区域
	 * A[2, 8, 7,1,3,5,6, 4]
	 * A[2, 8,7, 1,3,5,6, 4]
	 * A[2,1, 7,8, 3,5,6, 4]
	 * A[2,1,3, 8,7, 5,6, 4]
	 * A[2,1,3, 8,7,5, 6, 4]
	 * A[2,1,3, 8,7,5,6, 4] 全部比较完成，区分为3快 左边为小于主元的组，中间为大于主元的组
	 * 
     * <code>
     * PARTITION(A,p,r)
     * x <- A[r]
     * i <- p-1
     * for j <- p to r-1
     *   do if A[j] <= x
     *     then i <- i+1
     *       exchange A[i] <-> A[j]
     * exchange A[i+1] <-> A[r]
     * return i+1
     * </code>
	 * @param sequenceForSort 待分组数组
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partition(List<T> sequenceForSort,int startIndex,int endIndex){
		T keyElement = sequenceForSort.get(endIndex);  //主元
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j < endIndex; j++) {
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (sequenceForSort.get(j).compareTo(keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort.get(j);
				sequenceForSort.set(j, sequenceForSort.get(areaDevide));
				sequenceForSort.set(areaDevide, currentKey);
//				sequenceForSort[j] = sequenceForSort[areaDevide];
//				sequenceForSort[areaDevide] = currentKey;
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort.get(areaDevide+1);
		sequenceForSort.set(areaDevide+1, sequenceForSort.get(endIndex));
		sequenceForSort.set(endIndex, currentKey);
//		sequenceForSort[areaDevide+1] = sequenceForSort[endIndex];
//		sequenceForSort[endIndex] = currentKey;

		return areaDevide+1;
	}
	
	/**
	 * Quick sort
	 * @param sequenceForSort
	 */
	public static <T> void sort(List<T> sequenceForSort, Comparator<T> c){
		quickSort(sequenceForSort, 0, sequenceForSort.size()-1, c);
	}
	
	/**
	 * <B>QUICKSORT</B>
	 * <code>
     * QUICKSORT(A,p,r)
     *   if p < r
     *     then q <- PARTITION(A,p,r)
     *       QUICKSORT(A,p,q-1)
     *       QUICKSORT(A,q+1,r)
     * </code>
	 * @param sequenceForSort
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T> void quickSort(List<T> sequenceForSort, int startIndex, int endIndex, Comparator<T> c){
		if (startIndex < endIndex) {
			int areaElement = partition(sequenceForSort, startIndex, endIndex, c);
			quickSort(sequenceForSort, startIndex, areaElement-1, c);
			quickSort(sequenceForSort, areaElement+1, endIndex, c);
		}
	}
	/**
	 * <p><B><code>PARTITION(A,p,r)</code></B> PARTITION对数组进行就地重排，他总是选择一个x=A[r]作为主元(pivot element),
	 * 并围绕它来划分子数组A[p,r],随着其过程执行，他总是会产生四个(有可能为空)的区域(a 小于A[r]的key区域，b 大于A[r]的key区域，c 
	 * 未比较区域, d 主元区域A[r]). 具体区分流程如下,空格表示区分的区域</p>
	 * <p></p>
	 * A[ 2,8,7,1,3,5,6, 4] 设置主元A[r]，key为4
	 * A[2, 8,7,1,3,5,6, 4] 比较A[1],key为2，比主元小，放左边区域
	 * A[2, 8, 7,1,3,5,6, 4]
	 * A[2, 8,7, 1,3,5,6, 4]
	 * A[2,1, 7,8, 3,5,6, 4]
	 * A[2,1,3, 8,7, 5,6, 4]
	 * A[2,1,3, 8,7,5, 6, 4]
	 * A[2,1,3, 8,7,5,6, 4] 全部比较完成，区分为3快 左边为小于主元的组，中间为大于主元的组
	 * 
     * <code>
     * PARTITION(A,p,r)
     * x <- A[r]
     * i <- p-1
     * for j <- p to r-1
     *   do if A[j] <= x
     *     then i <- i+1
     *       exchange A[i] <-> A[j]
     * exchange A[i+1] <-> A[r]
     * return i+1
     * </code>
	 * @param sequenceForSort 待分组数组
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	private static <T> int partition(List<T> sequenceForSort,int startIndex,int endIndex, Comparator<T> c){
		T keyElement = sequenceForSort.get(endIndex);  //主元
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j < endIndex; j++) {
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (c.compare(sequenceForSort.get(j), keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort.get(j);
				sequenceForSort.set(j, sequenceForSort.get(areaDevide));
				sequenceForSort.set(areaDevide, currentKey);
//				sequenceForSort[j] = sequenceForSort[areaDevide];
//				sequenceForSort[areaDevide] = currentKey;
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort.get(areaDevide+1);
		sequenceForSort.set(areaDevide+1, sequenceForSort.get(endIndex));
		sequenceForSort.set(endIndex, currentKey);
//		sequenceForSort[areaDevide+1] = sequenceForSort[endIndex];
//		sequenceForSort[endIndex] = currentKey;

		return areaDevide+1;
	}
}
