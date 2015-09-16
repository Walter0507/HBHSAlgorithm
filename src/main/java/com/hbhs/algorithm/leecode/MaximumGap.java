package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Maximum Gap</b><br>
 * <br>
 * <br>Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * <br>Try to solve it in linear time/space.
 * <br>Return 0 if the array contains less than 2 elements.
 * <br>
 * <br>You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * <br>
 * @author walter.xu
 *
 */
public class MaximumGap {
	public static void main(String[] args) {
		int[] num = new int[]{1,10000};
		System.out.println(solution1(num));
	}
	/**
	 * 假设一个该数组(n位)中最大值最小值分别为：min, max<br>
	 * 那么最大的跨度应该为：maxGap = (max-min)/(n-1)<br>
	 * 按照桶来区分，那么最大的桶的数目为：maxBucket = (max-min)/maxGap+1;<br>
	 * 
	 * @param num
	 * @return
	 */
	public static int solution1(int[] num){
		if (num==null||num.length<2) {
			return 0;
		}
		if (num.length==0) {
			return num[0]>num[1]?(num[0]-num[1]):(num[1]-num[0]);
		}
		// find max and min value in the array
		int minValue = num[0], maxValue = num[0];
		for (int i = 1; i < num.length; i++) {
			if (num[i] < minValue) {
				minValue = num[i];
			}
			if (num[i] > maxValue) {
				maxValue = num[i];
			}
		}
		int maxBucketLength = (maxValue - minValue)/num.length+1;
		int maxBucket = (maxValue-minValue)/maxBucketLength + 1;
		List<Bucket> bucketList = new ArrayList<Bucket>();
		for (int i = 0; i < maxBucket; i++) {
			bucketList.add(new Bucket());
		}
		for (int i = 0; i < num.length; i++) {
			int bucketIndex = (num[i]-minValue)/maxBucketLength;
			Bucket currentBucket = bucketList.get(bucketIndex);
			if (currentBucket.getBucketSize()==0) {
				currentBucket.setMinValue(num[i]);
				currentBucket.setMaxValue(num[i]);
			} else if (num[i] > currentBucket.getMaxValue()) {
				currentBucket.setMaxValue(num[i]);
			} else if (num[i] < currentBucket.getMinValue()) {
				currentBucket.setMinValue(num[i]);
			}
			currentBucket.addBucketSize();
		}
		int maxGap = 0;
		int previousBucketIndex = 0;
		for (int i = 1; i < bucketList.size(); i++) {
			if (bucketList.get(i).getBucketSize()==0) {
				continue;
			}
			int currentGap = bucketList.get(i).getMinValue()-bucketList.get(previousBucketIndex).getMaxValue();
			if (currentGap>maxGap) {
				maxGap = currentGap;
			}
			previousBucketIndex = i;
		}
		return maxGap;
	}
}

class Bucket {
	private int maxValue;
	private int minValue;
	private int bucketSize = 0;
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getBucketSize() {
		return bucketSize;
	}
	public void setBucketSize(int bucketSize) {
		this.bucketSize = bucketSize;
	}
	
	public void addBucketSize(){
		this.bucketSize = bucketSize+1;
	}
}