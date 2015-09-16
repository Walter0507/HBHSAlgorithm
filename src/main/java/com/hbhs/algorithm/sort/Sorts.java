package com.hbhs.algorithm.sort;

import java.util.Comparator;
import java.util.List;

public class Sorts {

	public static <T extends Comparable<? super T>> void sortByHeap(List<T> collection){
		HeapSort.sort(collection);
	}
	public static <T> void sortByHeap(List<T> collection, Comparator<T> c){
		HeapSort.sort(collection, c);
	}
	
	public static <T extends Comparable<? super T>> void sortByInsert(List<T> collection){
		InsertionSort.sort(collection);
	}
	public static <T> void sortByInsert(List<T> collection, Comparator<T> c){
		InsertionSort.sort(collection, c);
	}
	
	public static <T extends Comparable<? super T>> void sortByMerge(List<T> collection){
		MergeSort.sort(collection);
	}
	public static <T> void sortByMerge(List<T> collection, Comparator<T> c){
		MergeSort.sort(collection, c);
	}
	
	public static <T extends Comparable<? super T>> void sortByQuick(List<T> collection){
		QuickSort.sort(collection);
	}
	public static <T> void sortByQuick(List<T> collection, Comparator<T> c){
		QuickSort.sort(collection, c);
	}
	
	public static <T extends Comparable<? super T>> void sortByBinaryTree(List<T> collection){
		QuickSort.sort(collection);
	}
	public static <T> void sortByBinaryTree(List<T> collection, Comparator<T> c){
		QuickSort.sort(collection, c);
	}
	
	public static <T extends Comparable<? super T>> void sortByBubbleSort(List<T> collection){
		QuickSort.sort(collection);
	}
	public static <T> void sortByBubbleSort(List<T> collection, Comparator<T> c){
		QuickSort.sort(collection, c);
	}
	public static <T extends Comparable<? super T>> void sortByShell(List<T> collection){
		QuickSort.sort(collection);
	}
	public static <T> void sortByShell(List<T> collection, Comparator<T> c){
		QuickSort.sort(collection, c);
	}
	
	public static <T extends Comparable<? super T>> void sortBySelection(List<T> collection){
		QuickSort.sort(collection);
	}
	public static <T> void sortBySelection(List<T> collection, Comparator<T> c){
		QuickSort.sort(collection, c);
	}

}
