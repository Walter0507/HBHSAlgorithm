package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 简单选择排序算法<BR>
 * 它的工作原理如下。首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * <BR>
 * 
 * @author walter.xu
 *
 */
public class SimpleSelectSort {
	/**
	 * 选择排序算法
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		
		for (int i = 0; i < sequenceArray.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < sequenceArray.length; j++) {
				if (sequenceArray[minIndex].compareTo(sequenceArray[j])>0) {
					minIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
		}
	}
	
	/**
	 * 选择排序算法
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		
		for (int i = 0; i < sequenceArray.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < sequenceArray.length; j++) {
				if (c.compare(sequenceArray[minIndex], sequenceArray[j])>0) {
					minIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
		}
	}
	
	/**
	 * 选择排序算法
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		
		for (int i = 0; i < sequenceArray.size(); i++) {
			int minIndex = i;
			for (int j = i+1; j < sequenceArray.size(); j++) {
				if (sequenceArray.get(minIndex).compareTo(sequenceArray.get(j))>0) {
					minIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
		}
	}
	
	/**
	 * 选择排序算法
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		
		for (int i = 0; i < sequenceArray.size(); i++) {
			int minIndex = i;
			for (int j = i+1; j < sequenceArray.size(); j++) {
				if (c.compare(sequenceArray.get(minIndex), sequenceArray.get(j))>0) {
					minIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
		}
	}
	/**
	 * Swap
	 * @param sequenceArray
	 * @param i
	 * @param j
	 */
	private static <T> void swap(T[] sequenceArray, int i, int j){
		T swap = sequenceArray[i];
		sequenceArray[i] = sequenceArray[j];
		sequenceArray[j] = swap;
	}
	/**
	 * Swap
	 * @param sequenceArray
	 * @param i
	 * @param j
	 */
	private static <T> void swap(List<T> sequenceArray, int i, int j){
		T swap = sequenceArray.get(i);
		sequenceArray.set(i, sequenceArray.get(j));
		sequenceArray.set(j, swap);
	}
}
