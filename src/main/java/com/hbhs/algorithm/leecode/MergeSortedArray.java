package com.hbhs.algorithm.leecode;

import java.util.Arrays;

/**
 * <B>Merge Sorted Array </B><BR>
 * <BR>Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * <BR>
 * <BR>Note:
 * <BR>You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
 * <BR>
 * <BR>
 * <BR>
 * @author walter.xu
 *
 */
public class MergeSortedArray {
	public static void main(String[] args) {
//		int[] A = new int[]{1,3,5,9,0,0,0,0,0,0,0,0,0,0};
//		int[] B = new int[]{2,4,6,8}; 
		int[] A = new int[]{4,6,7,8,0,0,0,0,0,0,0,0,0};
		int[] B = new int[]{1,2,3,5};
		int m = 4;
		int n = 4;
		
		solution(A, m, B, n);
		System.out.println(Arrays.toString(A));
	}
	
	public static void solution(int A[], int m, int B[], int n){
		if (m==0) {
			for (int i = 0; i < n; i++) {
				A[i] = B[i];
			}
		}
		if (n == 0) {
			return ;
		}
		int aIndex = m-1;
		int bIndex = n-1;
		for (int i = m+n-1; i > 0; i--) {
			if (aIndex<0||bIndex<0) {
				break;
			}
			if (A[aIndex] > B[bIndex]) {
				A[i] = A[aIndex];
				aIndex--;
			}else{
				A[i] = B[bIndex];
				bIndex--;
			}
		}
		while(bIndex>=0){
			A[bIndex] = B[bIndex];
			bIndex--;
		}
		
	}
}
