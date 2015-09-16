package com.hbhs.algorithm.leecode;
/**
 * <b>Maximum Subarray</b><br>
 * <br>Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <br>
 * <br>For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * <br>the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * <br>
 * <br>
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class MaximumSubarray {
	public static void main(String[] args) {
		
		System.out.println(solution1(new int[]{-2,1}));
//		System.out.println(solution2(new int[]{-2,1}));
		System.out.println(solution1(new int[]{-2,1,-3,4,-1,3}));
//		System.out.println(solution2(new int[]{-2,1,-3,4,-1,3}));
	}
	public static int solution1(int[] A){
		int max = A[0];
		int tempSum = A[0];
		for(int i = 1; i < A.length; i++) {
			if(tempSum  < 0) {
				tempSum = A[i];
		    } else {
		    	tempSum += A[i];
		    }
			max = max >= tempSum ? max : tempSum;
		}
		return max;
	}
	@Deprecated
	public static int solution2(int[] A){
		return divideAndFind(A, 0, A.length-1);
	}
	public static int divideAndFind(int[] A, int start, int end){
		int minIndex = findMinIndex(A, start, end);
		if (minIndex<0) {
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += A[i];
			}
			return sum;
		}
		if (minIndex == start) {
			return divideAndFind(A, start+1, end);
		}else if (minIndex == end) {
			return divideAndFind(A, start, end-1);
		}else{
			int left = divideAndFind(A, start, minIndex-1);
			int right = divideAndFind(A, minIndex+1, end);
			
			return left>right?left:right;
		}
	}
	
	public static int findMinIndex(int[] A, int start, int end){
		int index = -1;
		for (int i = start; i <= end; i++) {
			if (A[i] < 0) {
				if (index < 0 || A[index] > A[i]) {
					index = i;
				}
			}
		}
		return index;
	}
}
