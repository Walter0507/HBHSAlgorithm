package com.hbhs.algorithm.leecode;

/**
 * <B>Ugly Number</B>
 * <BR>
 * <BR>Write a program to check whether a given number is an ugly number.
 * <BR>
 * <BR>Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * <BR>
 * <BR>Note that 1 is typically treated as an ugly number.
 * <BR>
 * <BR>
 * 
 * @ClassName: UglyNumber 
 * @author Walter.xu
 * @date 2015年9月6日 下午3:57:05
 */
public class UglyNumber {

	
	public static void main(String[] args) {
		System.out.println(isUgly(10));
		System.out.println(isUgly(11));
		System.out.println(isUgly(15));
	}
	public static boolean isUgly(int num) {
		int left = num;
		int div = 0;
        while(true){
        	num = left;
        	div = left%2;
        	if (div == 0) {
				left = left /2;
			}
        	div = left%3;
        	if (div == 0) {
				left = left /3;
			}
        	div = left%5;
        	if (div == 0) {
				left = left /5;
			}
        	if (num ==left) {
				break;
			}
        }
        return left == 1;
    }
}
