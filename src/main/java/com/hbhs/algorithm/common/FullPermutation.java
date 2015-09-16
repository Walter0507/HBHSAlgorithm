package com.hbhs.algorithm.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列问题，将一个字符串全排列
 * @author walter.xu
 *
 */
public class FullPermutation {
	/**
	 * 排列指定对应的组合，各组合不会重复
	 * @param args 
	 * @return
	 */
	public static <T> List<List<T>> permutation(List<T> args){
		return permutation(args, false);
	}
	/**
	 * 排列指定对应的组合，依据指定参数表明结果集合是否重复
	 * @param args
	 * @param repeatable 结果集合中参数是否重复
	 * @return
	 */
	public static <T> List<List<T>> permutation(List<T> args, boolean repeatable){
		return permutation(args, args.size(), repeatable);
	}
	/**
	 * 排列指定对应的组合，依据指定参数表明结果集合是否重复
	 * @param args
	 * @param totalSize 选取参数个数，当该值大于排列数组大小或者小于0时，默认为args.size(),
	 * @param repeatable 结果集合中参数是否重复
	 * @return
	 */
	public static <T> List<List<T>> permutation(List<T> args, int totalSize, boolean repeatable){
		if (totalSize<=0||totalSize>args.size()) {
			totalSize = args.size();
		}
		List<List<T>> resultList = new ArrayList<List<T>>();
		List<T> secondPart = new ArrayList<T>();
		permutation(args, secondPart, resultList, totalSize, repeatable);
		return resultList;
	}
	/**
	 * 排列指定对应的组合，依据指定参数表明结果集合是否重复
	 * @param firstPart
	 * @param secondPart
	 * @param resultList
	 * @param totalSize
	 * @param repeatable 是否重复
	 */
	private static <T> void permutation(List<T> firstPart, List<T> secondPart, List<List<T>> resultList,
			int totalSize, boolean repeatable){
		// 当已经选取的集合达到要求的集合后，则返回
		if (totalSize == secondPart.size()) {
			resultList.add(secondPart);
			return ;
		}
		// 迭代
		for (int i = 0; i < firstPart.size(); i++) {
			List<T> currentFirstPart = new ArrayList<T>(firstPart);
			List<T> currentSecondPart = new ArrayList<T>(secondPart);
			currentSecondPart.add(firstPart.get(i));
			// 如果需要筛选重复的选择，则需要在源数据中删除已经选中的项
			if (!repeatable) {
				currentFirstPart.remove(i);
			}
			permutation(currentFirstPart, currentSecondPart, resultList, totalSize, repeatable);
		}
	}
}
