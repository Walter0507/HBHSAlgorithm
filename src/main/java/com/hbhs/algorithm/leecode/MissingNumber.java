package com.hbhs.algorithm.leecode;
/**
 * <b>Missing Number </B>
 * <BR>Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <BR>
 * <BR>For example,
 * <BR>
 * <BR>Given nums = [0, 1, 3] return 2.
 * <BR>
 * <BR>Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * <BR>
 * <BR>
 * <BR>
 * @ClassName: MissingNumber 
 * @author Walter.xu
 * @date 2015年8月26日 下午2:13:54
 */
public class MissingNumber {
	public static void main(String[] args) {
		int[] nums = new int[]{0,1,2,3,5,6,7,8,9,10};
//		nums = new int[]{0,1,2};
		nums = new int[]{1};
		System.out.println(missingNumber2(nums));
	}
	
	public static int missingNumber2(int[] nums) {
		int result = nums.length;
		for (int i = 0; i < nums.length; i++) {
			result += i - nums[i];
		}
		return result;
	}
	public static int missingNumber(int[] nums) {
		if (nums[0]!=0) {
			return 0;
		}
		if (nums[nums.length-1]==nums.length-1) {
			return nums.length;
		}
        int start = 0;
        int end = nums.length;
        int middle = (start+end)/2;
        while(true){
        	if (end-start<=1) {
				if (nums[start]==start) {
					return start+1;
				}else{
					return start-1;
				}
			}
        	if (nums[middle]==middle) {
				start = middle;
			}else{
				end = middle;
			}
        	middle = (start+end)/2;
        }
    }
}
