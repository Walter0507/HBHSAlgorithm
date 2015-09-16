package com.hbhs.algorithm.leecode;

/**
 * <b>Find Peak Element </b><br>
 * <br>A peak element is an element that is greater than its neighbors.
 * <br>
 * <br>Given an input array where <code>num[i] ≠ num[i+1]</code>, find a peak element and return its index.
 * <br>
 * <br>The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <br>
 * <br>You may imagine that <code>num[-1] = num[n] = -∞</code>.
 * <br>
 * <br>For example, in array <code>[1, 2, 3, 1]</code>, <code>3</code> is a peak element and your function should return the index number <code>2</code>.
 * @author walter.xu
 *
 */
public class FindPeakElement {
	public static void main(String[] args) {
		int[] num = new int[]{1,2,3,1};
		System.out.println(solution1(num));
	}
	
	public static int solution1(int[] num){
		if (num.length==0) {
			return -1;
		}
		if (num.length==1) {
			return num[0];
		}
		if (num.length==2) {
			return num[1]>num[0]?1:0;
		}
		if (num[0]>num[1]) {
			return 0;
		}
		if (num[num.length-1]>num[num.length-2]) {
			return num.length-1;
		}
		return 0;
	}
}
