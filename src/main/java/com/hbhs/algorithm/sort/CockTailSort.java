package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 鸡尾酒排序，也就是定向冒泡排序, 鸡尾酒搅拌排序, 搅拌排序 (也可以视作选择排序的一种变形), 涟漪排序, 来回排序 or 快乐小时排序, 是冒泡排序的一种变形。此算法与冒泡排序的不同处在于排序时是以双向在序列中进行排序。
 * public void sort(int[] a) {
 * 	//需要来回a,length/2趟
 * 	for (int i = 0; i < a.length / 2; i++) {
 * 		//类冒泡，交换最大值至右端
 * 		for (int j = i; 1 + j < a.length - i; j++)
 * 			if (a[j] > a[1 + j])
 * 				Arr.swap(a, j, 1 + j);
 * 		//类冒泡，交换最小值至左端
 * 		for (int j = a.length - i - 1; j > i; j--)
 * 			if (a[j - 1] > a[j])
 * 				Arr.swap(a, j - 1, j);
 * 	}
 * }
 * @author walter.xu
 *
 */
public class CockTailSort {
	/**
	 * 双向冒泡排序
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		for (int i = 0; i < sequenceArray.length/2; i++) {
			for (int j = i+1; j < sequenceArray.length-1; j++) {
				if (sequenceArray[j].compareTo(sequenceArray[j+1])>0) {
					T swap = sequenceArray[j];
					sequenceArray[j] = sequenceArray[j+1];
					sequenceArray[j+1] = swap;
				}
			}
			for (int j = sequenceArray.length-i-1; j > i; j--) {
				if (sequenceArray[j-1].compareTo(sequenceArray[j])>0) {
					T swap = sequenceArray[j];
					sequenceArray[j] = sequenceArray[j-1];
					sequenceArray[j-1] = swap;
				}
			}
		}
	}
	
	/**
	 * 双向冒泡排序
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		for (int i = 0; i < sequenceArray.length/2; i++) {
			for (int j = i+1; j < sequenceArray.length-1; j++) {
				if (c.compare(sequenceArray[j], sequenceArray[j+1])>0) {
					T swap = sequenceArray[j];
					sequenceArray[j] = sequenceArray[j+1];
					sequenceArray[j+1] = swap;
				}
			}
			for (int j = sequenceArray.length-i-1; j > i; j--) {
				if (c.compare(sequenceArray[j-1], sequenceArray[j])>0) {
					T swap = sequenceArray[j];
					sequenceArray[j] = sequenceArray[j-1];
					sequenceArray[j-1] = swap;
				}
			}
		}
	}
	
	/**
	 * 双向冒泡排序
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		for (int i = 0; i < sequenceArray.size()/2; i++) {
			for (int j = i+1; j < sequenceArray.size()-1; j++) {
				if (sequenceArray.get(j).compareTo(sequenceArray.get(j+1))>0) {
					T swap = sequenceArray.get(j);
					sequenceArray.set(j, sequenceArray.get(j+1));
					sequenceArray.set(j+1, swap);
//					sequenceArray[j] = sequenceArray[j+1];
//					sequenceArray[j+1] = swap;
				}
			}
			for (int j = sequenceArray.size()-i-1; j > i; j--) {
				if (sequenceArray.get(j-1).compareTo(sequenceArray.get(j))>0) {
					T swap = sequenceArray.get(j);
					sequenceArray.set(j, sequenceArray.get(j-1));
					sequenceArray.set(j-1, swap);
//					sequenceArray[j] = sequenceArray[j-1];
//					sequenceArray[j-1] = swap;
				}
			}
		}
	}
	
	/**
	 * 双向冒泡排序
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		for (int i = 0; i < sequenceArray.size()/2; i++) {
			for (int j = i+1; j < sequenceArray.size()-1; j++) {
				if (c.compare(sequenceArray.get(j), (sequenceArray.get(j+1)))>0) {
					T swap = sequenceArray.get(j);
					sequenceArray.set(j, sequenceArray.get(j+1));
					sequenceArray.set(j+1, swap);
//					sequenceArray[j] = sequenceArray[j+1];
//					sequenceArray[j+1] = swap;
				}
			}
			for (int j = sequenceArray.size()-i-1; j > i; j--) {
				if (c.compare(sequenceArray.get(j-1), (sequenceArray.get(j)))>0) {
					T swap = sequenceArray.get(j);
					sequenceArray.set(j, sequenceArray.get(j-1));
					sequenceArray.set(j-1, swap);
//					sequenceArray[j] = sequenceArray[j-1];
//					sequenceArray[j-1] = swap;
				}
			}
		}
	}
}
