package com.hbhs.algorithm.string.onePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sunday算法<BR>
 * 核心思想是：在匹配过程中，模式串发现不匹配时，算法能跳过尽可能多的字符以进行下一步的匹配，从而提高了匹配效率。Sunday算法在单模式字符串搜索算法中应该是最快的算法，比KMP一级BM都要快<br>
 * 算法用例<BR>
 * 匹配串：O U R S T R O N G X S E A R C H<BR>
 * 模式串：S E A R C H<BR>
 * 这里我们看到O-S不相同，我们就看匹配串中的O在模式串的位置，没有出现在模式串中。<BR>
 * 匹配串：O U R S T R O N G X S E A R C H<Br>
 * 模式串：____________S E A R C H<BR>
 * 移动模式串，使模式串的首字符和O的下一个字符对齐。<BR>
 * 匹配串：O U R S T R O N G X S E A R C H<BR>
 * 模式串：____________________S E A R C H<BR>
 * 匹配完成<Br>
 * 
 * Sunday核心思想：
 * 1 需要记录匹配串的下个字符在模式串中的位置
 * 2 如果未找到，则移动整个模式串的长度
 * 3 如果找到，则移动最右边长度的模式串长度
 * @author walter.xu
 *
 */
public class Sunday {
	
	public static void main(String[] args) {
		
	}
	
	public static List<Integer> sunday(String text, String pattern){
		List<Integer> positionList = new ArrayList<Integer>();
		int m = text.length();
		int n = pattern.length();
		// 获取到stepsMap
		Map<Character, Integer> stepsMap = genStepsMap(pattern);
		// 定义匹配串当前最左边的匹配位置
		int alignAt = 0;
		// 保证该位置在可匹配范围内
		while(alignAt <= m-n){
			// 匹配串与模式串逐个匹配
			
			for (int i = 0; i < n; i++) {
				// 一旦发现未匹配的字符串
				if (text.charAt(alignAt+i)!=pattern.charAt(i)) {
					// 如果不相等，则匹配窗口中的最右边在下一位的位置
					// 验证A[alignAt+n+1]是否已经越界，如果越界，则直接退出
					if (alignAt+n+1>=m) {
						break;
					}
					// 获取到待匹配的字符串
					char nextMatchChar = text.charAt(alignAt+n);
					// 查询该字符串的需要移动的位置数目，如果为空，则默认移动整个模式串长度
					int steps = stepsMap.get(nextMatchChar)==null?n:stepsMap.get(nextMatchChar);
					// 移动匹配串位置
					alignAt += steps;
				}else{
					if (i == n-1) {
						positionList.add(alignAt);
						alignAt ++;
					}
				}
			}
		}
		return positionList;
	}
	
	private static Map<Character, Integer> genStepsMap(String pattern){
		Map<Character, Integer> stepsMap = new HashMap<Character, Integer>();
		char[] patternArrary = pattern.toCharArray();
		for (int i = patternArrary.length-1; i >= 0; i--) {
			// 从尾部开始计算，计算字符串移动的匹配串的下一位所需要的位移
			if (stepsMap.get(patternArrary[i])==null) {
				stepsMap.put(patternArrary[i], patternArrary.length-i);
			}
		}
		return stepsMap;
	}
}
