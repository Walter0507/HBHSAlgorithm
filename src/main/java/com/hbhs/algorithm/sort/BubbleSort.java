package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 冒泡排序算法
 * 冒泡排序算法的运作如下：<BR>
 * 1 比较相邻的元素。如果第一个比第二个大，就交换他们两个。<BR>
 * 2 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。<BR>
 * 3 针对所有的元素重复以上的步骤，除了最后一个。<BR>
 * 4 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。<BR>
 * @author walter.xu
 *
 */
public class BubbleSort {
	/**
	 * 冒泡排序
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		for (int i = sequenceArray.length-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (sequenceArray[j].compareTo(sequenceArray[i])>0) {
					T swap = sequenceArray[i];
					sequenceArray[i] = sequenceArray[j];
					sequenceArray[j] = swap;
				}
			}
		}
	}
	/**
	 * 冒泡排序
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		for (int i = sequenceArray.length-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (c.compare(sequenceArray[j], sequenceArray[i])>0) {
					T swap = sequenceArray[i];
					sequenceArray[i] = sequenceArray[j];
					sequenceArray[j] = swap;
				}
			}
		}
	}
	
	/**
	 * 冒泡排序
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		for (int i = sequenceArray.size()-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (sequenceArray.get(j).compareTo(sequenceArray.get(i))>0) {
					T swap = sequenceArray.get(i);
					sequenceArray.set(i, sequenceArray.get(j));
					sequenceArray.set(j, swap);
//					sequenceArray[i] = sequenceArray[j];
//					sequenceArray[j] = swap;
				}
			}
		}
	}
	/**
	 * 冒泡排序
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		for (int i = sequenceArray.size()-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (c.compare(sequenceArray.get(j), sequenceArray.get(i))>0) {
					T swap = sequenceArray.get(i);
					sequenceArray.set(i, sequenceArray.get(j));
					sequenceArray.set(j, swap);
//					sequenceArray[i] = sequenceArray[j];
//					sequenceArray[j] = swap;
				}
			}
		}
	}
}
