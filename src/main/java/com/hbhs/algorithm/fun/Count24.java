package com.hbhs.algorithm.fun;

import java.util.ArrayList;
import java.util.List;

import com.hbhs.algorithm.HBHSAssert;

/**
 * 计算21点游戏<BR>
 * 给定数字数组，通过+,-,*,/算法得出最终的24点
 * @author walter.xu
 *
 */
public class Count24 {
	
	public static void main(String[] args) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(7);
		result.add(7);
		result.add(3);
		result.add(4);
		int totalCount = 24;
		List<String> operatioins = point(result, totalCount, false);
		for(String str: operatioins){
			System.out.println(str);
		}
	}
	/**
	 * 算法思想<BR>
	 * 1 将所有的数值按照指定的规则生成所有的可排序的序列
	 * 2 针对每一个序列分别计算+,-,*,/操作，并计算最终结果，如果成功记记录下当前的值
	 * @param patternArray
	 * @param result
	 * @return
	 */
	public static List<String> point(List<Integer> patternList,int result, boolean easyOperation){
		
		HBHSAssert.isTrue(patternList==null || patternList.size() < 2, "Pattern must be more two");
		List<String> resultList = new ArrayList<String>();
		
		List<String> operateArray = new ArrayList<String>();
		operateArray.add("+");
		operateArray.add("-");
		operateArray.add("*");
		operateArray.add("/");
		if (!easyOperation) {
			operateArray.add(">>");
			operateArray.add("<<");
			operateArray.add("^");
		}
		
		List<List<String>> operationList = Permutation.permutation(operateArray, patternList.size()-1, true);
		List<List<Integer>> intList = Permutation.permutation(patternList);
		for (int i = 0; i < operationList.size(); i++) {
			List<String> currentOperationList = operationList.get(i);
			for(List<Integer> currentIntGroup: intList){
				int currentCount = currentIntGroup.get(0);
				int j = 1;
			
				for (; j < currentIntGroup.size(); j++) {
					if ("+".equalsIgnoreCase(currentOperationList.get(j-1))) {
						if (currentIntGroup.get(j) < currentIntGroup.get(j-1)) {
							break;
						}
						currentCount += currentIntGroup.get(j);
					} else if ("-".equalsIgnoreCase(currentOperationList.get(j-1))) {
						if (currentIntGroup.get(j) < currentIntGroup.get(j-1)) {
							break;
						}
						currentCount -= currentIntGroup.get(j);
					} else if ("*".equalsIgnoreCase(currentOperationList.get(j-1))) {
						if (currentIntGroup.get(j) < currentIntGroup.get(j-1)) {
							break;
						}
						currentCount *= currentIntGroup.get(j);
					} else if ("/".equalsIgnoreCase(currentOperationList.get(j-1))) {
						if (currentIntGroup.get(j-1)%currentIntGroup.get(j)>0) {
							break;
						}
						currentCount /= currentIntGroup.get(j);
					} else if (">>".equalsIgnoreCase(currentOperationList.get(j-1))) {
						currentCount = currentCount >> currentIntGroup.get(j);
					} else if ("<<".equalsIgnoreCase(currentOperationList.get(j-1))) {
						currentCount = currentCount << currentIntGroup.get(j);
					} else if ("^".equalsIgnoreCase(currentOperationList.get(j-1))) {
						int times = currentIntGroup.get(j);
						int temp = currentCount;
						times--;
						while(times >0){
							currentCount *= temp;
							times --;
						}
					}
					
				}
				if (j==currentIntGroup.size()) {
					if (currentCount == result) {
//						System.out.println(formatResult(currentIntGroup, currentOperationList, result));
						resultList.add(formatResult(currentIntGroup, currentOperationList, result));
					}
				}
			}
		}
		return resultList;
	}
	
	
	private static String formatResult(List<Integer> intList, List<String> operationgList, int result){
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < operationgList.size(); i++) {
			str.append("(");
		}
		str.append(intList.get(0));
		for (int i = 1; i < intList.size(); i++) {
			str.append("").append(operationgList.get(i-1)).append(intList.get(i)).append(")");
		}
		str.append(" = ").append(result);
		return str.toString();
		
	}
	/**
	 * 排列组合类<BR>
	 * 用于排列组合各种情况适用
	 * @author walter.xu
	 *
	 */
	private static class Permutation{
		public static <T> List<List<T>> permutation(List<T> args){
			return permutation(args, false);
		}
		
		public static <T> List<List<T>> permutation(List<T> args, boolean repeatable){
			return permutation(args, args.size(), repeatable);
		}
		
		public static <T> List<List<T>> permutation(List<T> args, int totalSize, boolean repeatable){
			List<List<T>> resultList = new ArrayList<List<T>>();
			List<T> secondPart = new ArrayList<T>();
			permutation(args, secondPart, resultList, totalSize, repeatable);
			return resultList;
		}
		
		private static <T> void permutation(List<T> firstPart, List<T> secondPart, List<List<T>> resultList,
				int totalSize, boolean repeatable){
			if (totalSize == secondPart.size()) {
				resultList.add(secondPart);
				return ;
			}
			for (int i = 0; i < firstPart.size(); i++) {
				List<T> currentFirstPart = new ArrayList<T>(firstPart);
				List<T> currentSecondPart = new ArrayList<T>(secondPart);
				currentSecondPart.add(firstPart.get(i));
				if (!repeatable) {
					currentFirstPart.remove(i);
				}
				permutation(currentFirstPart, currentSecondPart, resultList, totalSize, repeatable);
			}
		}
	}
}
