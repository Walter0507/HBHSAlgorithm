package com.hbhs.algorithm.string.onePattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Boyer-Moore（BM）算法<br>
 * Boyer-Moore算法是一种基于后缀匹配的模式串匹配算法，后缀匹配就是模式串从右到左开始比较，但模式串的移动还是从左到右的。
 * 字符串匹配的关键就是模式串的如何移动才是最高效的，Boyer-Moore为了做到这点定义了两个规则：坏字符规则和好后缀规则<br>
 * 坏字符规则<bR>
 * 1.如果坏字符没有出现在模式字符中，则直接将模式串移动到坏字符的下一个字符：<br>
 * 2.如果坏字符出现在模式串中，则将模式串最靠近好后缀的坏字符（当然这个实现就有点繁琐）与母串的坏字符对齐：<br>
 * 好后缀规则<bR>
 * 1.模式串中有子串匹配上好后缀，此时移动模式串，让该子串和好后缀对齐即可，如果超过一个子串匹配上好后缀，则选择最靠靠近好后缀的子串对齐。<br>
 * 2.模式串中没有子串匹配上后后缀，此时需要寻找模式串的一个最长前缀，并让该前缀等于好后缀的后缀，寻找到该前缀后，让该前缀和好后缀对齐即可。<br>
 * 3.模式串中没有子串匹配上后后缀，并且在模式串中找不到最长前缀，让该前缀等于好后缀的后缀。此时，直接移动模式到好后缀的下一个字符。<br>
 * 
 * @author walter.xu
 *
 */
public class BM {
	public static void main(String[] args) {
		List<Integer> matches = bm("bananas", "ana");
		for (Integer integer : matches)
			System.out.println("Match at: " + integer);
	}

	/**
	 * BM搜索算法
	 * @param text 待搜索的字符串
	 * @param pattern 模式字符串
	 * @return 返回的搜索结果，为空时表示未搜索到结果
	 */
	public static List<Integer> bm(String text, String pattern) {
		// 搜索结果
		List<Integer> matchList = new ArrayList<Integer>();
		int m = text.length();  // 待匹配字符串的长度
		int n = pattern.length();  // 模式字符串的长度
		// 生成模式字符串的坏字符移动结果
		Map<Character, Integer> backCharacterShift = prepareBadShift(pattern);
		int alignedAt = 0;   // 匹配的节点位置
		// 如果当前节点在可匹配范围内，即当前的A[k]必须在A[0, m-n-1)之间，否则没有必要做匹配
		while (alignedAt + (n - 1) < m) {
			// 循环模式组，查询模式组是否匹配 从模式串的最后面开始匹配，并逐渐往前匹配
			for (int indexInPattern = n - 1; indexInPattern >= 0; indexInPattern--) {
				// 1 定义待查询字符串中的当前匹配位置. 
				int indexInText = alignedAt + indexInPattern;
				// 2 验证带查询字符串的当前位置是否已经超过最长字符，如果超过，则表示未查询到.
				if (indexInText >= m)
					break;
				// 3 获取到带查询字符串和模式字符串中对应的待匹配字符
				char x = text.charAt(indexInText);
				char y = pattern.charAt(indexInPattern);
				// 4 验证结果
				if (x != y) {
					// 4.1 如果两个字符串不相等，则寻找最坏字符串的结果，生成下次移动的队列位置
					Integer r = backCharacterShift.get(x);
					if (r == null) {
						// 当前坏字符串未在模式串中出现，则移动模式串到坏字符串的后一位
						alignedAt = indexInText + 1;
					} else {
						// 当前坏字符串在模式串中存在，则将模式串最靠近好后缀的坏字符与母串的坏字符对齐
						// shift 实际为模式串总长度
						int shift = indexInText - (alignedAt + r);
						alignedAt += shift > 0 ? shift : 1;
					}
					// 退出匹配
					break;
				} else if (indexInPattern == 0) {
					// 4.2 匹配到的话 并且最终匹配到模式串第一个字符，便是已经找到匹配串，记录下当前的位置
					matchList.add(alignedAt);
					alignedAt++;
				}
			}
		}
		return matchList;
	}

	/**
	 * 坏字符串<br>
	 * 依据待匹配的模式字符串生成一个坏字符串的移动列，该移动列中表明当一个坏字符串出现时，需要移动的位数
	 * @param pattern
	 * @return
	 */
	private static Map<Character, Integer> prepareBadShift(String pattern) {
		// 
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		// BM算法是从后往前查询，所以其生成的坏字符串应该也是从字符串后面开始设置
		for (int i = pattern.length() - 1; i >= 0; i--) {
			char c = pattern.charAt(i);
			if (!map.containsKey(c))
				map.put(c, i);
		}
		return map;
	}
}
