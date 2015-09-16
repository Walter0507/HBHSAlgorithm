package com.hbhs.algorithm.leecode;

import java.math.BigInteger;
/**
 * <b>Single Number </b><br>
 * <br>Given an array of integers, every element appears three times except for one. Find that single one.
 * <br>
 * <br>Note:
 * <br>Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <br>
 * @author walter.xu
 *
 */
public class SingleNumberII {
	public static void main(String[] args) {
		int[] nums = new int[]{};
		System.out.println(solution1(nums));
		System.out.println(Integer.MIN_VALUE);

	}
	
	public static int solution1(int[] nums){
		int[] bitArray = new int[32];
		
		for(int i: nums){
			String binary = Integer.toBinaryString(i);
			System.out.println(binary);
			for (int j = binary.length()-1; j >= 0 ; j--) {
				bitArray[32-binary.length()+j] += binary.charAt(j)-'0';
			}
		}
		StringBuilder resultBinary = new StringBuilder();
		for (int i = 0; i < bitArray.length; i++) {
			resultBinary.append(bitArray[i]%3==0?"0":"1");
		}
		return new BigInteger(resultBinary.toString(), 2).intValue();
	}
}
