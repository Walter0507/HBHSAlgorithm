package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <b>Insert Interval</b><br>
 * <br>
 * <br>Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <br>You may assume that the intervals were initially sorted according to their start times.
 * <br>
 * <br>Example 1:
 * <br>Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * <br>
 * <br>Example 2:
 * <br>Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * <br>This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * <br>
 * <br>
 * @author walter.xu
 *
 */
public class InsertInterval {

	public static void main(String[] args) {
		
	}
	
	public static List<Interval> solution(List<Interval> intervals, Interval newInterval){
		intervals.add(newInterval);
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
