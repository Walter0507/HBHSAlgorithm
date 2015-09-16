package com.hbhs.algorithm.leecode;

/**
 * <B>Find Minimum in Rotated Sorted Array</B><BR>
 * <Br>
 * <Br>Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <Br>
 * <Br>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <Br>
 * <Br>Find the minimum element.
 * <Br>
 * <Br>You may assume no duplicate exists in the array.
 * @author walter.xu
 *
 */
public class FindMinimumInRotatedSortedArray {

	public static void main(String[] args) {
		int[] num = new int[]{1,2,3,4,5,6,7};
//		System.out.println(solution1(num));
		num = new int[]{10,10,10,1,10};
		System.out.println(solution1(num));

	}
	
	public static int solution1(int[] num){
		if (num==null||num.length==0) {
			return -1;
		}
		if (num.length==1) {
			return num[0];
		}
		if (num.length==2) {
			return num[0]>num[1]?num[1]:num[0];
		}
		boolean isUpper = true;
		int prefix, next;
		for (int i = 1; i < num.length; i++) {
			prefix = num[i-1];
			next = (i==num.length-1)?num[0]:num[i+1];
			if (prefix == next) {
				if (prefix == num[i]) {
					continue;
				} else {
					return num[i]>prefix?prefix:num[i];
				}
			}else if (prefix <= num[i]&&num[i]<= next) {
				break;
			}else if (prefix >= num[i]&&num[i]>= next) {
				isUpper = false;
				break;
			}
		}
		if (isUpper) {
			if (num[0] < num[num.length-1]) {
				return num[0];
			}
		}else {
			if (num[0] > num[num.length-1]) {
				return num[num.length-1];
			}
		}
		int firstIndex = 0;
		int lastIndex = num.length - 1;
		int compareIndex = num.length/2;
		while (lastIndex-firstIndex>1) {
			if (isUpper) {
				// 升序
				if (num[compareIndex] >= num[firstIndex]) {
					firstIndex = compareIndex;
				} else if (num[compareIndex] <= num[firstIndex]) {
					lastIndex = compareIndex;
				} 
			} else {
				if (num[compareIndex] >= num[firstIndex]) {
					lastIndex = compareIndex;
				} else if (num[compareIndex] <= num[firstIndex]) {
					firstIndex = compareIndex;
				} 
			}
			compareIndex = (firstIndex+lastIndex)/2;
		}
		if (lastIndex == firstIndex) {
			return num[lastIndex];
		}else {
			return num[lastIndex]<num[firstIndex]?num[lastIndex]:num[firstIndex];
		}
	}

}
