package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Substring with Concatenation of All Words</b><br>
 * <br>
 * <br>You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
 * <br>
 * <br>For example, given:
 * <br>S: "barfoothefoobarman"
 * <br>L: ["foo", "bar"]
 * <br>You should return the indices: [0,9].
 * <br>(order does not matter).
 * @author walter.xu
 *
 */
public class SubstringWithConcatenationOfAllWords {
	public static void main(String[] args) {
		List<Integer> result = null;
		String S = "barfoothefoobarman";
		String[] L = new String[]{"foo", "bar"};
		result = solution(S, L);
		System.out.println(Arrays.toString(result.toArray()));
	}
	
	public static List<Integer> solution(String S, String[] L){
		if (L==null||L.length == 0) {
			return null;
		}
		List<Integer> result = new ArrayList<Integer>();
	    int size = L[0].length();
	    if (L.length == 0 || L[0].isEmpty() || L[0].length() > S.length()) 
	        return result;
	    Map<String, Integer> hist = new HashMap<String,Integer>();
	    for (String w : L) {
	        hist.put(w, !hist.containsKey(w) ? 1 : hist.get(w)+1);
	    }
	    for (int i = 0; i+size*L.length <= S.length(); i++) {
	        if (hist.containsKey(S.substring(i, i+size))) {
	            Map<String, Integer> currHist = new HashMap<String,Integer>();
	            for (int j = 0; j < L.length; j++) {
	                String word = S.substring(i+j*size, i+(j+1)*size);
	                currHist.put(word, !currHist.containsKey(word) ? 
	                        1 : currHist.get(word)+1);
	            }
	            if (currHist.equals(hist)) result.add(i);
	        }
	    }
	    return result;
	}
}
