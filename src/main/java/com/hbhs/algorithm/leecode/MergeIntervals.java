package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <b>Merge Intervals </b><br>
 * <bR>
 * <bR>Given a collection of intervals, merge all overlapping intervals.
 * <bR>
 * <bR>For example,
 * <bR>
 * <bR>Given [1,3],[2,6],[8,10],[15,18],
 * <bR>return [1,6],[8,10],[15,18].
 * <bR>
 * <bR>
 * <bR>
 * @author walter.xu
 *
 */
public class MergeIntervals {
	public static void main(String[] args) {
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1, 4));
		list.add(new Interval(1, 4));
		list.add(new Interval(5, 5));
		list.add(new Interval(5, 6));
		list.add(new Interval(5, 14));
		List<Interval> result = solution(list);
		System.out.println(Arrays.toString(result.toArray()));
	}
	
	public static List<Interval> solution(List<Interval> intervals){
		if (intervals.size() < 2) {
			return intervals;
		}
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			}
		});
		List<Interval> resultList = new ArrayList<Interval>();
		int index = 0;
		int compareIndex = 1;
		while(compareIndex<intervals.size()){
			if (intervals.get(index).end >= intervals.get(compareIndex).start) {
				if (intervals.get(index).end < intervals.get(compareIndex).end) {
					intervals.get(index).end = intervals.get(compareIndex).end;
				}
				compareIndex++;
			}else{
				resultList.add(intervals.get(index));
				index = compareIndex;
			}
			
		}
		resultList.add(intervals.get(index));
		return resultList;
	}
}
class Interval{
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
	public String toString(){
		return "["+start+", "+end+"]";
	}
}