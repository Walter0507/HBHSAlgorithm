package com.hbhs.algorithm.dataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 后缀树
 * @author walter.xu
 *
 */
public class SuffixTree {
//	private SuffixTreeNode root = new SuffixTreeNode();
}
class SuffixTreeNode{
	private String str;
	private char[] commonCharArray ; 
	private boolean isRoot;
	private List<SuffixTreeNode> subNodeList = new ArrayList<SuffixTreeNode>();
	protected SuffixTreeNode(){
		isRoot = true;
		str = "ROOT";
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public char[] getCommonCharArray() {
		return commonCharArray;
	}
	public void setCommonCharArray(char[] commonCharArray) {
		this.commonCharArray = commonCharArray;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public List<SuffixTreeNode> getSubNodeList() {
		return subNodeList;
	}
	public void setSubNodeList(List<SuffixTreeNode> subNodeList) {
		this.subNodeList = subNodeList;
	}
}