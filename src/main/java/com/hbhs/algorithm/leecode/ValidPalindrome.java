package com.hbhs.algorithm.leecode;

/**
 * <b>Valid Palindrome</b><br>
 * <br>Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <br>For example,
 * <br>"A man, a plan, a canal: Panama" is a palindrome.
 * <br>"race a car" is not a palindrome.
 * <br>
 * <br><B>DEFINE:</BR>
 * <br>亦称回环，是正读反读都能读通的句子，亦有将文字排列成圆圈者
 * <BR>
 * @author walter.xu
 *
 */
public class ValidPalindrome {
	public static void main(String[] args) {
		System.out.println((int)'a');
		System.out.println((int)'z');
		System.out.println((int)'A');
		System.out.println((int)'Z');
		System.out.println((int)'0');
		System.out.println((int)'9');
		System.out.println(solution("A man, a plan, a canal: Panama"));
		System.out.println(solution(".,"));
		System.out.println(solution("ab"));
	}
	public static boolean solution(String s){
		if (s==null||s.trim().length()<=1) {
			return true;
		}
		
		char[] charArray = s.toLowerCase().toCharArray();
		int startIndex = 0;
		int endIndex = charArray.length-1;
		while(true){
			while(!isNumberOrCharacter(charArray[startIndex])&&startIndex<endIndex){
				startIndex++;
			}
			while(!isNumberOrCharacter(charArray[endIndex])&&endIndex>startIndex){
				endIndex--;
			}
			
			if (charArray[startIndex] != charArray[endIndex]) {
				return false;
			}
			if (endIndex-startIndex <=1) {
				break;
			}
			startIndex++;
			endIndex--;
		}
		return true;
	}
	public static boolean isNumberOrCharacter(char c){
		if (c>=48&&c<=57) {
			return true;
		}
		if (c>=65&&c<=90) {
			return true;
		}
		if (c>=97&&c<=122) {
			return true;
		}
		return false;
	}
}
