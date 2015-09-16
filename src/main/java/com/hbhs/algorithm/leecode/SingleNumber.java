package com.hbhs.algorithm.leecode;

import java.util.BitSet;

/**
 * <b>Single Number </b><br>
 * <br>Given an array of integers, every element appears twice except for one. Find that single one.
 * <br>
 * <br>Note:
 * <br>Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <br>
 * @author walter.xu
 *
 */
public class SingleNumber {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,4,3,2,1};
		System.out.println(solution1(nums));
	}
	
	public static int solution2(int[] nums){
		BitSet bs = new BitSet(1<<31-1);
		System.out.println(bs.size());
		System.out.println(1-Integer.MIN_VALUE);
		for (int i: nums) {
			bs.set(i-Integer.MIN_VALUE, !bs.get(i-Integer.MIN_VALUE));
		}
		return bs.nextSetBit(1);
	}
	
	public static int solution1(int[] nums){
		int sum = 0;
		for(int i: nums){
			sum ^= i;
		}
		return sum;
	}
}
