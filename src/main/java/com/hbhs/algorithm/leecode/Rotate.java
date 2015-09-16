package com.hbhs.algorithm.leecode;

import java.util.Arrays;

public class Rotate {
	public static void main(String[] args) {
		int[] a = new int[]{1,2,3,4,5,6,7};
		rotate_2(a, 3);
		System.out.println(Arrays.toString(a));
	}
	
	public static void rotate_1(int[] nums, int k){
		int length = nums.length;
        if(length == 0){
            return ;
        }
        k = k % length;
        if(k == 0){
            return ;
        }
        int[] temArray = new int[k];
        for(int i = 0; i< k; i++){
            temArray[i] = nums[length - k + i];
        }
        for(int i = length - k-1; i>=0; i--){
            nums[i+k] = nums[i];
        }
        for(int i = 0; i< k; i++){
            nums[i] = temArray[i];
        }
	}
	
	public static void rotate_2(int[] nums, int k){
		int length = nums.length;
        if(length == 0){
            return ;
        }
        k = k % length;
        if(k == 0){
            return ;
        }
        int[] temArray = new int[length];
        for(int i = 0; i < k; i++){
            temArray[i] = nums[length - k +i];
        }
        for(int i = k; i < length; i++){
            temArray[i] = nums[i-k];
        }
//        nums = temArray;
        for (int i = 0; i < temArray.length; i++) {
        	nums[i] = temArray[i];
		}
	}
}
