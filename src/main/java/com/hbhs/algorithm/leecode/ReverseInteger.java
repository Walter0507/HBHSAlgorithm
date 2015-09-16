package com.hbhs.algorithm.leecode;

/**
 * <b>Reverse Integer </b><br>
 * <br>Reverse digits of an integer.
 * <br>
 * <br>Example1: x = 123, return 321
 * <br>Example2: x = -123, return -321
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class ReverseInteger {

	public static void main(String[] args) {

//		System.out.println(solution(123456789));
//		System.out.println(solution(10000000));
		System.out.println(solution(-10000000));
		System.out.println(solution(-1234));
	}
	public static int solution(int x){
		
		boolean isBigThanZero = x>=0? true: false;
		x = Math.abs(x);
		int maxLength = 0;
		int value = x;
		while(value>0){
			value = value/10;
			maxLength++;
		}
		int[] args = new int[maxLength];
		value = x;
		int index = 0;
		while (value>0) {
			args[index] = value%10;
			value = value/10;
			index++;
		}
		double result = 0;
		for (int i = 0; i < args.length; i++) {
			double temp = args[i];
			int times = i+1;
			while (times<maxLength) {
				temp *=10;
				times++;
			}
			result+=temp;
		}
		if (result>Integer.MAX_VALUE||result < Integer.MIN_VALUE) {
			return 0;
		}
		return isBigThanZero?(int)result:-(int)result;
	}
}
