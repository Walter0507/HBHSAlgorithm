package com.hbhs.algorithm.dataStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trie树结构
 * @author walter.xu
 *
 */
public class TrieTree {
	private TrieTreeNode root = new TrieTreeNode('*', true);
	public TrieTree(){
	}
	public void addString(String str){
		root.addString(str);
	}
	public void addCharArray(char[] charArray){
		root.addCharArray(charArray);
	}
	public void addStrings(List<String> strs){
		if (strs==null) {
			return ;
		}
		for(String str: strs){
			root.addString(str);
		}
	}
	public boolean matchString(String str){
		return root.macthString(str);
	}
	public boolean matchCharArray(char[] charArray){
		return root.macthCharArray(charArray);
	}
}
/**
 * TRIE 树节点
 * @author walter.xu
 *
 */
class TrieTreeNode{
	private char value;
	private boolean isRoot = false;
	private Map<Character, TrieTreeNode> subNodeMap = new HashMap<Character, TrieTreeNode>();
	
	protected TrieTreeNode(char value, boolean isRoot){
		this.value = value;
		this.isRoot = isRoot;
	}
	/**
	 * 
	 * @param str
	 */
	public void addString(String str){
		if (str==null||str.length()==0) {
			return ;
		}
		addCharArray(str.toCharArray());
	}
	public boolean macthString(String str){
		if (str==null||str.length()==0) {
			return true;
		}
		return macthCharArray(str.toCharArray());
	}
	/**
	 * 
	 * @param charArray
	 */
	public void addCharArray(char[] charArray){
		addCharArray(charArray, 0);
	}
	public boolean macthCharArray(char[] charArray){
		return macthCharArray(charArray, 0);
	}
	/**
	 * 
	 * @param charArray
	 * @param index
	 */
	private void addCharArray(char[] charArray, int index){
		if (charArray==null||charArray.length==0||index>=charArray.length) {
			return ;
		}
		addChar(charArray[index]);
		getSubNodeMap().get(charArray[index]).addCharArray(charArray, index+1);;
	}
	private boolean macthCharArray(char[] charArray, int index){
		if (charArray==null||charArray.length==0||index>=charArray.length) {
			return true;
		}
		TrieTreeNode node = subNodeMap.get(charArray[index]);
		if (node==null) {
			return false;
		}
		return node.macthCharArray(charArray, index+1);
	}
	/**
	 * 
	 * @param c
	 */
	public void addChar(char c){
		TrieTreeNode subNode = subNodeMap.get(c);
		if (subNode==null) {
			subNodeMap.put(c, new TrieTreeNode(c));
		}
	}
	
	public TrieTreeNode(char value){
		this(value, false);
	}
	
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public Map<Character, TrieTreeNode> getSubNodeMap() {
		return subNodeMap;
	}
	public void setSubNodeMap(Map<Character, TrieTreeNode> subNodeMap) {
		this.subNodeMap = subNodeMap;
	}
	
}