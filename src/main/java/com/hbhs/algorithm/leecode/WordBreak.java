package com.hbhs.algorithm.leecode;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <b>Word Break</br><br>
 * <br>
 * <br>Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <br>
 * <br>For example, given
 * <br>s = "leetcode",
 * <br>dict = ["leet", "code"].
 * <br>
 * <br>Return true because "leetcode" can be segmented as "leet code"
 * <br>
 * @author walter.xu
 *
 */
public class WordBreak {
	public static void main(String[] args) {
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("a");
		wordDict.add("b");
		wordDict.add("aa");
		wordDict.add("aaa");
		wordDict.add("aaaa");
		wordDict.add("aaaaa");
		wordDict.add("aaaaaa");
		wordDict.add("aaaaaaa");
		wordDict.add("aaaaaaaa");
		wordDict.add("aaaaaaaaa");
		wordDict.add("aaaaaaaaaa");
		
		boolean isSuccess = solution2("a", wordDict);
		System.out.println(isSuccess);
	}
	/**
	 * 动态规划<BR>
	 * 如果F(n)是可拆分的，那么如果F(n) = F(n-1) +x(i)，则F(n-1)肯定可拆分。
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static boolean solution2(String s, Set<String> wordDict){
		// 保留字节是否可以拆分
		BitSet bs = new BitSet(s.length());
		bs.set(0);
		int index = 1;
		while(index <= s.length()){
			boolean matched = false;
			for(String target: wordDict){
				if (index - target.length() < 0) {
					continue ;
				}
				if (target.equals(s.subSequence(index-target.length(), index))&&bs.get(index-target.length())) {
					matched = true;
					break;
				}
			}
			if (matched) {
				bs.set(index);
			}
			index++;
		}
		System.out.println(bs);
		return bs.get(s.length());
	}
	
	
	
	public static boolean solution1(String s, Set<String> wordDict){
		TrieTreeNode root = new TrieTreeNode('#', null);
		for(String word: wordDict){
			root.addString(word, root);
		}
		
		return root.matchStr(s, 0);
	}
	
	private static class TrieTreeNode{
		private char currentChar;
		private String str = "";
		private boolean isKey;
		private TrieTreeNode root = null;
		private Map<Character, TrieTreeNode> subNodeMap = new HashMap<Character, WordBreak.TrieTreeNode>();
		
		public TrieTreeNode(char c, TrieTreeNode parent){
			currentChar = c;
			if (parent!=null) {
				this.str = parent.getStr()+currentChar;
			}
		}
		
		public boolean matchStr(String s, int index){
			if (index == s.length()) {
				return isKey;
			}
			char target = s.charAt(index);
			
			boolean isExist = false;
			if (subNodeMap.get(target)!=null) {
				isExist = this.subNodeMap.get(target).matchStr(s, index+1);
			}
			if (!isExist&&root!=null) {
				isExist = this.root.matchStr(s, index);
			}
			return isExist;
		}
		
		
		public void addString(String str, TrieTreeNode root){
			if (str==null||str.equals("")) {
				return ;
			}
			char[] charArray = str.toCharArray();
			addCharArray(charArray, 0, root);;
		}
		
		private void addCharArray(char[] array, int index, TrieTreeNode root){
			if (index==array.length) {
				this.isKey = true;
				this.root = root;
				return ;
			}
			if (subNodeMap.get(array[index])==null) {
				TrieTreeNode node = new TrieTreeNode(array[index], this);
				subNodeMap.put(array[index], node);
			}
			subNodeMap.get(array[index]).addCharArray(array, index+1, root);
		}
		
		public String getStr() {
			return str;
		}
	}
}
