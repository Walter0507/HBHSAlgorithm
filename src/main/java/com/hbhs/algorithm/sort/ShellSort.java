package com.hbhs.algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）
 * input: an array a of length n with array elements numbered 0 to n − 1
 * inc ← round(n/2)
 * while inc > 0 do:    
 *     for i = inc .. n − 1 do:        
 *        temp ← a[i]
 *        j ← i
 *        while j ≥ inc and a[j − inc] > temp do:
 *          a[j] ← a[j − inc] 
 *          j ← j − inc   
 *        a[j] ← temp  
 *     inc ← round(inc / 2)
 * 
 * STEP 1:
 * 13 14 94 33 82
 * 25 59 94 65 23
 * 45 27 73 25 39
 * 10
 * 
 * STEP 2:
 * 10 14 73 25 23
 * 13 27 94 33 39
 * 25 59 94 65 82
 * 45
 * 
 * STEP 3....
 * 10 14 73
 * 25 23 13
 * 27 94 33
 * 39 25 59
 * 94 65 82
 * 45
 * @author walter.xu
 *
 */
public class ShellSort {
	/**
	 * Shell sort
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		// 步长列表
		List<Integer> gapList = getGapArray(sequenceArray.length);
		// 循环步长
		for (int step = gapList.size()-1; step >= 0; step--) {
			int stepSize = gapList.get(step);
			for (int i = stepSize; i < sequenceArray.length; i++) {
				// 做简单插入排序操作
				simpleInsertSort(sequenceArray, stepSize, i);
			}
		}
	}
	/**
	 * 简单插入排序，排序的序列为：
	 * A[currentIndex],A[currentIndex-stepSize],A[currentIndex-2*stepSize],A[currentIndex-3*stepSize]...
	 * @param sequenceArray
	 * @param stepSize
	 * @param currentIndex
	 */
	private static <T extends Comparable<? super T>> void simpleInsertSort(T[] sequenceArray, int stepSize,
			int currentIndex){
		T currentValue = sequenceArray[currentIndex];
		while (currentIndex >= stepSize) {
			// 如果上个节点比当前节点达
			if (sequenceArray[currentIndex-stepSize].compareTo(sequenceArray[currentIndex])<0) {
				// 如果找到一个节点比当前节点小，则表示该节点为当前值待插入的节点
				break;
			}
			// 如果上一个值比当前值要大，则后移上一个值到当前值，同时将指针继续往前移一位
			sequenceArray[currentIndex] = sequenceArray[currentIndex-stepSize];
			currentIndex -= stepSize;
		}
		// 设置当前参照节点值
		sequenceArray[currentIndex] = currentValue;
	}
	
	/**
	 * Shell sort
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		// 步长列表
		List<Integer> gapList = getGapArray(sequenceArray.length);
		// 循环步长
		for (int step = gapList.size()-1; step >= 0; step--) {
			int stepSize = gapList.get(step);
			for (int i = stepSize; i < sequenceArray.length; i++) {
				// 做简单插入排序操作
				simpleInsertSort(sequenceArray, stepSize, i, c);
			}
		}
	}
	/**
	 * 简单插入排序，排序的序列为：
	 * A[currentIndex],A[currentIndex-stepSize],A[currentIndex-2*stepSize],A[currentIndex-3*stepSize]...
	 * @param sequenceArray
	 * @param stepSize
	 * @param currentIndex
	 */
	private static <T> void simpleInsertSort(T[] sequenceArray, int stepSize,
			int currentIndex, Comparator<T> c){
		T currentValue = sequenceArray[currentIndex];
		while (currentIndex >= stepSize) {
			// 如果上个节点比当前节点达
			if (c.compare(sequenceArray[currentIndex-stepSize], sequenceArray[currentIndex])<0) {
				// 如果找到一个节点比当前节点小，则表示该节点为当前值待插入的节点
				break;
			}
			// 如果上一个值比当前值要大，则后移上一个值到当前值，同时将指针继续往前移一位
			sequenceArray[currentIndex] = sequenceArray[currentIndex-stepSize];
			currentIndex -= stepSize;
		}
		// 设置当前参照节点值
		sequenceArray[currentIndex] = currentValue;
	}
	
	/**
	 * Shell sort
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		// 步长列表
		List<Integer> gapList = getGapArray(sequenceArray.size());
		// 循环步长
		for (int step = gapList.size()-1; step >= 0; step--) {
			int stepSize = gapList.get(step);
			for (int i = stepSize; i < sequenceArray.size(); i++) {
				// 做简单插入排序操作
				simpleInsertSort(sequenceArray, stepSize, i);
			}
		}
	}
	/**
	 * 简单插入排序，排序的序列为：
	 * A[currentIndex],A[currentIndex-stepSize],A[currentIndex-2*stepSize],A[currentIndex-3*stepSize]...
	 * @param sequenceArray
	 * @param stepSize
	 * @param currentIndex
	 */
	private static <T extends Comparable<? super T>> void simpleInsertSort(List<T> sequenceArray, int stepSize,
			int currentIndex){
		T currentValue = sequenceArray.get(currentIndex);
		while (currentIndex >= stepSize) {
			// 如果上个节点比当前节点达
			if (sequenceArray.get(currentIndex-stepSize).compareTo(sequenceArray.get(currentIndex))<0) {
				// 如果找到一个节点比当前节点小，则表示该节点为当前值待插入的节点
				break;
			}
			// 如果上一个值比当前值要大，则后移上一个值到当前值，同时将指针继续往前移一位
			sequenceArray.set(currentIndex, sequenceArray.get(currentIndex-stepSize));
//			sequenceArray[currentIndex] = sequenceArray[currentIndex-stepSize];
			currentIndex -= stepSize;
		}
		// 设置当前参照节点值
		sequenceArray.set(currentIndex, currentValue);
//		sequenceArray[currentIndex] = currentValue;
	}
	
	/**
	 * Shell sort
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		// 步长列表
		List<Integer> gapList = getGapArray(sequenceArray.size());
		// 循环步长
		for (int step = gapList.size()-1; step >= 0; step--) {
			int stepSize = gapList.get(step);
			for (int i = stepSize; i < sequenceArray.size(); i++) {
				// 做简单插入排序操作
				simpleInsertSort(sequenceArray, stepSize, i, c);
			}
		}
	}
	/**
	 * 简单插入排序，排序的序列为：
	 * A[currentIndex],A[currentIndex-stepSize],A[currentIndex-2*stepSize],A[currentIndex-3*stepSize]...
	 * @param sequenceArray
	 * @param stepSize
	 * @param currentIndex
	 */
	private static <T> void simpleInsertSort(List<T> sequenceArray, int stepSize,
			int currentIndex, Comparator<T> c){
		T currentValue = sequenceArray.get(currentIndex);
		while (currentIndex >= stepSize) {
			// 如果上个节点比当前节点达
			if (c.compare(sequenceArray.get(currentIndex-stepSize), sequenceArray.get(currentIndex))<0) {
				// 如果找到一个节点比当前节点小，则表示该节点为当前值待插入的节点
				break;
			}
			// 如果上一个值比当前值要大，则后移上一个值到当前值，同时将指针继续往前移一位
			sequenceArray.set(currentIndex, sequenceArray.get(currentIndex-stepSize));
//			sequenceArray[currentIndex] = sequenceArray[currentIndex-stepSize];
			currentIndex -= stepSize;
		}
		// 设置当前参照节点值
		sequenceArray.set(currentIndex, currentValue);
//		sequenceArray[currentIndex] = currentValue;
	}
	
	/**
	 * 获取所有步长的数组,可以选择普通的n/2方式.
	 * 经查询后发现最好步长串行是由Sedgewick提出的 (1, 5, 19, 41, 109,...)
	 * 该串行的项来自 9 * 4^i - 9 * 2^i + 1 和 4^i - 3 * 2^i + 1 .
	 * 本例中采用步长公式为：gap*3+1: by Knuth,1973>: 1, 4, 13, 40, 121
	 * @param sequenceSize
	 * @return
	 */
	private static List<Integer> getGapArray(int sequenceSize){
		List<Integer> gapList = new ArrayList<Integer>();
		int gapSize = 0;
		while(gapSize < sequenceSize){
			gapSize = 3*gapSize+1;
			if (gapSize < sequenceSize) {
				gapList.add(gapSize);
			}
			
		}
		return gapList;
	}
	
}
