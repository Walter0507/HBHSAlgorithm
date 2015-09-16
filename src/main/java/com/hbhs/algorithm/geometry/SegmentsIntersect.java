package com.hbhs.algorithm.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <B>确定任意两条线段相交</B>
 * 算法名称为<B>扫除(Sweeping)技术</B>
 * @author walter.xu
 *
 */
public class SegmentsIntersect {
	// 可以用红黑树 或者其他结构
	private List<Segment> segmentList = new ArrayList<Segment>();
	private Comparator<Segment> comparator = new Comparator<Segment>() {
		public int compare(Segment arg0, Segment arg1) {
			int crossDiff = (int)(arg0.getCross() - arg1.getCross());
			if (crossDiff==0) {
				return arg0.getName().compareTo(arg1.getName());
			}
			return crossDiff;
		}
	};
	public SegmentsIntersect(){
	}
	public synchronized void insert(Segment segment){
		if (segment!=null) {
			segmentList.add(segment);
			Collections.sort(segmentList,comparator);
		}
	}
	
	public synchronized void delete(Segment segment){
		int index = findIndex(segment);
		if (index>=0) {
			segmentList.remove(index);
		}
	}
	
	public Segment above(Segment segment){
		int index = findIndex(segment);
		if (index<=0) {
			return null;
		}
		return segmentList.get(index-1);
	}
	
	public Segment below(Segment segment){
		int index = findIndex(segment);
		if (index>=segmentList.size()) {
			return null;
		}
		return segmentList.get(index+1);
	}
	
	private int findIndex(Segment segment){
		int index = -1;
		for (int i = 0; i < segmentList.size(); i++) {
			if (segment.getCross() == segmentList.get(i).getCross()) {
				if (segment.equals(segmentList.get(i))) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
}
