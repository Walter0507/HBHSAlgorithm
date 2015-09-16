package com.hbhs.algorithm.string;

/**
 * 最长公共子序列<BR>
 * 如果字符串一的所有字符按其在字符串中的顺序出现在另外一个字符串二中，则字符串一称之为字符串二的子串。注意，并不要求子串（字符串一）
 * 的字符必须连续出现在字符串二中。<BR>
 * 考虑最长公共子序列问题如何分解成子问题，设A=“a0，a1，…，am-1”，B=“b0，b1，…，bn-1”，并Z=“z0，z1，…，zk-1”
 * 为它们的最长公共子序列。不难证明有以下性质：<br>
 * 
 * 1 如果am-1==bn-1，则zk-1=am-1=bn-1，且“z0，z1，…，zk-2”是“a0，a1，…，am-2”和“b0，b1，…，bn-2”
 * 的一个最长公共子序列；<br>
 * 
 * 2 如果am-1!=bn-1，则若zk-1!=am-1时，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-2”和“b0，b1，…，bn-1”
 * 的一个最长公共子序列；<br>
 * 
 * 3 如果am-1!=bn-1，则若zk-1!=bn-1时，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-1”和“b0，b1，…，bn-2”
 * 的一个最长公共子序列。<br>
 * 
 * @author walter.xu
 *
 */
public class LongestCommonSubsquence {
	
	/**
	 * lcs
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static String lcs(String arg0, String arg1) {
		int m = arg0.length();
		int n = arg1.length();
		int[][] countArray = new int[m+1][n+1];
		int[][] directArray = new int[m+1][n+1];
		for (int i = 0; i < m; i++) {
			countArray[i][0]=0;
		}
		for (int i = 0; i < n; i++) {
			countArray[0][i]=0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (arg0.charAt(i-1)==arg1.charAt(j-1)) {
					countArray[i][j]=countArray[i-1][j-1]+1;
					directArray[i][j] = 5;
				}else{
					if (countArray[i-1][j] >= countArray[i][j-1]) {
						countArray[i][j]=countArray[i-1][j];
						directArray[i][j] = 1;
					}else{
						countArray[i][j]=countArray[i][j-1];
						directArray[i][j] = 9;
					}
				}
			}
		}
		char[] result = new char[countArray[m][n]];
		int i = m, j = n, index = countArray[m][n]-1;
		while(i>0&&j>0){
			int direct = directArray[i][j];
			if (direct==5) {
				result[index] = arg0.charAt(i-1);
				i--;
				j--;
				index--;
			}else if (direct==1) {
				i--;
			}else{
				j--;
			}
			
		}
		return String.valueOf(result);
	}
}
