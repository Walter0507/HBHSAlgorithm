package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <b>Generate Parentheses </b><br>
 * <br>
 * <br>Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <br>
 * <br>For example, given n = 3, a solution set is:
 * <br>"((()))", "(()())", "(())()", "()(())", "()()()"
 * <br>
 * <br>
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class GenerateParentheses {
	public static void main(String[] args) {
		List<String> result = solution(2);
		result = solution(3);
		System.out.println(Arrays.toString(result.toArray()));
	}
	
	public static List<String> solution(int n){
		if (n==0) {
			return new ArrayList<String>();
		}
		if (n==1) {
			List<String> temp = new ArrayList<String>();
			temp.add("()");
			return temp;
		}
		List<String> result = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		tempList.add("(");
		push(n-1, n, tempList, result);
		pop(n-1, n, tempList, result);
		return result;
	}
	
	private static void push(int pushCount,int popCount, List<String> prefixList, List<String> result){

		if (pushCount==0) {
			return ;
		}
		List<String> currentList =new ArrayList<String>(prefixList);
		for (int j = 0; j < currentList.size(); j++) {
			currentList.set(j, currentList.get(j)+"(");
		}
		push(pushCount-1, popCount, currentList, result);
		pop(pushCount-1, popCount, currentList, result);
		
	}
	private static void pop(int pushCount,int popCount, List<String> prefixList, List<String> result){
		if (popCount==0) {
			result.addAll(prefixList);
			return ;
		}
		if (popCount==pushCount ) {
			return ;
		}
		List<String> currentList =new ArrayList<String>(prefixList);
		for (int j = 0; j < currentList.size(); j++) {
			currentList.set(j, currentList.get(j)+")");
		}
		push(pushCount, popCount-1, currentList, result);
		pop(pushCount, popCount-1, currentList, result);
	}
}
