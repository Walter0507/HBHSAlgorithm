package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 奇偶排序，或奇偶换位排序，或砖排序[1]，是一种相对简单的排序算法，最初发明用于有本地互连的并行计算。这是与冒泡排序特点类似的一种比较排序。
 * 
 * # 假设已有列表a等待排序
 * while True:
 *    sorted = True
 *    # 处理奇-偶对
 *    for i in xrange(1, len(a)-1, 2):
 *      if a[i] > a[i+1]:
 *           a[i], a[i+1] = a[i+1], a[i] # 交换
 *           sorted = False
 *    # 处理偶-奇对
 *    for i in xrange(0, len(a)-1, 2):
 *      if a[i] > a[i+1]:
 *           a[i], a[i+1] = a[i+1], a[i] # 交换
 *           sorted = False
 *    if sorted:
 *      break
 * 
 * @author walter.xu
 *
 */
public class OddEvenSort {
	/**
	 * 
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		boolean isSorted = false;
		while(!isSorted){
			// 预设已经排序成功
			isSorted = true;
			// 奇-偶对
			for (int i = 1; i < sequenceArray.length-1; i = i+2) {
				if (sequenceArray[i].compareTo(sequenceArray[i+1])>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
			// 偶-奇对
			for (int i = 0; i < sequenceArray.length-1; i = i+2) {
				if (sequenceArray[i].compareTo(sequenceArray[i+1])>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		boolean isSorted = false;
		while(isSorted){
			// 预设已经排序成功
			isSorted = true;
			// 奇-偶对
			for (int i = 1; i < sequenceArray.length-1; i = i+2) {
				if (c.compare(sequenceArray[i], sequenceArray[i+1])>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
			// 偶-奇对
			for (int i = 0; i < sequenceArray.length-1; i = i+2) {
				if (c.compare(sequenceArray[i], sequenceArray[i+1])>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		boolean isSorted = false;
		while(isSorted){
			// 预设已经排序成功
			isSorted = true;
			// 奇-偶对
			for (int i = 1; i < sequenceArray.size()-1; i = i+2) {
				if (sequenceArray.get(i).compareTo(sequenceArray.get(i+1))>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
			// 偶-奇对
			for (int i = 0; i < sequenceArray.size()-1; i = i+2) {
				if (sequenceArray.get(i).compareTo(sequenceArray.get(i+1))>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		boolean isSorted = false;
		while(isSorted){
			// 预设已经排序成功
			isSorted = true;
			// 奇-偶对
			for (int i = 1; i < sequenceArray.size()-1; i = i+2) {
				if (c.compare(sequenceArray.get(i), sequenceArray.get(i+1))>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
			// 偶-奇对
			for (int i = 0; i < sequenceArray.size()-1; i = i+2) {
				if (c.compare(sequenceArray.get(i), sequenceArray.get(i+1))>0) {
					swap(sequenceArray, i, i+1);
					isSorted = false;
				}
			}
		}
	}
	/**
	 * 交换值
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
	 * 交换值
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
