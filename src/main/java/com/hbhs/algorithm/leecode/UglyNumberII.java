package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/***
 * <b>Ugly Number II</b>
 * <br>
 * <br>Write a program to find the n-th ugly number.
 * <br>
 * <br>Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * <br>
 * <br>Note that 1 is typically treated as an ugly number.
 * <br>
 * <br>
 * @ClassName: UglyNumberII 
 * @author Walter.xu
 * @date 2015年9月6日 下午4:27:47
 */
public class UglyNumberII {
	
	public static void main(String[] args) {
		System.out.println(nthUglyNumber(11));
	}
	public static int nthUglyNumber(int n) {
        List<Integer> uglyNumberList = new ArrayList<Integer>();
        uglyNumberList.add(1);
        int[] keyCurrentIndex = new int[]{0,0,0};
        while(uglyNumberList.size()!=n){
        	addNextMinUglyNumber(uglyNumberList, keyCurrentIndex);
        }
        return uglyNumberList.get(uglyNumberList.size()-1);
    }
	
	private static void addNextMinUglyNumber(List<Integer> uglyNumberList, int[] keyCurrentIndex){
		int nextTwo = uglyNumberList.get(keyCurrentIndex[0])*2;
		int nextThree = uglyNumberList.get(keyCurrentIndex[1])*3;
		int nextFive = uglyNumberList.get(keyCurrentIndex[2])*5;
		
		int nextMin = nextTwo;
		if (nextTwo <= nextThree) {
			if (nextTwo<=nextFive) {
				nextMin = nextTwo;
				keyCurrentIndex[0] += 1;
			}else{
				nextMin = nextFive;
				keyCurrentIndex[2] += 1;
			}
		}else{
			if (nextThree<=nextFive) {
				nextMin = nextThree;
				keyCurrentIndex[1] += 1;
			}else{
				nextMin = nextFive;
				keyCurrentIndex[2] += 1;
			}
		}
		if (nextMin!=uglyNumberList.get(uglyNumberList.size()-1)) {
			uglyNumberList.add(nextMin);
		}
	}

}
