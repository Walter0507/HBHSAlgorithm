package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>N-Queens II </b><br>
 * <br>
 * <br>Follow up for N-Queens problem.
 * <br>
 * <br>Now, instead outputting board configurations, return the total number of distinct solutions.
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class N_QueensII {
	
	public static void main(String[] args) {
		N_QueensII test = new N_QueensII();
		int count = test.solution1(4);
		System.out.println("COUNT: "+count);
	}
	
	public int solution1(int n) {
        List<String[]> result = new ArrayList<String[]>();
        int[] compareArray = new int[n];
        initArray(compareArray);
        
        int rowIndex = 0;
        while(rowIndex<n){
        	compareArray[0] = rowIndex;
        	setUpNextRow(compareArray, 1, n, result);
        	rowIndex++;
        }
        return result.size();
    }
	
	public void setUpNextRow(int[] compareArray, int index, final int n, List<String[]> result){
		if (compareArray[n-1]>-1) {
			//success
			result.add(formatSuccess(compareArray));
			compareArray[index-1] = -1;
			return ;
		}
		for (int i = 0; i < n; i++) {
			boolean isCross = checkIfCross(compareArray, index, i);
			if (!isCross) {
				compareArray[index] = i;
				// next line
				setUpNextRow(compareArray, index+1, n, result);
			}
		}
		
	}
	
	
	private void initArray(int[] array){
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
	}
	
	private boolean checkIfCross(int[] array, int currentIndex, int currentValue){
		for (int i = 0; i < currentIndex; i++) {
//			System.out.println("Node: ("+currentIndex+", "+currentValue+"), ("+i+","+array[i]+")");
			if (currentValue == array[i]) {
				return true;
			}
			if (currentIndex - i == currentValue - array[i]) {
				return true;
			}
			if (currentIndex - i == array[i] - currentValue) {
				return true;
			}
		}
		
		return false;
	}
	private String[] formatSuccess(int[] array){
		String[] resultList = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String value = "";
			for (int j = 0; j < resultList.length; j++) {
				if (j == array[i]) {
					value += "Q";
				}else{
					value += ".";
				}
			}
			resultList[i] = value;
		}
		return resultList;
	}
}
