package com.hbhs.algorithm.leecode;

/**
 * <b>Search in Rotated Sorted Array</b><br>
 * <br>Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <br>
 * <br>(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <br>You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <br>
 * <br>You may assume no duplicate exists in the array.
 * <br>
 * @author walter.xu
 *
 */
public class SearchInRotatedSortedArray {
	public static void main(String[] args) {
		
	}
	
	public static int solution(int[] A, int target){
		if (A==null||A.length==0) {
			return -1;
		}
		if (A.length<3) {
			for (int i = 0; i < A.length; i++) {
				if (A[i] == target) {
					return i;
				}
			}
			return -1;
		}
		boolean isAsc = true;
		if (A[0]<A[1]) {
			if (A[2]>A[0]&&A[2]<A[1]) {
				isAsc = false;
			}
		}else{
			isAsc = false;
			if (A[2]>A[1]&&A[2]<A[0]) {
				isAsc = true;
			}
		}
		System.out.println("ISASC: "+isAsc);
		
		return 0;
	}
	
}
