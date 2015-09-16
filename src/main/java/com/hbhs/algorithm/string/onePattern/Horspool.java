package com.hbhs.algorithm.string.onePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Horspool算法<BR>
 * Horspool算法可以说是BM算法的意见简化版本。在进行后缀匹配的时候，若发现不匹配字符，则需要将模式向右移动。假设文本中对齐模式最后一个字符的元素是字符C，则Horspool算法根据C的不同情况来确定移动的距离。实际上，Horspool算法也就是通过最大安全移动距离来减少匹配的次数，从而提高运行效率的。<Br>
 * 
 * @author walter.xu
 *
 */
public class Horspool {

	/**
	 * Horspool算法<BR>
	 * 
	 * @param text 待搜索字符串
	 * @param patternStr 模式匹配字符串
	 * @return 所有的匹配结果集合
	 */
	public static List<Integer> horspool(String text, String patternStr){
		// 结果集合
		List<Integer> positionList = new ArrayList<Integer>();
		int m = text.length();       // 待搜索的字符串长度
		int n = patternStr.length(); // 模式串的长度
		int alignAt = 0;             // 当前待匹配串的验证位置
		// 当匹配位置合法时 一直执行
		while(alignAt+n <= m){
			// 从模式串的尾部开始匹配
			for (int i = n-1; i >=0; i--) {
				// 对于待匹配串，如果超长，则直接退出
				if (alignAt+i >= m) {
					break;
				}
				// 如果未匹配上，执行查询跳转的steps
				if (patternStr.charAt(i) != text.charAt(alignAt+i)) {
					// 获取到未匹配到的坏字符串
					char badChar = patternStr.charAt(i);
					// 移动的step数目
					int moveSteps = 1;
					// 循环模式串之前的字符串，直到找到一个最近的坏字符串位置，同时记录下需要移动的steps值
					for (int j = i-1; j >= 0; j--) {
						if (patternStr.charAt(j)==badChar) {
							// 找到后退出
							break;
						}
						moveSteps ++;
					}
					// 移动位数
					alignAt += moveSteps;
				}else{
					if (i==0) {
						// 如果完全匹配，则添加该位置
						positionList.add(alignAt);
						alignAt++;
					}
				}
			}
		}
		return positionList;
	}
}
