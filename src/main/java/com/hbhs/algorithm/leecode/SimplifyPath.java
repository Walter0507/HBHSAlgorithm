package com.hbhs.algorithm.leecode;

import java.util.Stack;

/**
 * <b>Simplify Path</b><br>
 * <bR>Given an absolute path for a file (Unix-style), simplify it.
 * <bR>
 * <bR>For example,
 * <bR>path = "/home/", => "/home"
 * <bR>path = "/a/./b/../../c/", => "/c"
 * <bR>
 * @author walter.xu
 *
 */
public class SimplifyPath {
	public static void main(String[] args) {
		System.out.println(solution("/a/b/c/../../////./../../../../d/./e/.././././././././"));
	}
	public static String solution(String path){
		if (path==null) {
			return "/";
		}
		if (!path.startsWith("/")) {
			path ="/"+path;
		}
		String[] array = path.split("/");
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("")) {
				continue;
			}
			if (array[i].equals(".")) {
				continue;
			}
			if (array[i].equals("..")) {
				if (!stack.isEmpty()) {
//					System.out.println("POP:  "+array[i]);
					stack.pop();
				}
			} else {
//				System.out.println("PUSH: "+array[i]);
				stack.push(array[i]);
			}
		}
		if (stack.isEmpty()) {
			return "/";
		}
		String result ="/"+stack.pop();
		while(!stack.isEmpty()){
			result = "/"+stack.pop()+result;
		}
		return result;
	}
}
