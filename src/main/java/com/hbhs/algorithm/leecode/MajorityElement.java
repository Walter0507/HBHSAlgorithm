package com.hbhs.algorithm.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * <B>Majority Element</B><BR>
 * <BR>
 * <BR>Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <BR>
 * <BR>You may assume that the array is non-empty and the majority element always exist in the array.
 * <BR>
 * <BR>solution
 * <li>Runtime: O(n2) — Brute force solution: Check each element if it is the majority element.</li>
 * <li>Runtime: O(n), Space: O(n) — Hash table: Maintain a hash table of the counts of each element, then find the most common one.</li>
 * <li>Runtime: O(n log n) — Sorting: As we know more than half of the array are elements of the same value, we can sort the array and all majority elements will be grouped into one contiguous chunk. Therefore, the middle (n/2th) element must also be the majority element.</li>
 * <li>Average runtime: O(n), Worst case runtime: Infinity — Randomization: Randomly pick an element and check if it is the majority element. If it is not, do the random pick again until you find the majority element. As the probability to pick the majority element is greater than 1/2, the expected number of attempts is < 2.</li>
 * <li>Runtime: O(n log n) — Divide and conquer: Divide the array into two halves, then find the majority element A in the first half and the majority element B in the second half. The global majority element must either be A or B. If A == B, then it automatically becomes the global majority element. If not, then both A and B are the candidates for the majority element, and it is suffice to check the count of occurrences for at most two candidates. The runtime complexity, T(n) = T(n/2) + 2n = O(n log n).</li>
 * <li>Runtime: O(n) — Moore voting algorithm: We maintain a current candidate and a counter initialized to 0. As we iterate the array, we look at the current element x:
 * <br>1. If the counter is 0, we set the current candidate to x and the counter to 1.
 * <br>2. If the counter is not 0, we increment or decrement the counter based on whether x is the current candidate.
 * <br>After one pass, the current candidate is the majority element. Runtime complexity = O(n).</li>
 * <li>Runtime: O(n) — Bit manipulation: We would need 32 iterations, each calculating the number of 1's for the ith bit of all n numbers. Since a majority must exist, therefore, either count of 1's > count of 0's or vice versa (but can never be equal). The majority number’s ith bit must be the one bit that has the greater count.</li>
 * 
 * @author walter.xu
 *
 */
public class MajorityElement {

	public static void main(String[] args) {
		int[] num = new int[]{1,2,1,1,1,1,4,1,5,1,6,1,7,1,8,1,9,1,0,1,2,1,2,2,3};
//		int[] num = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//		System.out.println(solution1(num));
//		System.out.println(solution2(num));
//		System.out.println(solution3(num));
//		System.out.println(solution4(num));
		System.out.println(solution5(num));
	}
	/**
	 * 暴力破解
	 * @param num
	 * @return
	 */
	public static int solution1(int[] num){
		int target = 0;
		int count = 0;
		for (int i = 1; i < num.length; i++) {
			int current = num[i];
			int currentCount = 1;
			for (int j = i; j < num.length; j++) {
				if (current == num[j]) {
					currentCount++;
				}
			}
			if (currentCount > count) {
				target = current;
				count = currentCount;
			}
		}
		return target;
	}
	/**
	 * HASHMAP
	 * @param num
	 * @return
	 */
	public static int solution2(int[] num){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < num.length; i++) {
			map.put(num[i],map.get(num[i])==null?1:(map.get(num[i])+1));
		}
		Iterator<Integer> keyIterator = map.keySet().iterator();
		int target = 0;
		int count = 0;
		while(keyIterator.hasNext()){
			int current = keyIterator.next();
			int currentCount = map.get(current);
			if (currentCount > count) {
				target = current;
				count = currentCount;
			}
		}
		return target;
	}
	/**
	 * 排序
	 * @param num
	 * @return
	 */
	public static int solution3(int[] num){
		quickSort(num, 0, num.length-1);
		System.out.println(Arrays.toString(num));
		return num[num.length/2];
	}
	
	public static void quickSort(int[] num, int startIndex, int endIndex){
		if (endIndex-startIndex<=1) {
			return ;
		}
		int leftIndex = startIndex-1;
		for (int i = startIndex; i < endIndex; i++) {
			if (num[i]<num[endIndex]) {
				leftIndex++;
				if (leftIndex!=i) {
					int temp = num[leftIndex];
					num[leftIndex] = num[i];
					num[i] = temp;
				}
			}
		}
		if (leftIndex+1!=endIndex) {
			int temp = num[endIndex];
			num[endIndex] = num[leftIndex+1];
			num[leftIndex+1] = temp;
		}
		quickSort(num, startIndex, leftIndex);
		quickSort(num, leftIndex+2, endIndex);
	}
	/**
	 * 随机index，由于一半以上均是相同数字，所以随机选择情况下会很大可能获取到随机值
	 * @param num
	 * @return
	 */
	public static int solution4(int[] num){
		
		while(true){
			int index = (int) (Math.random()*num.length);
			int count = 0;
			for (int i = 0; i < num.length; i++) {
				if (num[i] == num[index]) {
					count++;
				}
			}
			if (count >= num.length/2) {
				return num[index];
			}
		}
	}
	/**
	 * Moore voting algorithm算法
	 * @param num
	 * @return
	 */
	public static int solution5(int[] num){
		int candidate = 0;
		int count = 0;
		for (int i = 0; i < num.length; i++) {
			if (count==0) {
				candidate = num[i];
				count = 1;
			}else{
				if (candidate == num[i]) {
					count++;
				}else{
					count--;
				}
			}
		}
		return candidate;
	}
}
