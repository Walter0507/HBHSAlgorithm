package com.hbhs.algorithm.leecode;
/**
 * <b>Factorial Trailing Zeroes</b><bR>
 * <br>Given an integer n, return the number of trailing zeroes in n!.(对数时间内)
 * <br>求N!的尾随0个数
 * <br><b>Note</b>: Your solution should be in logarithmic time complexity.
 * <br>
 * <br>
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class TrailingZeros {
	public static void main(String[] args) {
		System.out.println(solution1(25));
	}
	
	public static int solution1(int n){	
		if (n<=0) {
			return 0;
		}
		int count = n / 5;
		return count + solution1(count);
	}

}
