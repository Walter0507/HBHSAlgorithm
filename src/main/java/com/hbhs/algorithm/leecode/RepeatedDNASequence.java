package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>Repeated DNA Sequences</b><br>
 * <br>
 * <br>All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <br>
 * <br>Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <br>
 * <br>For example,
 * <br>
 * <br>Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * <br>Return: ["AAAAACCCCC", "CCCCCAAAAA"].
 * @author walter.xu
 *
 */
public class RepeatedDNASequence {
	
	public static void main(String[] args) {
		
		List<String> result = solution2("AAAAAAAAAAAA");
		
		for(String str: result){
			System.out.println(str);
		}
	}
	
	public static List<String> solution2(String s){
		if (s.length()<=10) {
			return new ArrayList<String>();
		}
		Set<String> resultList = new HashSet<String>();
		Map<Character, Integer> map = initMap();
		int count = countFirst(s, map);
		BitSet bit = new BitSet(1<<20);
		bit.set(count);
		
		for (int i = 10; i < s.length(); i++) {
			count = (count>>2) + (map.get(s.charAt(i))<<18);
			if (bit.get(count)) {
				resultList.add(s.substring(i-9,i+1));
			}else{
				bit.set(count);
			}
		}
		return new ArrayList<String>(resultList);
	}
	
	private static int countFirst(String s, Map<Character, Integer> map){
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum+= map.get(s.charAt(i))<<(i*2);
		}
		return sum;
	}
	
	private static Map<Character, Integer> initMap(){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		return map;
	}
	
}
