package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>Word Break II </<b><br>
 * <br>
 * <br>
 * Given a string s and a dictionary of words dict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word. <br>
 * Return all such possible sentences. <br>
 * For example, given <br>
 * s = "catsanddog", <br>
 * dict = ["cat", "cats", "and", "sand", "dog"]. <br>
 * <br>
 * A solution is ["cats and dog", "cat sand dog"]. <br>
 * 
 * @author walter.xu
 *
 */
public class WordBreakII {
	public static void main(String[] args) {
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("a");
		wordDict.add("aa");
		wordDict.add("aaa");
		wordDict.add("aaaa");
//		wordDict.add("dog");
		solution1("aaaaa", wordDict);
	}

	public static List<String> solution1(String s, Set<String> wordDict) {
		// 保留字节是否可以拆分
		Map<Integer, List<Integer>> headPrefix = new HashMap<Integer, List<Integer>>();
		BitSet bs = new BitSet(s.length());
		bs.set(0);
		int index = 1;
		while (index <= s.length()) {
			boolean matched = false;
			for (String target : wordDict) {
				if (index - target.length() < 0) {
					continue;
				}
				if (target
						.equals(s.subSequence(index - target.length(), index))
						&& bs.get(index - target.length())) {
					matched = true;
					if (headPrefix.get(index)==null) {
						headPrefix.put(index, new ArrayList<Integer>());
					}
					headPrefix.get(index).add(index-target.length());
				}
			}
			if (matched) {
				bs.set(index);
			}
			index++;
		}
		List<String> resultList = new ArrayList<String>();
		addAll(resultList, s, headPrefix, s.length(), "");
		if (!bs.get(s.length())) {
			return resultList;
		}
		for(String res: resultList){
			System.out.println(res);
		}
		return resultList;
	}
	
	private static void addAll(List<String> resultList, String s, Map<Integer, List<Integer>> mapList, int index, String tail){
		if (index==0) {
			resultList.add(tail.trim());
			return ;
		}
		List<Integer> indexList = mapList.get(index);
		for(Integer start: indexList){
			String tailNew = s.substring(start, index)+" "+tail;
			addAll(resultList, s, mapList, start, tailNew);
		}
	}
}
