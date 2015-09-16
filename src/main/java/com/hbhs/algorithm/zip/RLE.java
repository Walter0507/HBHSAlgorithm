package com.hbhs.algorithm.zip;
/**
 * 
 * @author walter.xu
 *
 */
public class RLE {
	public static void main(String[] args) {
		String resource = "111122222333333333555555544444346";
		System.out.println("Resource: "+resource);
		String encode = compress1(resource);
		System.out.println("encode  : "+encode);
		String decode = depress1(encode);
		System.out.println("decode  : "+decode);
	}
	public static String compress1(String str){
		StringBuilder result = new StringBuilder();
		int size = 0;
		char last = str.charAt(0);
		for (int i = 1; i < str.length(); i++) {
			size++;
			if (str.charAt(i)!=last) {
				result.append(size).append(last);
				last = str.charAt(i);
				size = 0;
			}
		}
		result.append(size).append(last);
		return result.toString();
	}
	
	public static String depress1(String encodeStr){
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < encodeStr.length(); i +=2) {
//			char number = encodeStr.charAt(i);
//			char c = encodeStr.charAt(i+1);
			
		}
		return result.toString();
	}
}
