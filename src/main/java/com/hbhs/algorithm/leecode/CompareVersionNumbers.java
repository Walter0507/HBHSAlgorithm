package com.hbhs.algorithm.leecode;

import java.util.regex.Pattern;

/**
 * <B>Compare Version Numbers</B><BR>
 * <BR>
 * <BR>Compare two version numbers version1 and version2.
 * <BR>If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <BR>You may assume that the version strings are non-empty and contain only digits and the . character.
 * <BR>The . character does not represent a decimal point and is used to separate number sequences.
 * <BR>For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * <BR>Here is an example of version numbers ordering:
 * <BR>0.1 < 1.1 < 1.2 < 13.37
 * @author walter.xu
 *
 */
public class CompareVersionNumbers {
	public static void main(String[] args) {
		System.out.println(solution("0.1", "0.2"));
		System.out.println(solution("0.1.1", "0.2"));
		System.out.println(solution("0.1", "0.1.1"));
		System.out.println(solution("1", "0"));
		System.out.println(solution("01", "1"));
		System.out.println(solution("01.2", "1.10"));
		System.out.println(solution("1", "1.0"));
		System.out.println(solution("1.0001.00000.0000.000.00.0.0", "1.1.0000"));
	}
	
	public static int solution(String version1, String version2){
		String[] v1Array = version1.split("\\.");
		String[] v2Array = version2.split("\\.");
		for (int i = v1Array.length-1; i > 0; i--) {
			if (Pattern.matches("[0]{1,}", v1Array[i])) {
				v1Array[i] = "0";
			}
		}
		for (int i = v2Array.length-1; i > 0; i--) {
			if (Pattern.matches("[0]{1,}", v2Array[i])) {
				v2Array[i] = "0";
			}
		}
		int index = 0;
		while(index < v1Array.length&&index<v2Array.length){
			String c1 = v1Array[index];
			String c2 = v2Array[index];
			while(c1.length()>1&&c1.startsWith("0")){
				c1 = c1.substring(1);
			}
			while(c2.length()>1&&c2.startsWith("0")){
				c2 = c2.substring(1);
			}
			int c1Int = Integer.valueOf(c1);
			int c2Int = Integer.valueOf(c2);
			if (c1Int > c2Int) {
				return 1;
			}else if (c1Int < c2Int) {
				return -1;
			}
			index++;
		}
		if (index == v1Array.length && index == v2Array.length) {
			return 0;
		}else if (index == v1Array.length) {
			boolean isAllZero = true;
			for (int i = index; i < v2Array.length; i++) {
				if (!v2Array[i].equals("0")) {
					isAllZero = false;
					break;
				}
			}
			if (isAllZero) {
				return 0; 
			}
			return -1;
		}else {
			boolean isAllZero = true;
			for (int i = index; i < v1Array.length; i++) {
				if (!v1Array[i].equals("0")) {
					isAllZero = false;
					break;
				}
			}
			if (isAllZero) {
				return 0; 
			}
			return 1;
		}
	}
}
