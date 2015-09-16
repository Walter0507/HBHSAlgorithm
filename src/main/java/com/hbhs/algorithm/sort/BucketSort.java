package com.hbhs.algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Bucket sort<br>
 * 桶排序 (Bucket sort)或所谓的箱排序，是一个排序算法，工作的原理是将数组分到有限数量的桶子里。每个桶子再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。桶排序是鸽巢排序的一种归纳结果。当要被排序的数组内的数值是均匀分配的时候，桶排序使用线性时间（Θ（n））。但桶排序并不是 比较排序，他不受到 O(n log n) 下限的影响。<br>
 * <br>执行流程如下<br>
 * 1 设置一个定量的数组当作空桶子。<bR>
 * 2 寻访串行，并且把项目一个一个放到对应的桶子去。<bR>
 * 3 对每个不是空的桶子进行排序。<bR>
 * 4 从不是空的桶子里把项目再放回原来的串行中。<bR>
 * @author walter.xu
 *
 */
public class BucketSort {
	/**
	 * 桶排序
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(T[] sequenceArray){
		// 1 初始化待比较的桶数组
		Object[] bucketArray = initBucket(sequenceArray);
		
		// 2 初始化桶数组中的排序号的序列Index
		Object[] bucketIndexArray = new Object[bucketArray.length];
		for (int i = 0; i < sequenceArray.length; i++) {
			insertIntoBucket(bucketArray, bucketIndexArray, sequenceArray, i);
		}
		wrapSequenceSorted(sequenceArray, bucketIndexArray);
	}
	/**
	 * 插入当前值
	 * @param bucketArray
	 * @param bucketIndexArray
	 * @param sequenceArray
	 * @param currentIndex
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void insertIntoBucket(Object[] bucketArray, Object[] bucketIndexArray,
			T[] sequenceArray, int currentIndex){
		T compareValue = sequenceArray[currentIndex];
		for (int i = bucketArray.length-1; i > 0; i--) {
			// 比较值 比当前值大，则插入
			if (bucketArray[i]==null||compareValue.compareTo((T)bucketArray[i])>=0) {
				// 将sequence插入
				List<Integer> bucketIndexList = (List<Integer>)bucketIndexArray[i];
				if (bucketIndexList==null) {
					bucketIndexList = new ArrayList<Integer>();
				}
				bucketIndexList.add(currentIndex);
				
				for (int j = bucketIndexList.size()-1; j >0 ; j--) {
					if (sequenceArray[bucketIndexList.get(j)].compareTo(sequenceArray[bucketIndexList.get(j-1)])<0) {
						int swapIndex = bucketIndexList.get(j);
						bucketIndexList.set(j, bucketIndexList.get(j-1));
						bucketIndexList.set(j-1, swapIndex);
						break;
					}
				}
				bucketIndexArray[i] = bucketIndexList;
				break;
			}
		}
	}
	
	/**
	 * 随机初始化桶数据，生成所需比较的桶列表,并做好排序
	 * @param sequenceArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> Object[] initBucket(T[] sequenceArray){
		// 生成10个桶 其中第一个桶为空
		Object[] bucketArray = new Object[10];
		int bucketIndex = 1, sequenceIndex = 0;
		// 设置第二个桶的值为带排序的数组第一个值
		bucketArray[bucketIndex] = sequenceArray[sequenceIndex];
		// 增加序列
		bucketIndex++;
		sequenceIndex++;
		while(bucketIndex < bucketArray.length){
			int compare = sequenceArray[sequenceIndex].compareTo((T)bucketArray[bucketIndex-1]);
			if (compare == 0) {
				sequenceIndex ++;
				continue ;
			}
			bucketArray[bucketIndex] = sequenceArray[sequenceIndex];
			
			// 排序
			for (int j = bucketIndex; j > 0; j--) {
				if ((j==1)||(((T)bucketArray[j]).compareTo((T)bucketArray[j-1]) > 0)) {
					break;
				}
				T swap = (T)bucketArray[j];
				bucketArray[j] = bucketArray[j-1];
				bucketArray[j-1] = swap;
			}
			bucketIndex++;
			sequenceIndex++;
		}
		
		return bucketArray;
	}
	
	/**
	 * 桶排序
	 * @param sequenceArray
	 */
	public static <T> void sort(T[] sequenceArray, Comparator<T> c){
		// 1 初始化待比较的桶数组
		Object[] bucketArray = initBucket(sequenceArray, c);
		
		// 2 初始化桶数组中的排序号的序列Index
		Object[] bucketIndexArray = new Object[bucketArray.length];
		for (int i = 0; i < sequenceArray.length; i++) {
			insertIntoBucket(bucketArray, bucketIndexArray, sequenceArray, i, c);
		}
		wrapSequenceSorted(sequenceArray, bucketIndexArray);
	}
	/**
	 * 插入当前值
	 * @param bucketArray
	 * @param bucketIndexArray
	 * @param sequenceArray
	 * @param currentIndex
	 */
	@SuppressWarnings("unchecked")
	private static <T> void insertIntoBucket(Object[] bucketArray, Object[] bucketIndexArray,
			T[] sequenceArray, int currentIndex, Comparator<T> c){
		T compareValue = sequenceArray[currentIndex];
		for (int i = bucketArray.length-1; i > 0; i--) {
			// 比较值 比当前值大，则插入
			if (bucketArray[i]==null||c.compare(compareValue, (T)bucketArray[i])>=0) {
				// 将sequence插入
				List<Integer> bucketIndexList = (List<Integer>)bucketIndexArray[i];
				if (bucketIndexList==null) {
					bucketIndexList = new ArrayList<Integer>();
				}
				bucketIndexList.add(currentIndex);
				
				for (int j = bucketIndexList.size()-1; j >0 ; j--) {
					if (c.compare(sequenceArray[bucketIndexList.get(j)], sequenceArray[bucketIndexList.get(j-1)])<0) {
						int swapIndex = bucketIndexList.get(j);
						bucketIndexList.set(j, bucketIndexList.get(j-1));
						bucketIndexList.set(j-1, swapIndex);
						break;
					}
				}
				bucketIndexArray[i] = bucketIndexList;
				break;
			}
		}
	}
	
	/**
	 * 随机初始化桶数据，生成所需比较的桶列表,并做好排序
	 * @param sequenceArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> Object[] initBucket(T[] sequenceArray, Comparator<T> c){
		// 生成10个桶 其中第一个桶为空
		Object[] bucketArray = new Object[10];
		int bucketIndex = 1, sequenceIndex = 0;
		// 设置第二个桶的值为带排序的数组第一个值
		bucketArray[bucketIndex] = sequenceArray[sequenceIndex];
		// 增加序列
		bucketIndex++;
		sequenceIndex++;
		while(bucketIndex < bucketArray.length){
			int compare = c.compare(sequenceArray[sequenceIndex], (T)bucketArray[bucketIndex-1]);
			if (compare == 0) {
				sequenceIndex ++;
				continue ;
			}
			bucketArray[bucketIndex] = sequenceArray[sequenceIndex];
			
			// 排序
			for (int j = bucketIndex; j > 0; j--) {
				if ((j==1)||c.compare((T)bucketArray[j], (T)bucketArray[j-1])> 0) {
					break;
				}
				T swap = (T)bucketArray[j];
				bucketArray[j] = bucketArray[j-1];
				bucketArray[j-1] = swap;
			}
			bucketIndex++;
			sequenceIndex++;
		}
		
		return bucketArray;
	}
	
	/**
	 * 桶排序
	 * @param sequenceArray
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> sequenceArray){
		// 1 初始化待比较的桶数组
		Object[] bucketArray = initBucket(sequenceArray);
		
		// 2 初始化桶数组中的排序号的序列Index
		Object[] bucketIndexArray = new Object[bucketArray.length];
		for (int i = 0; i < sequenceArray.size(); i++) {
			insertIntoBucket(bucketArray, bucketIndexArray, sequenceArray, i);
		}
		wrapSequenceSorted(sequenceArray, bucketIndexArray);
	}
	/**
	 * 插入当前值
	 * @param bucketArray
	 * @param bucketIndexArray
	 * @param sequenceArray
	 * @param currentIndex
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void insertIntoBucket(Object[] bucketArray, Object[] bucketIndexArray,
			List<T> sequenceArray, int currentIndex){
		T compareValue = sequenceArray.get(currentIndex);
		for (int i = bucketArray.length-1; i > 0; i--) {
			// 比较值 比当前值大，则插入
			if (bucketArray[i]==null||compareValue.compareTo((T)bucketArray[i])>=0) {
				// 将sequence插入
				List<Integer> bucketIndexList = (List<Integer>)bucketIndexArray[i];
				if (bucketIndexList==null) {
					bucketIndexList = new ArrayList<Integer>();
				}
				bucketIndexList.add(currentIndex);
				
				for (int j = bucketIndexList.size()-1; j >0 ; j--) {
					if (sequenceArray.get(bucketIndexList.get(j)).compareTo(sequenceArray.get(bucketIndexList.get(j-1)))<0) {
						int swapIndex = bucketIndexList.get(j);
						bucketIndexList.set(j, bucketIndexList.get(j-1));
						bucketIndexList.set(j-1, swapIndex);
						break;
					}
				}
				bucketIndexArray[i] = bucketIndexList;
				break;
			}
		}
	}
	
	/**
	 * 随机初始化桶数据，生成所需比较的桶列表,并做好排序
	 * @param sequenceArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> Object[] initBucket(List<T> sequenceArray){
		// 生成10个桶 其中第一个桶为空
		Object[] bucketArray = new Object[10];
		int bucketIndex = 1, sequenceIndex = 0;
		// 设置第二个桶的值为带排序的数组第一个值
		bucketArray[bucketIndex] = sequenceArray.get(sequenceIndex);
		// 增加序列
		bucketIndex++;
		sequenceIndex++;
		while(bucketIndex < bucketArray.length){
			int compare = sequenceArray.get(sequenceIndex).compareTo((T)bucketArray[bucketIndex-1]);
			if (compare == 0) {
				sequenceIndex ++;
				continue ;
			}
			bucketArray[bucketIndex] = sequenceArray.get(sequenceIndex);
			
			// 排序
			for (int j = bucketIndex; j > 0; j--) {
				if ((j==1)||(((T)bucketArray[j]).compareTo((T)bucketArray[j-1]) > 0)) {
					break;
				}
				T swap = (T)bucketArray[j];
				bucketArray[j] = bucketArray[j-1];
				bucketArray[j-1] = swap;
			}
			bucketIndex++;
			sequenceIndex++;
		}
		
		return bucketArray;
	}
	
	/**
	 * 桶排序
	 * @param sequenceArray
	 */
	public static <T> void sort(List<T> sequenceArray, Comparator<T> c){
		// 1 初始化待比较的桶数组
		Object[] bucketArray = initBucket(sequenceArray, c);
		
		// 2 初始化桶数组中的排序号的序列Index
		Object[] bucketIndexArray = new Object[bucketArray.length];
		for (int i = 0; i < sequenceArray.size(); i++) {
			insertIntoBucket(bucketArray, bucketIndexArray, sequenceArray, i, c);
		}
		wrapSequenceSorted(sequenceArray, bucketIndexArray);
	}
	/**
	 * 插入当前值
	 * @param bucketArray
	 * @param bucketIndexArray
	 * @param sequenceArray
	 * @param currentIndex
	 */
	@SuppressWarnings("unchecked")
	private static <T> void insertIntoBucket(Object[] bucketArray, Object[] bucketIndexArray,
			List<T> sequenceArray, int currentIndex, Comparator<T> c){
		T compareValue = sequenceArray.get(currentIndex);
		for (int i = bucketArray.length-1; i > 0; i--) {
			// 比较值 比当前值大，则插入
			if (bucketArray[i]==null||c.compare(compareValue, (T)bucketArray[i])>=0) {
				// 将sequence插入
				List<Integer> bucketIndexList = (List<Integer>)bucketIndexArray[i];
				if (bucketIndexList==null) {
					bucketIndexList = new ArrayList<Integer>();
				}
				bucketIndexList.add(currentIndex);
				
				for (int j = bucketIndexList.size()-1; j >0 ; j--) {
					if (c.compare(sequenceArray.get(bucketIndexList.get(j)), sequenceArray.get(bucketIndexList.get(j-1)))<0) {
						int swapIndex = bucketIndexList.get(j);
						bucketIndexList.set(j, bucketIndexList.get(j-1));
						bucketIndexList.set(j-1, swapIndex);
						break;
					}
				}
				bucketIndexArray[i] = bucketIndexList;
				break;
			}
		}
	}
	
	/**
	 * 随机初始化桶数据，生成所需比较的桶列表,并做好排序
	 * @param sequenceArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> Object[] initBucket(List<T> sequenceArray, Comparator<T> c){
		// 生成10个桶 其中第一个桶为空
		Object[] bucketArray = new Object[10];
		int bucketIndex = 1, sequenceIndex = 0;
		// 设置第二个桶的值为带排序的数组第一个值
		bucketArray[bucketIndex] = sequenceArray.get(sequenceIndex);
		// 增加序列
		bucketIndex++;
		sequenceIndex++;
		while(bucketIndex < bucketArray.length){
			int compare = c.compare(sequenceArray.get(sequenceIndex), (T)bucketArray[bucketIndex-1]);
			if (compare == 0) {
				sequenceIndex ++;
				continue ;
			}
			bucketArray[bucketIndex] = sequenceArray.get(sequenceIndex);
			
			// 排序
			for (int j = bucketIndex; j > 0; j--) {
				if ((j==1)||c.compare((T)bucketArray[j], (T)bucketArray[j-1])> 0) {
					break;
				}
				T swap = (T)bucketArray[j];
				bucketArray[j] = bucketArray[j-1];
				bucketArray[j-1] = swap;
			}
			bucketIndex++;
			sequenceIndex++;
		}
		
		return bucketArray;
	}
	
	/**
	 * 将待排序数组按照指定的排序序列重新排序
	 * @param sequenceArray
	 * @param bucketIndexList
	 */
 	@SuppressWarnings("unchecked")
	private static <T> void wrapSequenceSorted(T[] sequenceArray, Object[] bucketIndexList){
		List<Object> tempList = new ArrayList<Object>();
		for (int i = 0; i < bucketIndexList.length; i++) {
			List<Integer> bucketList = (List<Integer>)bucketIndexList[i];
			if (bucketList!=null&&bucketList.size()>0) {
				for (int j = 0; j < bucketList.size(); j++) {
					tempList.add(sequenceArray[bucketList.get(j)]);
				}
			}
		}
		for (int i = 0; i < tempList.size(); i++) {
			sequenceArray[i] = (T)tempList.get(i);
		}
	}
 	/**
	 * 将待排序数组按照指定的排序序列重新排序
	 * @param sequenceArray
	 * @param bucketIndexList
	 */
 	@SuppressWarnings("unchecked")
	private static <T> void wrapSequenceSorted(List<T> sequenceArray, Object[] bucketIndexList){
		List<Object> tempList = new ArrayList<Object>();
		for (int i = 0; i < bucketIndexList.length; i++) {
			List<Integer> bucketList = (List<Integer>)bucketIndexList[i];
			if (bucketList!=null&&bucketList.size()>0) {
				for (int j = 0; j < bucketList.size(); j++) {
					tempList.add(sequenceArray.get(bucketList.get(j)));
				}
			}
		}
		for (int i = 0; i < tempList.size(); i++) {
			sequenceArray.set(i, (T)tempList.get(i));
		}
	}
}
