package com.hbhs.algorithm.leecode;

import java.util.Arrays;

/**
 * <b>Divide Two Integers</b><br>
 * <br>
 * <br>Divide two integers without using multiplication, division and mod operator.
 * <br>If it is overflow, return MAX_INT.
 * <br>
 * @author walter.xu
 *
 */
public class DivideTwoIntegers {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(moveSteps(2, 3)));
		System.out.println(Arrays.toString(moveSteps(5, 3)));
		System.out.println(Arrays.toString(moveSteps(1100540749, 1090366779)));
		System.out.println(solution(-1100540749, -1090366779));
	}
	
	public static int solution(int dividend, int divisor){
		if (divisor == 0) {
			return Integer.MAX_VALUE;
		}
		if (dividend == Integer.MIN_VALUE&&divisor==-1) {
			return Integer.MAX_VALUE;
		}
		if (divisor==1) {
			return dividend;
		}
		if (divisor == -1) {
			return -dividend;
		}
		if (dividend == divisor) {
			return 1;
		}
		if (dividend + divisor == 0) {
			return -1;
		}
		if (divisor == Integer.MIN_VALUE) {
			return 0;
		}
		boolean isDiff = false;
		if ((dividend>0&&divisor<0)||(dividend<0&&divisor>0)) {
			isDiff = true;
		}
		int sum = 0;
		divisor = Math.abs(divisor);
		if (dividend == Integer.MIN_VALUE) {
			sum++;
			dividend += Math.abs(divisor);
		}
		dividend = Math.abs(dividend);
		
		while(dividend >= divisor){
			int[] result = moveSteps(dividend, divisor);
			dividend = dividend-result[0];
			sum += (1<<result[1]);
		}
		return isDiff?(-sum):sum;
	}
	private static int[] moveSteps(int int1, int int2){
		if (int2>=Integer.MAX_VALUE >>1||int1<int2) {
			return new int[]{int2,0};
		}
		int[] value = new int[]{0,0};
		int steps = 0;
		while(int1>(int2<<1) && int2 < (Integer.MAX_VALUE>>1)){
			
			int2 = int2<<1;
			steps++;
		}
		value[0] = int2;
		value[1] = steps;
		return value;
	}
}
