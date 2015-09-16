package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 插入排序算法：
 * INSERTION-SORT[A]
 *     for j<- 2 to lenght[A]
 *       do key <- A[j]
 *         insert A[j] into the sequence A[1,...j-1].
 *         i <- j-1
 *         while i >0 and A[i] >key
 *           do A[i+1] <- A[i]
 *             i <- i-1
 *         A[i+1]<- key
 *
 */
public class InsertionSort {
	
	/**
	 * Sort by Insertion<br>
	 * @param sequenceForSort
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceForSort){
		if (sequenceForSort==null||sequenceForSort.length <=1 ) {
			return ;
		}
		insertionSortRecurtion(sequenceForSort, 1);
	}
	
	/**
	 * Do insertion sort by recurtion. This method execution flow is like this:<br>
	 * do key <- A[index]
	 *   Insert key into A[0,....,index].
	 *   Do recurtion for index+1.
	 * Exit only if index = A.length-1  
	 * 
	 * @param sequenceForSort
	 * @param index
	 */
	private static <T extends Comparable<? super T>> void insertionSortRecurtion(T[] sequenceForSort, int index){
		if (index >= sequenceForSort.length) {
			return ;
		}
		// Insert and swap data
		for (int i = index-1; i >= 0; i--) {
			if (sequenceForSort[i].compareTo(sequenceForSort[i+1])>0) {
				swap(sequenceForSort, i, i+1);
			} else {
				break;
			}
		}
		insertionSortRecurtion(sequenceForSort, ++index);
	}
	/**
	 * Swap data between <code>firstIndex</code> and <code>secondIndex</code>
	 * @param sequenceForSort
	 * @param firstIndex
	 * @param secondIndex
	 */
	private static <T extends Comparable<? super T>> void swap(T[] sequenceForSort, int firstIndex, int secondIndex){
		T swapValue = sequenceForSort[firstIndex];
		sequenceForSort[firstIndex] = sequenceForSort[secondIndex];
		sequenceForSort[secondIndex] = swapValue;
	}
	
	
	/**
	 * Sort by Insertion<br>
	 * @param sequenceForSort
	 */
	public static <T> void sort(T[] sequenceForSort, Comparator<T> c){
		if (sequenceForSort==null||sequenceForSort.length <=1 ) {
			return ;
		}
		insertionSortRecurtion(sequenceForSort, 1, c);
	}
	
	/**
	 * Do insertion sort by recurtion. This method execution flow is like this:<br>
	 * do key <- A[index]
	 *   Insert key into A[0,....,index].
	 *   Do recurtion for index+1.
	 * Exit only if index = A.length-1  
	 * 
	 * @param sequenceForSort
	 * @param index
	 */
	private static <T> void insertionSortRecurtion(T[] sequenceForSort, int index
			, Comparator<T> c){
		if (index >= sequenceForSort.length) {
			return ;
		}
		// Insert and swap data
		for (int i = index-1; i >= 0; i--) {
			if (c.compare(sequenceForSort[i], sequenceForSort[i+1])>0) {
				swap(sequenceForSort, i, i+1);
			} else {
				break;
			}
		}
		insertionSortRecurtion(sequenceForSort, ++index, c);
	}
	/**
	 * Swap data between <code>firstIndex</code> and <code>secondIndex</code>
	 * @param sequenceForSort
	 * @param firstIndex
	 * @param secondIndex
	 */
	private static <T> void swap(T[] sequenceForSort, int firstIndex, int secondIndex){
		T swapValue = sequenceForSort[firstIndex];
		sequenceForSort[firstIndex] = sequenceForSort[secondIndex];
		sequenceForSort[secondIndex] = swapValue;
	}
	
	/* ###################################
	 * List
	 * ###################################
	 */
	/**
	 * Sort by Insertion<br>
	 * @param sequenceForSort
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceForSort){
		if (sequenceForSort==null||sequenceForSort.size() <=1 ) {
			return ;
		}
		insertionSortRecurtion(sequenceForSort, 1);
	}
	
	/**
	 * Do insertion sort by recurtion. This method execution flow is like this:<br>
	 * do key <- A[index]
	 *   Insert key into A[0,....,index].
	 *   Do recurtion for index+1.
	 * Exit only if index = A.length-1  
	 * 
	 * @param sequenceForSort
	 * @param index
	 */
	private static <T extends Comparable<? super T>> void insertionSortRecurtion(List<T> sequenceForSort, int index){
		if (index >= sequenceForSort.size()) {
			return ;
		}
		// Insert and swap data
		for (int i = index-1; i >= 0; i--) {
			if (sequenceForSort.get(i).compareTo(sequenceForSort.get(i+1))>0) {
				T swapData = sequenceForSort.get(i);
				sequenceForSort.set(i, sequenceForSort.get(i+1));
				sequenceForSort.set(i+1, swapData);
			} else {
				break;
			}
		}
		insertionSortRecurtion(sequenceForSort, ++index);
	}

	
	/**
	 * Sort by Insertion<br>
	 * @param sequenceForSort
	 */
	public static <T> void sort(List<T> sequenceForSort, Comparator<T> c){
		if (sequenceForSort==null||sequenceForSort.size() <=1 ) {
			return ;
		}
		insertionSortRecurtion(sequenceForSort, 1, c);
	}
	
	/**
	 * Do insertion sort by recurtion. This method execution flow is like this:<br>
	 * do key <- A[index]
	 *   Insert key into A[0,....,index].
	 *   Do recurtion for index+1.
	 * Exit only if index = A.length-1  
	 * 
	 * @param sequenceForSort
	 * @param index
	 */
	private static <T> void insertionSortRecurtion(List<T> sequenceForSort, int index
			, Comparator<T> c){
		if (index >= sequenceForSort.size()) {
			return ;
		}
		// Insert and swap data
		for (int i = index-1; i >= 0; i--) {
			if (c.compare(sequenceForSort.get(i), sequenceForSort.get(i+1))>0) {
				//Swap data
				T swapData = sequenceForSort.get(i);
				sequenceForSort.set(i, sequenceForSort.get(i+1));
				sequenceForSort.set(i+1, swapData);
			} else {
				break;
			}
		}
		insertionSortRecurtion(sequenceForSort, ++index, c);
	}

}
