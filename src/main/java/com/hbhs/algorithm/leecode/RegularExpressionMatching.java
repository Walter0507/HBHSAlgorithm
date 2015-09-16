package com.hbhs.algorithm.leecode;

/**
 * <b>Regular Expression Matching</b><br>
 * Implement regular expression matching with support for '.' and '*'.<br>
 * <br>'.' Matches any single character.
 * <br>'*' Matches zero or more of the preceding element.
 * <br>The matching should cover the entire input string (not partial).
 * <br>
 * <br>The function prototype should be:
 * <br>bool isMatch(const char *s, const char *p)
 * <br>
 * <br>isMatch("aa","a") → false
 * <br>isMatch("aa","aa") → true
 * <br>isMatch("aa", "a*") → true
 * <br>isMatch("aa", ".*") → true
 * <br>
 * @ClassName: RegularExpressionMatching 
 * @author Walter.xu
 * @date 2015年7月15日 下午1:35:50
 */
public class RegularExpressionMatching {
	
	public static void main(String[] args) {

		boolean isTrue = isMatch("aaa", "ab*ac*a");
		System.out.println(isTrue);
	}
	
	public static boolean isMatch(String s, String p){
		char[] sArray = s.toCharArray();
		char[] pArray = p.toCharArray();
		return checkIndex(sArray, sArray.length-1, pArray, pArray.length-1);
	}
	/**
	 * 对于匹配串p(m), 原始串s(n), 根据动态规划，仅当满足如下条件时，才匹配成功<BR>
	 * <BR>1 当p[m]为‘*’时，则s(n-1)仅匹配p(m)或者p(m-2)时两串匹配成功
	 * <BR>2 当p[m]为‘.’时，则s(n-1)仅匹配p(m-1)两串匹配成功
	 * <BR>3 当p[m]为其他字符且p[m]==s[n]时，则s(n-1)仅匹配p(m-1)时两串匹配成功
	 * <BR>4 其他情况下均为失败
	 * @param sArray
	 * @param sIndex
	 * @param pArray
	 * @param pIndex
	 * @return
	 */
	private static boolean checkIndex(char[] sArray, int sIndex,
			char[] pArray, int pIndex){
		boolean isTrue = false;
		if (sIndex >= 0) {
			if (pIndex>=0) {
				if (pArray[pIndex]=='*') {
					if (pArray[pIndex-1]=='.'||sArray[sIndex] == pArray[pIndex-1]) {
						isTrue= checkIndex(sArray, sIndex-1, pArray, pIndex)||
								checkIndex(sArray, sIndex-1, pArray, pIndex-2);
					}else{
						isTrue= checkIndex(sArray, sIndex, pArray, pIndex-2);
					}
				}else if (pArray[pIndex]=='.') {
					isTrue= checkIndex(sArray, sIndex-1, pArray, pIndex-1);
				}else if (pArray[pIndex]==sArray[sIndex]) {
					isTrue= checkIndex(sArray, sIndex-1, pArray, pIndex-1);
				}else{
					isTrue= false;
				}
			}else{
				isTrue= false;
			}
			
		}else{
			isTrue= isTrue(pArray, pIndex);
		}
		System.out.println(toStr(sArray, sIndex, pArray, pIndex)+", return: "+isTrue);
		return isTrue;
	}
	
	private static String toStr(char[] sArray, int sIndex, char[] pArray, int pIndex){
		StringBuilder str = new StringBuilder();
		str.append("string:[");
		for (int i = 0; i <= sIndex; i++) {
			str.append(sArray[i]);
		}
		str.append("], pattern[");
		for (int i = 0; i <= pIndex; i++) {
			str.append(pArray[i]);
		}
		str.append("]");
		return str.toString();
	}
	
	private static boolean isTrue(char[] pArray, int pIndex){
		if (pIndex<0) {
			return true;
		}
		if (pArray[pIndex]=='*') {
			return isTrue(pArray, pIndex-2);
		}else if (pArray[pIndex]=='.') {
			return isTrue(pArray, pIndex-1);
		}else
			return false;
	}
}
