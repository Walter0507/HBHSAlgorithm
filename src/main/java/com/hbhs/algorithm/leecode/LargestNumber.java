package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <b>Largest Number</b><bR>
 * <br>
 * <br>Given a list of non negative integers, arrange them such that they form the largest number.
 * <br>
 * <br>For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * <br>
 * <br>Note: The result may be very large, so you need to return a string instead of an integer.
 * <br>
 * @author walter.xu
 *
 */
public class LargestNumber {

	public static void main(String[] args) {
		System.out.println(((int)'0')+": "+(int)'9');
//		int[] num = new int[]{4312,7645,98,8323,7452,9659,569,4153,5222,2323,7189,9469,4907,7692,9354,7048,8080,3020,6921,6868,6764,3576,6107,7475,2066,9189,3992,983,3703,8152,7171,6628,9718,2004,4763,4294,2448,4920,2099,3932,7025,6764,3650,4581,2884,7130,3088,7126,7720,3062,970,7698,5825,8828,5746,9273,6341,9749,968,1629,6254,7840,4114,7518,7291,8554,545,6209,321,5754,4114,7038,6711,7231,8983,5997,7095,3448,9712,7735,2339,7501,1380,6625,6394,3563,2857,6665,7732,6778,2419,9820,2839,5798,4992,2321,3443,2919,2659,55};
		int[] num = new int[]{84,848,8489};
		long start = System.currentTimeMillis();
//		System.out.println(solution1(num));
		System.out.println(solution2(num));
		System.out.println("COST: "+(System.currentTimeMillis()-start));
		
	}
	
	
	
	public static String solution1(int[] num){
		if (num.length==0) {
			return "";
		}
		if (num.length==1) {
			return String.valueOf(num[0]);
		}
		int maxDigit = 0;
		for (int i = 0; i < num.length; i++) {
			int current = num[i];
			int currentLength = 0;
			while(current>0){
				current /= 10;
				currentLength++;
			}
			
			maxDigit = (currentLength>maxDigit?currentLength:maxDigit);
		}
		sortByDigit(num, 0, 0, num.length, maxDigit);
		StringBuilder str = new StringBuilder();
		for (int i = num.length-1; i >= 0; i--) {
			str.append(num[i]);
		}
		return str.toString();
	}
	
	private static void sortByDigit(int[] num, int digit, int startIndex, int endIndex,int maxDigit){
		if (digit>maxDigit) {
			return ;
		}
		int[] tempNum = new int[endIndex-startIndex];
		for (int i = 0; i < tempNum.length; i++) {
			tempNum[i] = num[startIndex+i];
		}
		int[] temp = new int[10];
		
		for (int i = 0; i < tempNum.length; i++) {
			int digitValue = getValueByDigit(num[i],digit);
			temp[digitValue] = temp[digitValue] + 1;
		}
		
		for (int i = 1; i < temp.length; i++) {
			temp[i] = temp[i] + temp[i-1]; 
		}
		System.out.println(Arrays.toString(temp));
		List<int[]> subSortList = new ArrayList<int[]>();
		for (int i = tempNum.length-1; i >= 0; i--) {
			int digitNum = getValueByDigit(tempNum[i], digit);
			num[startIndex+temp[digitNum]-1] = tempNum[i];
			
			if (digitNum>0) {
				int div = temp[digitNum]-temp[digitNum-1];
				if (div > 1) {
					subSortList.add(new int[]{startIndex+temp[digitNum]-div, startIndex+temp[digitNum]});
				}
			}
			temp[digitNum]--;
		}
		System.out.println(Arrays.toString(temp));
		
		
		System.out.println("Index:"+digit+", "+"["+startIndex+", "+endIndex+"]"+Arrays.toString(num));
		for (int[] sub: subSortList) {
			sortByDigit(num, digit+1, sub[0], sub[1],maxDigit);
		}
	}
	
	private static int getValueByDigit(int number,int digit){
		String numStr = String.valueOf(number);
		if(digit >= numStr.length()) {
			return numStr.charAt(numStr.length()-1)-48;
		}
		return numStr.charAt(digit)-48;
	}
	
	/**#######################################################3
	 * 
	 * #######################################################
	 */
	public static String solution2(int[] num){
		if (num==null||num.length==0) {
			return "0";
		}
		if (num.length==1) {
			return String.valueOf(num[0]);
		}
		String[] strArray = new String[num.length];
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = ""+num[i];
		}
		Arrays.sort(strArray, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return (o2+o1).compareTo(o1+o2);
			}
			
		});
		StringBuilder str = new StringBuilder();
		for(String s: strArray){
			str.append(s);
		}
		if (str.indexOf("0")==0) {
			return "0";
		}
		return str.toString();
	}
	
	
}
