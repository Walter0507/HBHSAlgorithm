package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 分治模式：
 * 分解(Divide):将原来问题分解成一系列子问题
 * 解决(Conquer):递归解各子问题，如子问题足够小，直接求解
 * 合并(Combine):将子问题的结果合并成原来的问题的解
 * 合并排序(merge sort) 算法完全依照分治模式，直观操作如下：
 * 分解：将n个元素分成各自含n/2个元素的子序列
 * 解决：用合并排序法对两个子序列递归的排序
 * 合并：合并两个以排序的子序列以得到排序结果
 * 
 * 算法伪代码：
 * MERGE(A,p,q,r)
 * n1 <- q-p+1
 * n2 <- r-q
 * create arrays L[1...n1+1] and R[1...n2+1]
 * for i <- 1 to n1
 *     do L[i] <- A[p+i-1]
 * for j <-1 to n2
 *     do R[j] <- A[q+j]
 * L[n1+1] <- MAX(int)
 * R[n2+1] <- MAX(int)
 * i <- 1
 * j <- 1
 * for k <- p to r
 *     do if L[i] <= R[j]
 *        then A[k] <- L[i]
 *          i <- i+1
 *        else A[k] <- R[j]
 *          j <- j+1
 * 
 *
 */
public class MergeSort {
	
	/**
	 * 归并排序算法
	 * @param sequenceForSort
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceForSort){
		mergeSortAlgorithm(sequenceForSort, 0, sequenceForSort.length-1);
	}
	
	/**
	 * 归并算法, 归并待排序数组中从A[start,....,end] 范围内的数据,<br>
	 * 在该范围内二分后区分为左子数组以及右子数组，并分别对两个子数组做递归归并.
	 * @param sequenceForSort
	 * @param start
	 * @param end
	 * @return
	 */
	private static <T extends Comparable<? super T>> void mergeSortAlgorithm(T[] sequenceForSort,int start,int end){
		if (start < end) {
			int q = (start+end)/2;
			// 递归左子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, start, q);
			// 递归右子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, q+1, end);
			// 归并数组，最终使得A[start,..,q]均小于A[q,end]
			merge(sequenceForSort, start, q, end);
		}
	}

	/**
	 * Merge core method. This method promise the data format as below:<br>
	 * for A[start,..., end]
	 *   define int i= (end-start)/2 , split as left part: A[start,...,(end-start+1)], right part: A[(end-start),...,end]
	 *   merge the two part, and must fit the rules: every element in left part must lower than the ones in right part.
	 * @param sequenceForSort
	 * @param start
	 * @param div
	 * @param end
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge(T[] sequenceForSort,int start,int div,int end){
		int n1 = div-start+1; //分治的左边
		int n2 = end-div; //分治的右边
		Object[] leftPart = new Object[n1];
		Object[] rightPart = new Object[n2];
		//赋值,初始化左边数组和右边数组
		for(int i = 0;i<n1;i++){
			leftPart[i] = sequenceForSort[start+i];
		}
		for(int i = 0;i<n2;i++){
			rightPart[i] = sequenceForSort[div+i+1];
		}
		// 对于左半部分和右半部分和原来的数组中元素对比，按照规则set对应值。
		int i=0,j=0;
		for(int k=start;k<=end;k++){
			if (i>= leftPart.length||j>=rightPart.length) {
				break;
			}
			if (((T)leftPart[i]).compareTo((T)rightPart[j])<0) {
				sequenceForSort[k]=(T)leftPart[i];
				i++;
			}else{
				sequenceForSort[k]=(T)rightPart[j];
				j++;
			}
			
		}
		//最后leftPart和rightPart有一个未做set值操作，需要将改值设置到原始数组对应的end值中
		if (i==leftPart.length) {
			sequenceForSort[end]= (T)rightPart[rightPart.length-1];
		}else
			sequenceForSort[end]= (T)leftPart[leftPart.length-1];
	}
	
	/**
	 * 归并排序算法
	 * @param sequenceForSort
	 */
	public static <T> void sort(T[] sequenceForSort, Comparator<T> c){
		mergeSortAlgorithm(sequenceForSort, 0, sequenceForSort.length-1, c);
	}
	
	/**
	 * 归并算法, 归并待排序数组中从A[start,....,end] 范围内的数据,<br>
	 * 在该范围内二分后区分为左子数组以及右子数组，并分别对两个子数组做递归归并.
	 * @param sequenceForSort
	 * @param start
	 * @param end
	 * @return
	 */
	private static <T> void mergeSortAlgorithm(T[] sequenceForSort,int start,int end, Comparator<T> c){
		if (start < end) {
			int q = (start+end)/2;
			// 递归左子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, start, q, c);
			// 递归右子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, q+1, end, c);
			// 归并数组，最终使得A[start,..,q]均小于A[q,end]
			merge(sequenceForSort, start, q, end, c);
		}
	}

	/**
	 * Merge core method. This method promise the data format as below:<br>
	 * for A[start,..., end]
	 *   define int i= (end-start)/2 , split as left part: A[start,...,(end-start+1)], right part: A[(end-start),...,end]
	 *   merge the two part, and must fit the rules: every element in left part must lower than the ones in right part.
	 * @param sequenceForSort
	 * @param start
	 * @param div
	 * @param end
	 */
	@SuppressWarnings("unchecked")
	private static <T> void merge(T[] sequenceForSort,int start,int div,int end, Comparator<T> c){
		int n1 = div-start+1; //分治的左边
		int n2 = end-div; //分治的右边
		Object[] leftPart = new Object[n1];
		Object[] rightPart = new Object[n2];
		//赋值,初始化左边数组和右边数组
		for(int i = 0;i<n1;i++){
			leftPart[i] = sequenceForSort[start+i];
		}
		for(int i = 0;i<n2;i++){
			rightPart[i] = sequenceForSort[div+i+1];
		}
		// 对于左半部分和右半部分和原来的数组中元素对比，按照规则set对应值。
		int i=0,j=0;
		for(int k=start;k<=end;k++){
			if (i>= leftPart.length||j>=rightPart.length) {
				break;
			}
			if (c.compare((T)leftPart[i], (T)rightPart[j])<0) {
				sequenceForSort[k]=(T)leftPart[i];
				i++;
			}else{
				sequenceForSort[k]=(T)rightPart[j];
				j++;
			}
			
		}
		//最后leftPart和rightPart有一个未做set值操作，需要将改值设置到原始数组对应的end值中
		if (i==leftPart.length) {
			sequenceForSort[end]= (T)rightPart[rightPart.length-1];
		}else
			sequenceForSort[end]= (T)leftPart[leftPart.length-1];
	}
	
	/**#########################
	 * List
	 * #########################
	 */
	/**
	 * 归并排序算法
	 * @param sequenceForSort
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceForSort){
		mergeSortAlgorithm(sequenceForSort, 0, sequenceForSort.size()-1);
	}
	
	/**
	 * 归并算法, 归并待排序数组中从A[start,....,end] 范围内的数据,<br>
	 * 在该范围内二分后区分为左子数组以及右子数组，并分别对两个子数组做递归归并.
	 * @param sequenceForSort
	 * @param start
	 * @param end
	 * @return
	 */
	private static <T extends Comparable<? super T>> void mergeSortAlgorithm(List<T> sequenceForSort,int start,int end){
		if (start < end) {
			int q = (start+end)/2;
			// 递归左子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, start, q);
			// 递归右子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, q+1, end);
			// 归并数组，最终使得A[start,..,q]均小于A[q,end]
			merge(sequenceForSort, start, q, end);
		}
	}

	/**
	 * Merge core method. This method promise the data format as below:<br>
	 * for A[start,..., end]
	 *   define int i= (end-start)/2 , split as left part: A[start,...,(end-start+1)], right part: A[(end-start),...,end]
	 *   merge the two part, and must fit the rules: every element in left part must lower than the ones in right part.
	 * @param sequenceForSort
	 * @param start
	 * @param div
	 * @param end
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge(List<T> sequenceForSort,int start,int div,int end){
		int n1 = div-start+1; //分治的左边
		int n2 = end-div; //分治的右边
		Object[] leftPart = new Object[n1];
		Object[] rightPart = new Object[n2];
		//赋值,初始化左边数组和右边数组
		for(int i = 0;i<n1;i++){
			leftPart[i] = sequenceForSort.get(start+i);
		}
		for(int i = 0;i<n2;i++){
			rightPart[i] = sequenceForSort.get(div+i+1);
		}
		// 对于左半部分和右半部分和原来的数组中元素对比，按照规则set对应值。
		int i=0,j=0;
		for(int k=start;k<=end;k++){
			if (i>= leftPart.length||j>=rightPart.length) {
				break;
			}
			if (((T)leftPart[i]).compareTo((T)rightPart[j])<0) {
				sequenceForSort.set(k, (T)leftPart[i]);
//				sequenceForSort[k]=(T)leftPart[i];
				i++;
			}else{
				sequenceForSort.set(k, (T)rightPart[j]);
//				sequenceForSort[k]=(T)rightPart[j];
				j++;
			}
			
		}
		//最后leftPart和rightPart有一个未做set值操作，需要将改值设置到原始数组对应的end值中
		if (i==leftPart.length) {
			sequenceForSort.set(end, (T)rightPart[rightPart.length-1]);
//			sequenceForSort[end]= (T)rightPart[rightPart.length-1];
		}else{
			sequenceForSort.set(end, (T)leftPart[leftPart.length-1]);
//			sequenceForSort[end]= (T)leftPart[leftPart.length-1];
		}
	}
	
	/**
	 * 归并排序算法
	 * @param sequenceForSort
	 */
	public static <T> void sort(List<T> sequenceForSort, Comparator<T> c){
		mergeSortAlgorithm(sequenceForSort, 0, sequenceForSort.size()-1, c);
	}
	
	/**
	 * 归并算法, 归并待排序数组中从A[start,....,end] 范围内的数据,<br>
	 * 在该范围内二分后区分为左子数组以及右子数组，并分别对两个子数组做递归归并.
	 * @param sequenceForSort
	 * @param start
	 * @param end
	 * @return
	 */
	private static <T> void mergeSortAlgorithm(List<T> sequenceForSort,int start,int end, Comparator<T> c){
		if (start < end) {
			int q = (start+end)/2;
			// 递归左子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, start, q, c);
			// 递归右子数组做归并排序
			mergeSortAlgorithm(sequenceForSort, q+1, end, c);
			// 归并数组，最终使得A[start,..,q]均小于A[q,end]
			merge(sequenceForSort, start, q, end, c);
		}
	}

	/**
	 * Merge core method. This method promise the data format as below:<br>
	 * for A[start,..., end]
	 *   define int i= (end-start)/2 , split as left part: A[start,...,(end-start+1)], right part: A[(end-start),...,end]
	 *   merge the two part, and must fit the rules: every element in left part must lower than the ones in right part.
	 * @param sequenceForSort
	 * @param start
	 * @param div
	 * @param end
	 */
	@SuppressWarnings("unchecked")
	private static <T> void merge(List<T> sequenceForSort,int start,int div,int end, Comparator<T> c){
		int n1 = div-start+1; //分治的左边
		int n2 = end-div; //分治的右边
		Object[] leftPart = new Object[n1];
		Object[] rightPart = new Object[n2];
		//赋值,初始化左边数组和右边数组
		for(int i = 0;i<n1;i++){
			leftPart[i] = sequenceForSort.get(start+i);
		}
		for(int i = 0;i<n2;i++){
			rightPart[i] = sequenceForSort.get(div+i+1);
		}
		// 对于左半部分和右半部分和原来的数组中元素对比，按照规则set对应值。
		int i=0,j=0;
		for(int k=start;k<=end;k++){
			if (i>= leftPart.length||j>=rightPart.length) {
				break;
			}
			if (c.compare((T)leftPart[i], (T)rightPart[j])<0) {
				sequenceForSort.set(k, (T)leftPart[i]);
//				sequenceForSort[k]=(T)leftPart[i];
				i++;
			}else{
				sequenceForSort.set(k, (T)rightPart[i]);
//				sequenceForSort[k]=(T)rightPart[j];
				j++;
			}
			
		}
		//最后leftPart和rightPart有一个未做set值操作，需要将改值设置到原始数组对应的end值中
		if (i==leftPart.length) {
			sequenceForSort.set(end, (T)rightPart[rightPart.length-1]);
//			sequenceForSort[end]= (T)rightPart[rightPart.length-1];
		}else{
			sequenceForSort.set(end, (T)leftPart[leftPart.length-1]);
//			sequenceForSort[end]= (T)leftPart[leftPart.length-1];
		}
	}
}
