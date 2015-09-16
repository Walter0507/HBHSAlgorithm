package com.hbhs.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序<br>
 * 是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。<Br>
 * 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 * <br>
 * 
 * @author walter.xu
 *
 */
public class RadixSort {
	/**
	 * Radix sort
	 * @param sequenceArray
	 */
	public static void sort(int[] sequenceArray){
		int maxDigit = queryMaxDigit(sequenceArray);
		int[] tempArray = new int[sequenceArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = sequenceArray[i];
		}
		countingSortByNum(tempArray, sequenceArray, maxDigit);
	}
	
	/**
	 * 
	 * @param sequenceArray
	 * @return
	 */
	private static int queryMaxDigit(int[] sequenceArray){
		int maxDigit = 0;
		for (int i = 0; i < sequenceArray.length; i++) {
			int count = 0;
			int value = sequenceArray[i];
			while(value > 0){
				value /= 10;
				count++;
			}
			if (count > maxDigit) {
				maxDigit = count;
			}
		}
		return maxDigit;
	}

	/**
	 * 按照指定的位数排序
	 * @param sequenceForSort
	 * @param digitValue
	 * @return
	 */
	private static void countingSortByNum(int[] sequenceForSort,int[] sequenceSorted, int digitValue){
		//对临时存储区赋初始值
		int[] currentStorage = new int[10];
		
		//计数, 迭代待排序数组，将待排序数组中每个key出现一次，则将当前临时存储区的对应值加一
		for (int i = 0; i < sequenceForSort.length; i++) {
			int digitNum = getValueByDigit(sequenceForSort[i], digitValue);
			currentStorage[digitNum] = currentStorage[digitNum]+1;
		}
		
		//计算临时存储区的当前i位置的包含小于或者等于i的元素个数
		for (int i = 1; i < 10; i++) {
			currentStorage[i] = currentStorage[i]+currentStorage[i-1];
		}

		//根据上面临时存储区的位置信息，得到排序结果
		for (int i = (sequenceForSort.length-1); i >= 0; i--) {
			int digitNum = getValueByDigit(sequenceForSort[i], digitValue);
			sequenceSorted[currentStorage[digitNum]-1] = sequenceForSort[i];
			currentStorage[digitNum]--;
		}
		
	}
	
	/**
	 * Radix sort
	 * @param sequenceArray
	 */
	public static void sort(Integer[] sequenceArray){
		int maxDigit = queryMaxDigit(sequenceArray);
		int[] tempArray = new int[sequenceArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = sequenceArray[i];
		}
		countingSortByNum(tempArray, sequenceArray, maxDigit);
	}
	
	/**
	 * 按照指定的位数排序
	 * @param sequenceForSort
	 * @param digitValue
	 * @return
	 */
	private static void countingSortByNum(int[] sequenceForSort,Integer[] sequenceSorted, int digitValue){
		//对临时存储区赋初始值
		int[] currentStorage = new int[10];
		
		//计数, 迭代待排序数组，将待排序数组中每个key出现一次，则将当前临时存储区的对应值加一
		for (int i = 0; i < sequenceForSort.length; i++) {
			int digitNum = getValueByDigit(sequenceForSort[i], digitValue);
			currentStorage[digitNum] = currentStorage[digitNum]+1;
		}
		
		//计算临时存储区的当前i位置的包含小于或者等于i的元素个数
		for (int i = 1; i < 10; i++) {
			currentStorage[i] = currentStorage[i]+currentStorage[i-1];
		}

		//根据上面临时存储区的位置信息，得到排序结果
		for (int i = (sequenceForSort.length-1); i >= 0; i--) {
			int digitNum = getValueByDigit(sequenceForSort[i], digitValue);
			sequenceSorted[currentStorage[digitNum]-1] = sequenceForSort[i];
			currentStorage[digitNum]--;
		}
		
	}
	
	/**
	 * 
	 * @param sequenceArray
	 * @return
	 */
	private static int queryMaxDigit(Integer[] sequenceArray){
		int maxDigit = 0;
		for (int i = 0; i < sequenceArray.length; i++) {
			int count = 0;
			int value = sequenceArray[i];
			while(value > 0){
				value /= 10;
				count++;
			}
			if (count > maxDigit) {
				maxDigit = count;
			}
		}
		return maxDigit;
	}
	
	
	/**
	 * Radix sort
	 * @param sequenceArray
	 */
	public static void sort(List<Integer> sequenceArray){
		int maxDigit = queryMaxDigit(sequenceArray);
		List<Integer> tempArray = new ArrayList<Integer>();
		for (int i = 0; i < sequenceArray.size(); i++) {
			tempArray.add(sequenceArray.get(i));
		}
		countingSortByNum(tempArray, sequenceArray, maxDigit);
	}
	
	/**
	 * 按照指定的位数排序
	 * @param sequenceForSort
	 * @param digitValue
	 * @return
	 */
	private static void countingSortByNum(List<Integer> sequenceForSort,List<Integer> sequenceSorted, int digitValue){
		//对临时存储区赋初始值
		int[] currentStorage = new int[10];
		
		//计数, 迭代待排序数组，将待排序数组中每个key出现一次，则将当前临时存储区的对应值加一
		for (int i = 0; i < sequenceForSort.size(); i++) {
			int digitNum = getValueByDigit(sequenceForSort.get(i), digitValue);
			currentStorage[digitNum] = currentStorage[digitNum]+1;
		}
		
		//计算临时存储区的当前i位置的包含小于或者等于i的元素个数
		for (int i = 1; i < 10; i++) {
			currentStorage[i] = currentStorage[i]+currentStorage[i-1];
		}

		//根据上面临时存储区的位置信息，得到排序结果
		for (int i = (sequenceForSort.size()-1); i >= 0; i--) {
			int digitNum = getValueByDigit(sequenceForSort.get(i), digitValue);
			sequenceSorted.set(currentStorage[digitNum]-1, sequenceForSort.get(i));
			currentStorage[digitNum]--;
		}
		
	}
	
	/**
	 * 
	 * @param sequenceArray
	 * @return
	 */
	private static int queryMaxDigit(List<Integer> sequenceArray){
		int maxDigit = 0;
		for (int i = 0; i < sequenceArray.size(); i++) {
			int count = 0;
			int value = sequenceArray.get(i);
			while(value > 0){
				value /= 10;
				count++;
			}
			if (count > maxDigit) {
				maxDigit = count;
			}
		}
		return maxDigit;
	}

	
	/**
	 * 根据位数返回对应位数的字符
	 * @param number
	 * @param digit
	 * @return
	 */
	private static int getValueByDigit(int number,int digit){
		String numStr = String.valueOf(number);
		if(digit > numStr.length()||digit<=0) {
			return 0 ;
		}
		return Integer.parseInt(String.valueOf(numStr.charAt(numStr.length()-digit)));
	}
}
