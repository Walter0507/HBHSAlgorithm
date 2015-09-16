package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;
/**
 * 选择排序的改良版本，一次性查找中查询同时查询到最大和最小值
 * @author walter.xu
 *
 */
public class SelectSort {
	/**
	 * 选择排序算法
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		
		for (int i = 0; i < sequenceArray.length; i++) {
			int minIndex = i;
			int maxIndex = sequenceArray.length-i-1;
			for (int j = i+1; j < sequenceArray.length-i; j++) {
				if (sequenceArray[minIndex].compareTo(sequenceArray[j])>0) {
					minIndex = j;
				}
				if (sequenceArray[maxIndex].compareTo(sequenceArray[j])<0) {
					maxIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
			if (maxIndex!=i) {
				swap(sequenceArray, sequenceArray.length-i-1, maxIndex);
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
			int maxIndex = sequenceArray.length-i-1;
			for (int j = i+1; j < sequenceArray.length-i; j++) {
				if (c.compare(sequenceArray[minIndex], sequenceArray[j])>0) {
					minIndex = j;
				}
				if (c.compare(sequenceArray[maxIndex], sequenceArray[j])<0) {
					maxIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
			if (maxIndex!=i) {
				swap(sequenceArray, sequenceArray.length-i-1, maxIndex);
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
			int maxIndex = sequenceArray.size()-i-1;
			for (int j = i+1; j < sequenceArray.size()-i; j++) {
				if (sequenceArray.get(minIndex).compareTo(sequenceArray.get(j))>0) {
					minIndex = j;
				}
				if (sequenceArray.get(maxIndex).compareTo(sequenceArray.get(j))<0) {
					maxIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
			if (maxIndex!=i) {
				swap(sequenceArray, sequenceArray.size()-i-1, maxIndex);
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
			int maxIndex = sequenceArray.size()-i-1;
			for (int j = i+1; j < sequenceArray.size()-i; j++) {
				if (c.compare(sequenceArray.get(minIndex), sequenceArray.get(j))>0) {
					minIndex = j;
				}
				if (c.compare(sequenceArray.get(maxIndex), sequenceArray.get(j))<0) {
					maxIndex = j;
				}
			}
			if (minIndex!=i) {
				swap(sequenceArray, i, minIndex);
			}
			if (maxIndex!=i) {
				swap(sequenceArray, sequenceArray.size()-i-1, maxIndex);
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
