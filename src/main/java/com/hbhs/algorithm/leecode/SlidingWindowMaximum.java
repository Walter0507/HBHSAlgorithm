package com.hbhs.algorithm.leecode;

import java.util.Arrays;

/**
 * <b>Sliding Window Maximum</b>
 * <bR>Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <br>For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <br>Window position                Max
 * <br>---------------               -----
 * <Br>[1  3  -1] -3  5  3  6  7       3
 * <Br> 1 [3  -1  -3] 5  3  6  7       3
 * <Br> 1  3 [-1  -3  5] 3  6  7       5
 * <Br> 1  3  -1 [-3  5  3] 6  7       5
 * <Br> 1  3  -1  -3 [5  3  6] 7       6
 * <Br> 1  3  -1  -3  5 [3  6  7]      7
 * <Br>Therefore, return the max sliding window as [3,3,5,5,6,7].
 * <Br>
 * <br>Hint:
 * <br>How about using a data structure such as deque (double-ended queue)?
 * <br>The queue size need not be the same as the window’s size.
 * <br>Remove redundant elements and the queue should store only elements that need to be considered.
 * <br>
 * @ClassName: SlidingWindowMaximum 
 * @author Walter.xu
 * @date 2015年7月21日 上午9:51:32
 */
public class SlidingWindowMaximum {
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
		int k = 3;
		int[] result = null;
		
		result = maxSlidingWindow(nums, k);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k==1) {
			return nums;
		}
        int[] resultArray = new int[nums.length-k+1];
        int maxIndex = findMaxIndexFromArrayDiv(nums, 0, k);
        resultArray[0] = nums[maxIndex];
        for (int i = k; i < nums.length; i++) {
        	if (nums[i]>=nums[maxIndex]) {
        		maxIndex = i;
			}else{
				if (maxIndex == i-k) {
					maxIndex = findMaxIndexFromArrayDiv(nums, i-k+1, i+1);
				}
			}
        	resultArray[i-k+1] = nums[maxIndex];
        	System.out.println("current: "+i+", maxIndex: "+maxIndex);
		}
        return resultArray;
    }
	private static int findMaxIndexFromArrayDiv(int[] nums, int startIndex, int endIndex){
		int maxIndex = startIndex;
		endIndex = nums.length<endIndex?nums.length:endIndex;
		for (int i = startIndex; i < endIndex; i++) {
			if (nums[maxIndex] < nums[i]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}
