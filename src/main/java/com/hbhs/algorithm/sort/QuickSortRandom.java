package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Quick sort by random key.
 * 快速排序优化版本，使用随机元
 * @author walter.xu
 *
 */
public class QuickSortRandom {
	
	public static <T extends Comparable<? super T>> void sort(T[] sequenceForSort){
		quickSort(sequenceForSort, 0, sequenceForSort.length-1);
	}
	
	private static <T extends Comparable<? super T>> void quickSort(T[] sequenceForSort, int startIndex, int endIndex){
		if (startIndex < endIndex) {
			int areaElement = partitionRandomized(sequenceForSort, startIndex, endIndex);
			quickSort(sequenceForSort, startIndex, areaElement-1);
			quickSort(sequenceForSort, areaElement+1, endIndex);
		}
	}
	
	private static <T extends Comparable<? super T>> int partitionRandomized(T[] sequenceForSort, int startIndex, int endIndex){
		int keyIndex = random(startIndex, endIndex);  //主元 随机获取
//		int keyIndex = 9;
		T keyElement = sequenceForSort[keyIndex];
		System.out.println("keyElement:"+keyElement);
//		int keyElement = endIndex;
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j <= endIndex; j++) {
			
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (sequenceForSort[j].compareTo(keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort[j];
				sequenceForSort[j] = sequenceForSort[areaDevide];
				sequenceForSort[areaDevide] = currentKey;
				// 如果当前节点为KEY节点，这个时候有做转换操作，标识该KEY对应的新节点INDEX，便于后面做交换值操作
				if (keyIndex == j) {
					keyIndex = areaDevide;
				}
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort[areaDevide];
		sequenceForSort[areaDevide] = sequenceForSort[keyIndex];
		sequenceForSort[keyIndex] = currentKey;

		return areaDevide+1;
	}
	
	public static <T> void sort(T[] sequenceForSort, Comparator<T> c){
		quickSort(sequenceForSort, 0, sequenceForSort.length-1, c);
	}
	
	private static <T> void quickSort(T[] sequenceForSort, 
			int startIndex, int endIndex, Comparator<T> c){
		if (startIndex < endIndex) {
			int areaElement = partitionRandomized(sequenceForSort, startIndex, endIndex, c);
			quickSort(sequenceForSort, startIndex, areaElement-1, c);
			quickSort(sequenceForSort, areaElement+1, endIndex, c);
		}
	}
	
	private static <T> int partitionRandomized(T[] sequenceForSort, int startIndex, int endIndex, Comparator<T> c){
		int keyIndex = random(startIndex, endIndex);  //主元 随机获取
//		int keyIndex = 9;
		T keyElement = sequenceForSort[keyIndex];
		System.out.println("keyElement:"+keyElement);
//		int keyElement = endIndex;
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j <= endIndex; j++) {
			
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (c.compare(sequenceForSort[j], keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort[j];
				sequenceForSort[j] = sequenceForSort[areaDevide];
				sequenceForSort[areaDevide] = currentKey;
				// 如果当前节点为KEY节点，这个时候有做转换操作，标识该KEY对应的新节点INDEX，便于后面做交换值操作
				if (keyIndex == j) {
					keyIndex = areaDevide;
				}
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort[areaDevide];
		sequenceForSort[areaDevide] = sequenceForSort[keyIndex];
		sequenceForSort[keyIndex] = currentKey;

		return areaDevide+1;
	}
	
	private static int random(int start, int end){
		return new Random().nextInt((end-start))+start;
	}
	
	/**
	 * List
	 */
	
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceForSort){
		quickSort(sequenceForSort, 0, sequenceForSort.size()-1);
	}
	
	private static <T extends Comparable<? super T>> void quickSort(List<T> sequenceForSort, int startIndex, int endIndex){
		if (startIndex < endIndex) {
			int areaElement = partitionRandomized(sequenceForSort, startIndex, endIndex);
			quickSort(sequenceForSort, startIndex, areaElement-1);
			quickSort(sequenceForSort, areaElement+1, endIndex);
		}
	}
	
	private static <T extends Comparable<? super T>> int partitionRandomized(List<T> sequenceForSort, int startIndex, int endIndex){
		int keyIndex = random(startIndex, endIndex);  //主元 随机获取
//		int keyIndex = 9;
		T keyElement = sequenceForSort.get(keyIndex);
		System.out.println("keyElement:"+keyElement);
//		int keyElement = endIndex;
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j <= endIndex; j++) {
			
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (sequenceForSort.get(j).compareTo(keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort.get(j);
				sequenceForSort.set(j, sequenceForSort.get(areaDevide));
				sequenceForSort.set(areaDevide, currentKey);
//				sequenceForSort[j] = sequenceForSort[areaDevide];
//				sequenceForSort[areaDevide] = currentKey;
				// 如果当前节点为KEY节点，这个时候有做转换操作，标识该KEY对应的新节点INDEX，便于后面做交换值操作
				if (keyIndex == j) {
					keyIndex = areaDevide;
				}
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort.get(areaDevide);
		sequenceForSort.set(areaDevide, sequenceForSort.get(keyIndex));
		sequenceForSort.set(keyIndex, currentKey);
//		sequenceForSort[areaDevide] = sequenceForSort[keyIndex];
//		sequenceForSort[keyIndex] = currentKey;

		return areaDevide+1;
	}
	
	public static <T> void sort(List<T> sequenceForSort, Comparator<T> c){
		quickSort(sequenceForSort, 0, sequenceForSort.size()-1, c);
	}
	
	private static <T> void quickSort(List<T> sequenceForSort, 
			int startIndex, int endIndex, Comparator<T> c){
		if (startIndex < endIndex) {
			int areaElement = partitionRandomized(sequenceForSort, startIndex, endIndex, c);
			quickSort(sequenceForSort, startIndex, areaElement-1, c);
			quickSort(sequenceForSort, areaElement+1, endIndex, c);
		}
	}
	
	private static <T> int partitionRandomized(List<T> sequenceForSort, int startIndex, int endIndex, Comparator<T> c){
		int keyIndex = random(startIndex, endIndex);  //主元 随机获取
//		int keyIndex = 9;
		T keyElement = sequenceForSort.get(keyIndex);
		System.out.println("keyElement:"+keyElement);
//		int keyElement = endIndex;
		int areaDevide = startIndex-1;   // 左区间和右区间的节点，对应要返回的节点值前一位
		// 循环start-(end-1)操作数，与主元对比大小
		for (int j = startIndex; j <= endIndex; j++) {
			
			// 小于主元时，则将区域节点加一，然后交换区域节点的值和当前j位置的值
			if (c.compare(sequenceForSort.get(j), keyElement)<=0) {
				areaDevide++;
				T currentKey = sequenceForSort.get(j);
				sequenceForSort.set(j, sequenceForSort.get(areaDevide));
				sequenceForSort.set(areaDevide, currentKey);
//				sequenceForSort[j] = sequenceForSort[areaDevide];
//				sequenceForSort[areaDevide] = currentKey;
				// 如果当前节点为KEY节点，这个时候有做转换操作，标识该KEY对应的新节点INDEX，便于后面做交换值操作
				if (keyIndex == j) {
					keyIndex = areaDevide;
				}
			}
		}
		// 循环结束，交换主元和当前区域节点的key值
		T currentKey = sequenceForSort.get(areaDevide);
		sequenceForSort.set(areaDevide, sequenceForSort.get(keyIndex));
		sequenceForSort.set(keyIndex, currentKey);
//		sequenceForSort[areaDevide] = sequenceForSort[keyIndex];
//		sequenceForSort[keyIndex] = currentKey;

		return areaDevide+1;
	}
}
