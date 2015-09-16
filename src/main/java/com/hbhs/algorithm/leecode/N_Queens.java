package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>N-Queens </b><br>
 * <br>
 * <br>The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <br>
 * <br>Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <br>
 * <br>Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <br>
 * <br>
 * <br>
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class N_Queens {
	
	public static void main(String[] args) {
		N_Queens test = new N_Queens();
//		int[] array = new int[]{0,2,-1,-1};
//		System.out.println(test.checkIfCross(array, 2, 0));
//		System.out.println(test.checkIfCross(array, 2, 1));
//		System.out.println(test.checkIfCross(array, 2, 2));
//		System.out.println(test.checkIfCross(array, 2, 3));
//		System.out.println(test.checkIfCross(array, 3, 1));
		List<String[]> result = test.solution1(4);
		int index = 1;
		for(String[] s: result){
			System.out.println("Result: "+index++);
			for(String str: s){
				System.out.println(str);
			}
		}
	}
	
	public List<String[]> solution1(int n) {
        List<String[]> result = new ArrayList<String[]>();
        int[] compareArray = new int[n];
        initArray(compareArray);
        
        int rowIndex = 0;
        while(rowIndex<n){
        	compareArray[0] = rowIndex;
        	setUpNextRow(compareArray, 1, n, result);
        	rowIndex++;
        }
        return result;
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
