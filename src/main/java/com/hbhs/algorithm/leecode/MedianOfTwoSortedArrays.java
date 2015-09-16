package com.hbhs.algorithm.leecode;

/**
 * <B>Median of Two Sorted Arrays</B><BR>
 * <BR>
 * <BR>There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <BR>
 * @author walter.xu
 *
 */
public class MedianOfTwoSortedArrays {
	public static void main(String[] args) {
		int[] a = new int[]{1};
//		System.out.println(solution(a));
//		a = new int[]{1,2};
//		System.out.println(solution(a));
		int[] b = new int[]{2};
		System.out.println(solution(a, b));
	}
	public static double solution(int[] A, int[] B){
		if (A.length==0&&B.length==0) {
			return A.length==0?solution(B):solution(A);
		}

		int AStartIndex = 0, AEndIndex = A.length>0?(A.length-1):0;
		int BstartIndex = 0, BEndIndex = B.length>0?(B.length-1):0;
		while(true){
			if (AStartIndex == AEndIndex && BstartIndex== BEndIndex) {
				return ((double)A[AStartIndex]+B[BstartIndex])/2;
			}
			if (AStartIndex > AEndIndex) {
				return solution(B, BstartIndex, BEndIndex);
			}
			if (BstartIndex > BEndIndex) {
				return solution(A, AStartIndex, AEndIndex);
			}
			
			if (A[AStartIndex] <= B[BstartIndex]) {
				AStartIndex++;
			}else{
				BstartIndex++;
			}
			
			if (A[AEndIndex] >= B[BEndIndex]) {
				AEndIndex--;
			}else{
				BEndIndex--;
			}
		}
	}
	public static double solution(int[] a){
		return solution(a,0,a.length-1);
	}
	public static double solution(int[] a, int startInde, int endIndex){
		int length = endIndex - startInde;
		if (length%2==0) {
			return (double)a[startInde+length/2];
		}else{
			return ((double)(a[startInde+(length-1)/2]+a[startInde+(length+1)/2]))/2;
		}
	}
}
