package com.hbhs.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestCommonSubstring {
	
	public static List<String> lcs(String arg0, String arg1){
		int result = 0;
		List<Integer> positionList = new ArrayList<Integer>();
		int m = arg0.length();
		int n = arg1.length();
		int[][] tempArray = new int[m+1][n+1];
		for (int i = 0; i < m; i++) {
			tempArray[i][0]=0;
		}
		for (int i = 0; i < n; i++) {
			tempArray[0][i]=0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (arg0.charAt(i-1)==arg1.charAt(j-1)) {
					tempArray[i][j] = tempArray[i-1][j-1]+1; 
					if (tempArray[i][j] > result) {
						result = tempArray[i][j];
						positionList.clear();
						positionList.add(i);
					}else if (tempArray[i][j] == result) {
						positionList.add(i);
					}
				}else{
					tempArray[i][j] = 0;
				}
			}
		}
		for(int[] temp: tempArray){
			System.out.println(Arrays.toString(temp));
		}
		List<String> strList = new ArrayList<String>();
		for (int i = 0; i < positionList.size(); i++) {
			strList.add(arg0.substring(positionList.get(i)-result,positionList.get(i)));
		}
		return strList;
	}
}
