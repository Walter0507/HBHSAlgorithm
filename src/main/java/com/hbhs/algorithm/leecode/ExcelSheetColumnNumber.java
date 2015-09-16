package com.hbhs.algorithm.leecode;
/**
 * <B>Excel Sheet Column Number</B><BR>
 * <BR>
 * <BR>Given a column title as appear in an Excel sheet, return its corresponding column number. For example:
 * <BR>A -> 1
 * <BR>B -> 2
 * <BR>C -> 3
 * <BR>...
 * <BR>Z -> 26
 * <BR>AA -> 27
 * <BR>AB -> 28 
 * <BR>
 * @author walter.xu
 *
 */
public class ExcelSheetColumnNumber {

	public static void main(String[] args) {
		System.out.println(solution("ZZZ"));
//		System.out.println((int)'A');
	}
	
	public static int solution(String s){
		if (s==null||s.trim().length()==0) {
			return 0;
		}
		s = s.toUpperCase();
		char[] charArray = s.toCharArray();
		int total = 0;
		for (int i = 0; i < charArray.length; i++) {
			int charValue = charArray[i];
			int index = charArray.length -1 - i;
			int subTotal = 1;
			while(index > 0){
				subTotal *= 26;
				index -- ;
			}
			total += subTotal*(charValue-64);
		}
		return total;
	}
}
