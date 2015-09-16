package com.hbhs.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/*
 * 计数排序<BR>
 *  计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。<BR>
 *  1 找出待排序的数组中最大和最小的元素<BR>
 *  2 统计数组中每个值为i的元素出现的次数，存入数组C的第i项<BR>
 *  3 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）<BR>
 *  4 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1<BR>
 */
public class CountSort {
	/**
	 * 计数排序，针对集中数字有较好的排序
	 * @param sequenceForArray
	 */
	public static void sort(Integer[] sequenceForArray){
		int maxValue = sequenceForArray[0], minValue = sequenceForArray[0];
		// 获取最大和最小值
		for (int i = 1; i < sequenceForArray.length; i++) {
			if (sequenceForArray[i] < minValue) {
				minValue = sequenceForArray[i];
			}
			if (sequenceForArray[i] > maxValue) {
				maxValue = sequenceForArray[i];
			}
		}
		int[] countArray = new int[maxValue-minValue+1];
		// 计数
		for (int i = 0; i < sequenceForArray.length; i++) {
			int value = sequenceForArray[i];
			countArray[value- minValue] += 1;
		}
		// 计算每个值所在位置
		Integer[] tempArray = new Integer[sequenceForArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = sequenceForArray[i];
		}
		for (int i = 1; i < countArray.length; i++) {
			countArray[i] += countArray[i-1];
		}
		// 整理设置
		for (int i = tempArray.length-1; i >=0 ; i--) {
			int value = tempArray[i];
			sequenceForArray[countArray[value-minValue]-1] = value;
			countArray[value-minValue] -= 1;
		}
	}
	
	/**
	 * 计数排序，针对集中数字有较好的排序
	 * @param sequenceForArray
	 */
	public static void sort(int[] sequenceForArray){
		int maxValue = sequenceForArray[0], minValue = sequenceForArray[0];
		// 获取最大和最小值
		for (int i = 1; i < sequenceForArray.length; i++) {
			if (sequenceForArray[i] < minValue) {
				minValue = sequenceForArray[i];
			}
			if (sequenceForArray[i] > maxValue) {
				maxValue = sequenceForArray[i];
			}
		}
		int[] countArray = new int[maxValue-minValue+1];
		// 计数
		for (int i = 0; i < sequenceForArray.length; i++) {
			int value = sequenceForArray[i];
			countArray[value- minValue] += 1;
		}
		// 计算每个值所在位置
		Integer[] tempArray = new Integer[sequenceForArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = sequenceForArray[i];
		}
		for (int i = 1; i < countArray.length; i++) {
			countArray[i] += countArray[i-1];
		}
		// 整理设置
		for (int i = tempArray.length-1; i >=0 ; i--) {
			int value = tempArray[i];
			sequenceForArray[countArray[value-minValue]-1] = value;
			countArray[value-minValue] -= 1;
		}
	}
	/**
	 * 计数排序，针对集中数字有较好的排序
	 * @param sequenceForArray
	 */
	public static void sort(List<Integer> sequenceForArray){
		int maxValue = sequenceForArray.get(0), minValue = sequenceForArray.get(0);
		// 获取最大和最小值
		for (int i = 1; i < sequenceForArray.size(); i++) {
			if (sequenceForArray.get(i) < minValue) {
				minValue = sequenceForArray.get(i);
			}
			if (sequenceForArray.get(i) > maxValue) {
				maxValue = sequenceForArray.get(i);
			}
		}
		int[] countArray = new int[maxValue-minValue+1];
		// 计数
		for (int i = 0; i < sequenceForArray.size(); i++) {
			int value = sequenceForArray.get(i);
			countArray[value- minValue] += 1;
		}
		// 计算每个值所在位置
		
		for (int i = 1; i < countArray.length; i++) {
			countArray[i] += countArray[i-1];
		}
		List<Integer> tempArray = new ArrayList<Integer>();
		for (int i = 0; i < tempArray.size(); i++) {
			tempArray.add(sequenceForArray.get(i));
		}
		// 整理设置
		for (int i = tempArray.size()-1; i >=0 ; i--) {
			int value = tempArray.get(i);
			sequenceForArray.set(countArray[value-minValue]-1, value);
			countArray[value-minValue] -= 1;
		}
	}
}
