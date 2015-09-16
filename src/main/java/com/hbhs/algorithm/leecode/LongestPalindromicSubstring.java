package com.hbhs.algorithm.leecode;

/**
 * <b>Longest Palindromic Substring</b><BR>
 * <BR>
 * <BR>Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * <BR>
 * @author walter.xu
 *
 */
public class LongestPalindromicSubstring {
	public static void main(String[] args) {
		System.out.println(solution("aaaaaaaaaaaaaa"));
		System.out.println(solution("ababababababad"));
		System.out.println(solution("abcddcbaa"));
		System.out.println(solution("abcdcbcdcbd"));
	}
	
	public static String solution(String s){
		int currentIndex=0;
		int maxLength = 1;
		int maxStart = 0;
		
		int startIndex = currentIndex-1;
		int endIndex = currentIndex+1;
//		int currentMoveSteps = 1;
		while (currentIndex < s.length()) {
			if (endIndex<s.length()&&(startIndex+1)==currentIndex&&s.charAt(currentIndex) == s.charAt(endIndex)) {
//				currentMoveSteps++;
				endIndex++;
				continue;
			}

			//||s.charAt(startIndex)!=s.charAt(endIndex)
			if (endIndex >= s.length() || startIndex < 0) {
				
				if ((endIndex -startIndex-1) > maxLength) {
					maxStart = startIndex+1;
					maxLength = endIndex- startIndex - 1;
				}
				currentIndex ++;
				startIndex = currentIndex-1;
				endIndex = currentIndex+1;
				continue;
			}
			if (s.charAt(startIndex)==s.charAt(endIndex)) {
				startIndex--;
				endIndex++;
			} else {
				if ((endIndex -startIndex-1) > maxLength) {
					maxStart = startIndex+1;
					maxLength = endIndex- startIndex - 1;
				}
				currentIndex ++;
				startIndex = currentIndex-1;
				endIndex = currentIndex+1;
			}
		}
		return s.substring(maxStart, maxStart+maxLength);
	}
}
