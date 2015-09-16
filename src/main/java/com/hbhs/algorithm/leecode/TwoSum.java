package com.hbhs.algorithm.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.<br>
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.<br>
 * 
 * You may assume that each input would have exactly one solution.<br>
 * 
 * Input: numbers={2, 7, 11, 15}, target=9<br>
 * Output: index1=1, index2=2<br>
 * 
 * @author walter.xu<br>
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		int[] numbers = new int[]{3,2,4};
		int target = 6;
//		int[] result = solution1(numbers, target);
		int[] result = solution2(numbers, target);
		System.out.println("Result: "+Arrays.toString(result));

	}
	
	public static int[] solution1(int[] numbers, int target){
		if (numbers==null||numbers.length < 0) {
			return null;
		}
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i+1; j < numbers.length; j++) {
				if (numbers[i]+numbers[j]==target) {
					return new int[]{i+1,j+1};
				}
			}
		}
		return null;
	}
	
	public static int[] solution2(int[] numbers, int target){
		if (numbers==null||numbers.length < 0) {
			return null;
		}
		Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			int intElse = target - numbers[i];
			if (temp.get(intElse)!=null) {
				return new int[]{temp.get(intElse),i+1};
			}
			if (temp.get(numbers[i])==null) {
				temp.put(numbers[i], i+1);
			}
		}
		return null;
	}
	
}
