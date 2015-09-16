package com.hbhs.algorithm.leecode;
/**
 * <B>Excel Sheet Column Title</B><BR>
 * <BR>
 * <BR>Given a positive integer, return its corresponding column title as appear in an Excel sheet.. For example:
 * <BR>1 -> A
 * <BR>2 -> B
 * <BR>3 -> C
 * <BR>...
 * <BR>26 -> Z
 * <BR>27 -> AA
 * <BR>28 -> AB 
 * <BR>
 * @author walter.xu
 *
 */
public class ExcelSheetColumnTitle {
	public static void main(String[] args) {
		System.out.println(solution(18278));
	}
	public static String solution(int n){
		String result = "";
		int prefix = n/26;
		int tail = n%26;
		if (tail == 0) {
			tail = 26;
			prefix = prefix - 1;
		}
		result = (char)(tail+64)+result;
		while(prefix>0){
			tail = prefix%26;
			if (tail == 0) {
				tail = 26;
				prefix = prefix - 1;
			}
			prefix = prefix/26;
			
			result = (char)(tail+64)+result;
		}
		return result;
	}
}
