package com.hbhs.algorithm.leecode;

import java.util.BitSet;

/**
 * 如何编程最快速度求出两百万以内素数个数
 * @author walter.xu
 *
 */
public class FindPrimeNumber {
	public static void main(String[] args) {
		System.out.println(findCountBelow(2000000));
		System.out.println(findCountBelow_1(2000000));
//		System.out.println(findCountBelow_1(100));
	}
	
	public static int findCountBelow(int maxCount){
		long start = System.currentTimeMillis();
		BitSet bs = new BitSet(maxCount);
		for (int i = 2; i <= maxCount/2; i++) {
			int j = 2;
			while((i*j>0)&&(i*j <= maxCount)){
				bs.set((i*j)-1, true);
				j++;
			}
		}
		int toalCount = bs.size()-bs.cardinality();
		System.out.println("Total cost: "+(System.currentTimeMillis() - start)+"ms");
		return toalCount;
	}
	public static int findCountBelow_1(int maxCount){
		long start = System.currentTimeMillis();
		int sqrt = (int)Math.sqrt(maxCount);
		BitSet bs = new BitSet(maxCount);
		for (int i = 2; i <= sqrt; i++) {
			int j = i;
			while((i*j>0)&&(i*j <= maxCount)){
				bs.set((i*j)-1, true);
				j++;
			}
		}
		int toalCount = bs.size()-bs.cardinality();
		System.out.println("Total cost: "+(System.currentTimeMillis() - start)+"ms");
		
		return toalCount;
	}
}
