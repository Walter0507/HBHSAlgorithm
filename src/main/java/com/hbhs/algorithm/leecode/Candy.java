package com.hbhs.algorithm.leecode;

import java.util.Arrays;

/**
 * <b>Candy </b><br>
 * <br>There are N children standing in a line. Each child is assigned a rating value.
 * <br>
 * <br>You are giving candies to these children subjected to the following requirements:
 * <br>Each child must have at least one candy.
 * <br>Children with a higher rating get more candies than their neighbors.
 * <br>
 * <br>What is the minimum candies you must give?
 * <br>
 * @author walter.xu
 *
 */
public class Candy {
	
	public static void main(String[] args) {
		int[] ratings = new int[]{1,2,2};
		solution2s(ratings);
	}
	public static int solution2s(int[] ratings){
		if (ratings==null||ratings.length==0) {
			return 0;
		}
		int[] candyCountsArray = new int[ratings.length];
		candyCountsArray[0] = 1;
		for (int i = 1; i < ratings.length; ) {
			if (ratings[i] == ratings[i-1]) {
				int start = i, end = i;
				while(end<ratings.length&&ratings[start]==ratings[end]){
					end++;
				}
				for (int j = start; j < end; j++) {
					candyCountsArray[j] = candyCountsArray[j-1];
				}
				i = end;
			}else if (ratings[i] > ratings[i-1]) {
				candyCountsArray[i] = candyCountsArray[i-1]+1;
				i++;
			}else{
				int start = i-1, end=i;
				while(end<ratings.length&&ratings[end]<ratings[end-1]){
					end++;
				}
				candyCountsArray[end-1] = 1;
				for (int j = end-2; j > start; j--) {
					candyCountsArray[j] = candyCountsArray[j+1]+1;
				}
				if (candyCountsArray[start] <= candyCountsArray[start+1]) {
					boolean needCheckSameRating = start>0?candyCountsArray[start-1]==candyCountsArray[start]:false;
					candyCountsArray[start] = candyCountsArray[start+1]+1;
					start--;
					while (needCheckSameRating) {
						needCheckSameRating = start>0?candyCountsArray[start-1]==candyCountsArray[start]:false;
						candyCountsArray[start] = candyCountsArray[start+1];
						start--;
					}
				}
				i=end;
			}
			
		}
		System.out.println(Arrays.toString(ratings));
		System.out.println(Arrays.toString(candyCountsArray));
		int sum = 0;
		for (int i = 0; i < candyCountsArray.length; i++) {
			sum += candyCountsArray[i];
		}
		return sum;
	}
	public static int solution1(int[] ratings){
		if (ratings==null||ratings.length==0) {
			return 0;
		}
		int[] candyCountsArray = new int[ratings.length];
		candyCountsArray[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i-1]>ratings[i]) {
				candyCountsArray[i] = 1;
				int adjustIndex = i;
				while(adjustIndex>0 && candyCountsArray[adjustIndex]==candyCountsArray[adjustIndex-1]){
					candyCountsArray[adjustIndex-1] = candyCountsArray[adjustIndex]+1;
					adjustIndex-- ;
				}
			}else if (ratings[i-1] == ratings[i]) {
				candyCountsArray[i] = candyCountsArray[i-1];
			}else{
				candyCountsArray[i] = candyCountsArray[i-1]+1;
			}
		}
		System.out.println(Arrays.toString(ratings));
		System.out.println(Arrays.toString(candyCountsArray));
		int sum = 0;
		for (int i = 0; i < candyCountsArray.length; i++) {
			sum += candyCountsArray[i];
		}
		return sum;
	}
	
}
