package com.hbhs.algorithm.leecode;

/**
 * <b>ZigZag Conversion </b><br>
 * <bR>
 * <bR>The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * <bR>P   A   H   N
 * <bR>A P L S I I G
 * <bR>Y   I   R
 * <bR>
 * <br>And then read line by line: "PAHNAPLSIIGYIR"
 * <br>
 * <br>Write the code that will take a string and make this conversion given a number of rows:
 * <br>string convert(string text, int nRows);
 * <br>convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * <br>
 * @author walter.xu
 *
 */
public class ZigZagConversion {
	public static void main(String[] args) {
		System.out.println(solution("123456789abc", 2));
	}
	/**
	 * 1   7   d
	 * 2  68  ce
	 * 3 5 9 b f .
	 * 4   a   g
	 * @param s
	 * @param nRows
	 * @return
	 */
	public static String solution(String s, int nRows){
		if (s.length()<nRows||nRows==1) {
			return s;
		}
		StringBuilder str = new StringBuilder();
		int rowMax = 2*(nRows-1);
		for (int i = 0; i < nRows; i++) {
			int column = 0;
			while((column*rowMax+i)<s.length()){
				str.append(s.charAt(column*rowMax+i));
				if (i>0&&i<nRows-1) {
					if ((column+1)*rowMax-i<s.length()) {
						str.append(s.charAt((column+1)*rowMax-i));
					}
				}
				
				column++;
			}
		}
		return str.toString();
	}
}
