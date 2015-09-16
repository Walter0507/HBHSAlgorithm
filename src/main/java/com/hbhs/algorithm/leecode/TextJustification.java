package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Text Justification</b><br>
 * <bR>Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * <bR>
 * <bR>You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * <bR>
 * <bR>Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <bR>
 * <bR>For the last line of text, it should be left justified and no extra space is inserted between words.
 * <bR>For example,words ["This", "is", "an", "example", "of", "text", "justification."] L: 16.
 * <bR>Return the formatted lines as:
 * <bR>"This    is    an",
 * <bR>"example  of text",
 * <bR>"justification.  "
 * <bR>
 * <bR>
 * @author walter.xu
 *
 */
public class TextJustification {
	public static void main(String[] args) {
		System.out.println(formatStr(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 0, 2, 15, 7));
		List<String> resultList = new ArrayList<String>();
		String[] words = new String[]{"This", "is", "an", "justification","example", "of", "text", "justification."};
		int L = 16;
		resultList = solution(words, L);
		for(String str: resultList){
			System.out.println(str);
		}
	}
	
	public static List<String> solution(String[] words, int L){
		List<String> result = new ArrayList<String>();
		int[] length = new int[words.length];
		for (int i = 0; i < length.length; i++) {
			length[i] = words[i].length();
		}
		int startIndex = 0;
		int endIndex = startIndex + 1;
		while(startIndex<words.length){
			// one space
			int currentCount = length[startIndex];
			endIndex = startIndex+1;
			while(endIndex<words.length){
				if (currentCount+length[endIndex]+1>L) {
					break;
				}
				
				currentCount = currentCount+length[endIndex]+1;
				endIndex++;
			}
			String str = formatStr(words, startIndex, endIndex, L, currentCount);
			result.add(str);
			startIndex = endIndex;
		}
		if (startIndex<words.length) {
			String str = formatStr(words, startIndex, words.length-1, L, 0);
			result.add(str);
		}
		return result;
	}
	private static String formatStr(String[] words, int start, int end, int L, int currentCount){
		StringBuilder str = new StringBuilder();
		end--;
		if (end == words.length-1) {
			for (int i = start; i <= end; i++) {
				str.append(words[i]).append(" ");
			}
			int size = L-currentCount;
			while(size>0){
				str.append(" ");
				size--;
			}
			return str.substring(0, str.length()-1);
		}else{
			int wordSpaceCount = end-start;
			if(wordSpaceCount == 0){
				str.append(words[start]);
				int size = L -currentCount;
				while(size>0){
					str.append(" ");
					size--;
				}
				return str.toString();
			}
			int wordSpaceEvery = (L-currentCount)/wordSpaceCount;
			int wordSpaceLeft = (L-currentCount)%wordSpaceCount;
			System.out.println(wordSpaceCount+": "+wordSpaceEvery+": "+wordSpaceLeft);
			String space = "";
			while(wordSpaceEvery>0){
				space += " ";
				wordSpaceEvery--;
			}
			for (int i = start; i <= end; i++) {
				str.append(words[i]);
				if (i!=end) {
					str.append(" ").append(space);
				}
				if (wordSpaceLeft-->0) {
					str.append(" ");
				}
			}
		}
		return str.substring(0, str.length());
	}
}
