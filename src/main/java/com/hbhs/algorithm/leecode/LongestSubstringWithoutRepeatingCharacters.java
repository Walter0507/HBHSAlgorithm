package com.hbhs.algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>Longest Substring Without Repeating Characters </b><br>
 * <br>
 * <br>Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 * <br>
 * @author walter.xu
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
//		System.out.println(solution1("abcabcdefaaa"));
		System.out.println(solution1("aaaa"));
	}
	
	public static int solution1(String s){
		if (s==null||s.equals("")) {
			return 0;
		}
		
		Map<Character, Integer> charIndexMap = new HashMap<Character, Integer>();
		charIndexMap.put(s.charAt(0), 0);
		int startIndex = 0;
		int currentIndex = 1;
		int maxLength = currentIndex - startIndex;
		while(currentIndex<s.length()){
			Integer indexCurrentChar = charIndexMap.get(s.charAt(currentIndex));
			if (indexCurrentChar != null&&indexCurrentChar >= startIndex) {
				if (maxLength < currentIndex - startIndex) {
					maxLength = currentIndex - startIndex;
				}
				startIndex = indexCurrentChar+1;
			}
			charIndexMap.put(s.charAt(currentIndex), currentIndex);
			currentIndex++;
		}
		if (maxLength < currentIndex - startIndex) {
			maxLength = currentIndex - startIndex;
		}
		return maxLength;
	}
}
