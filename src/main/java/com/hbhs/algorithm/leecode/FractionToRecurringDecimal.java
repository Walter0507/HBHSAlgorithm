package com.hbhs.algorithm.leecode;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Fraction to Recurring Decimal</b><br>
 * <br>
 * <br>Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <br>
 * <br>If the fractional part is repeating, enclose the repeating part in parentheses.
 * <br>
 * <br>For example,
 * <br>1. Given numerator = 1, denominator = 2, return "0.5".
 * <br>2. Given numerator = 2, denominator = 1, return "2".
 * <br>3. Given numerator = 2, denominator = 3, return "0.(6)".
 * <br>
 * @author walter.xu
 *
 */
public class FractionToRecurringDecimal {
	public static void main(String[] args) {
//		System.out.println(findMaxCommonDivisor(14, 21));
//		System.out.println(getLength(55));
//		System.out.println(canAliquot(6));
//		System.out.println(solution1(1, 99));
//		System.out.println(solution1(1, 4));
//		System.out.println(solution1(1, 333));
//		System.out.println(solution1(1, 6));
//		System.out.println(solution1(-50, 7));
//		System.out.println(solution1(-1, -2147483648));
//		System.out.println(solution1(1, 214748364));
		System.out.println(solution1(-2147483648, -1));
	}
	
	public static String solution1(int numerator, int denominator){
		return solution1((long)numerator, (long)denominator);
	}
	
	public static String solution1(long numerator, long denominator){
		if (numerator == 0) {
			return "0";
		}
		if (numerator == denominator) {
			return "1";
		}
		boolean isMultiPositive = ((numerator>0&&denominator>0)||(numerator<0&&denominator<0))?true:false;
		numerator = numerator>0?numerator:(0-numerator);
		denominator = denominator>0?denominator:(0-denominator);
		if (denominator==1||denominator==-1) {
			return (isMultiPositive?"":"-")+String.valueOf(numerator);
		}
		
		long commonDivisor = findMaxCommonDivisor(numerator, denominator);
		if (commonDivisor>1) {
			numerator = numerator/commonDivisor;
			denominator = denominator/commonDivisor;
		}
		// aliquot
		boolean canAliquot = canAliquot(denominator);
		
		long prefix = numerator/denominator;
		
		long tail = numerator%denominator;
		if (tail==0) {
			return String.valueOf(prefix);
		}

		String result = (isMultiPositive?"":"-")+prefix+".";
		StringBuilder str = new StringBuilder();
		Map<Long, Integer> positionMap = new HashMap<Long, Integer>();
		int index = 0;
		// 直到尾数为0
		while(tail!=0){
			// 如果是循环小数，并且该尾数已经出现过了，则直接返回
			if (!canAliquot&&positionMap.get(tail)!=null) {
				String orderedPart = str.substring(0,positionMap.get(tail));
				String cyclePart = str.substring(positionMap.get(tail));
				return result+orderedPart+"("+cyclePart+")";
			}
			// 否则添加字符串，并将该尾数加到index中
			
			positionMap.put(tail, index);
			index++;
			prefix = tail*10/denominator;
			tail = tail*10%denominator;
			str.append(prefix);
			
			
		}
		return result+str.toString();
	}
	public static long findMaxCommonDivisor(long i, long j){
		// Change to make sure i is bigger than j
		if (i < j) {
			i = i + j;
			j = i - j;
			i = i - j;
		}
		long tailDigit = 0;
		while(i%j!=0){
			tailDigit = i%j;
			i = j;
			j = tailDigit;
		}
		System.out.println("Max common divisor for ["+i+", "+j+"] is: "+j);
		return j;
	}
	
	public static boolean canAliquot(long value){
		while(value%2==0||value%5==0){
			if (value%2==0) {
				value = value/2;
			}
			if (value%5==0) {
				value = value/5;
			}
		}
		return value==1;
	}

}
