package com.hbhs.algorithm.leecode;

import java.util.Arrays;

/**
 * <B>H-Index</B>
 * <BR>Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * <BR>
 * <BR>According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 * <BR>
 * <BR>
 * <BR>For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. 
 * <BR>
 * <BR>Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 * <BR>
 * <BR>Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * <BR>
 * <BR>Hint:
 * <BR>
 * @ClassName: H_Index 
 * @author Walter.xu
 * @date 2015年9月6日 下午5:27:43
 */
public class H_Index {
	public static void main(String[] args) {
		int[] citations = new int[]{1,2,3,4,5,6,7,8,9,10};
		citations = new int[]{4,4,4,4,2,2,2,2};
		int result = hIndex(citations);
		
		System.out.println(result);
	}
	
	public static int hIndex(int[] citations) {
        if (citations==null||citations.length == 0) {
			return 0;
		}
        Arrays.sort(citations);
        for (int i = citations.length-1; i >=0; i--) {
			if (citations[i]<=citations.length-i) {
				return citations[i];
			}
		}
		return 0;
    }
}
