package com.hbhs.algorithm.common;

import java.util.Arrays;
import java.util.Date;

/**
 * 旋转数组
 * @ClassName: RotateArray 
 * @author Walter.xu
 * @date 2015年6月11日 下午13:12:01
 */
public class RotateArray {
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		RotateArray test = new RotateArray(4);
		for (int i = 0; i < 20; i++) {
			test.add(i);
		}
		System.out.println(test);
	}
	
	private int[][] array = null;
	private node currentNode = null;
	public RotateArray(int index){
		array = new int[index][index];
		currentNode = new node(0,0);
	}
	
	public void add(int i){
		if (currentNode==null) {
			System.out.println("Already full.");
			return;
		}
		StringBuilder str = new StringBuilder();
		str.append("add "+i+", current:"+currentNode);
		array[currentNode.i][currentNode.j] = i;
		findNext();
		System.out.println(str.append(", next:"+currentNode));
	}
	
	private void findNext(){
		if (currentNode.i==currentNode.j) {
			// 对角线，下一个节点要么往左，要么往右
			if (currentNode.i + currentNode.j < array.length-1) {
				// right
				currentNode.i = currentNode.i+1;
			}else if (currentNode.i + currentNode.j > array.length-1) {
				// left
				currentNode.i = currentNode.i-1;
			}else{
				// full
				currentNode = null;
			}
		} else if(currentNode.i > currentNode.j){
			if (currentNode.i+currentNode.j==array.length-1) {
				//down
				currentNode.j = currentNode.j+1;
			}else if (currentNode.i+currentNode.j<array.length-1) {
				// right
				currentNode.i = currentNode.i+1;
			}else{
				//down
				currentNode.j = currentNode.j+1;
			}
		} else{
			if (currentNode.i+currentNode.j > array.length-1) {
				//left
				currentNode.i = currentNode.i-1;
			}else if(currentNode.i+currentNode.j == array.length-1){
				// 当最后一个元素时
				if (currentNode.j-currentNode.i==1) {
					currentNode = null;
				}else{
					//up
					currentNode.j = currentNode.j-1;
				}
				
			} else{
				if (currentNode.j-currentNode.i==1) {
					//right
					currentNode.i = currentNode.i+1;
				}else{
					//up
					currentNode.j = currentNode.j-1;
				}
			}
		}
	}

	private static class node{
		public int i;
		public int j;
		public node(int i, int j){
			this.i = i;
			this.j = j;
		}
		public String toString(){
			return "("+i+","+j+")";
		}
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("Result: \n");
		for(int[] args: array){
			str.append(Arrays.toString(args)+"\n");
		}
		return str.toString();
	}
}
