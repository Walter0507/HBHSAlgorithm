package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

/**
 * 梳排序(Comb sort)<br>
 * 梳排序(Comb sort)是一种由Wlodzimierz Dobosiewicz于1980年所发明的不稳定排序算法，并由Stephen
 * Lacey和Richard Box于1991年四月号的Byte杂志中推广。梳排序是改良自泡沫排序和快速排序，其要旨在于消除乌龟，亦即在阵列尾部的小数值，
 * 这些数值是造成泡沫排序缓慢的主因。相对地，兔子，亦即在阵列前端的大数值，不影响泡沫排序的效能。<br>
 * 梳排序中，开始时的间距设定为阵列长度，并在循环中以固定比率递减，通常递减率设定为1.3。在一次循环中，梳排序如同泡沫排序一样把阵列从首到尾扫描一次，
 * 比较及交换两项，不同的是两项的间距不固定于1。如果间距递减至1，梳排序假定输入阵列大致排序好，并以泡沫排序作最后检查及修正。<br>
 * 
 * @author walter.xu
 *
 */
public class CombSort {
	/**
	 * 梳排序(Comb sort)
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		int gap = sequenceArray.length;
		boolean isSorted = false;
		while(gap > 1|| !isSorted){
			isSorted = true;
			if (gap>1) {
				gap = (int)(gap/1.3);
			}
			for (int i = 0; i < sequenceArray.length&&i+gap<sequenceArray.length; i++) {
				if (sequenceArray[i].compareTo(sequenceArray[i+gap])>0) {
					swap(sequenceArray, i, i+gap);
					isSorted = false;
				}
			}
		}
	}
	
	/**
	 * 梳排序(Comb sort)
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		int gap = sequenceArray.length;
		boolean isSorted = false;
		while(gap > 1|| !isSorted){
			isSorted = true;
			if (gap>1) {
				gap = (int)(gap/1.3);
			}
			for (int i = 0; i < sequenceArray.length&&i+gap<sequenceArray.length; i++) {
				if (c.compare(sequenceArray[i], sequenceArray[i+gap])>0) {
					swap(sequenceArray, i, i+gap);
					isSorted = false;
				}
			}
		}
	}
	/**
	 * 梳排序(Comb sort)
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		int gap = sequenceArray.size();
		boolean isSorted = false;
		while(gap > 1|| !isSorted){
			isSorted = true;
			if (gap>1) {
				gap = (int)(gap/1.3);
			}
			for (int i = 0; i < sequenceArray.size()&&i+gap<sequenceArray.size(); i++) {
				if (sequenceArray.get(i).compareTo(sequenceArray.get(i+gap))>0) {
					swap(sequenceArray, i, i+gap);
					isSorted = false;
				}
			}
		}
	}
	
	/**
	 * 梳排序(Comb sort)
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		int gap = sequenceArray.size();
		boolean isSorted = false;
		while(gap > 1|| !isSorted){
			isSorted = true;
			if (gap>1) {
				gap = (int)(gap/1.3);
			}
			for (int i = 0; i < sequenceArray.size()&&i+gap<sequenceArray.size(); i++) {
				if (c.compare(sequenceArray.get(i), sequenceArray.get(i+gap))>0) {
					swap(sequenceArray, i, i+gap);
					isSorted = false;
				}
			}
		}
	}
	/**
	 * swap
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
	 * swap
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
