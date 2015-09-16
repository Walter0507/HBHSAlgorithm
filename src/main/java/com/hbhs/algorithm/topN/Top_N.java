package com.hbhs.algorithm.topN;

import java.util.List;

/**
 * TOPN算法<BR>
 * 采用一个最大堆或者最小堆来维护保留TOPN数据<Br>
 * 算法思想<BR>
 * 1 先将初始文件R[1..n]建成一个大根堆，此堆为初始的无序区<Br>
 * 2 再将关键字最大的记录R[1]（即堆顶）和无序区的最后一个记录R[n]交换，由此得到新的无序区R[1..n-1]和有序区R[n]，且满足R[1..n-1
 * ].keys≤R[n].key<BR>
 * 3由于交换后新的根R[1]可能违反堆性质，故应将当前无序区R[1..n-1]调整为堆。然后再次将R[1..n-1]中关键字最大的记录R[1]
 * 和该区间的最后一个记录R
 * [n-1]交换，由此得到新的无序区R[1..n-2]和有序区R[n-1..n]，且仍满足关系R[1..n-2].keys≤R[n-1.
 * .n].keys，同样要将R[1..n-2]调整为堆。<Br>
 * ……<Br>
 * 直到无序区只有一个元素为止。
 * 
 * @author walter.xu
 *
 */
public class Top_N {
	private int top = 10;
	private int[] maxOrMinHeap = null;
	private boolean maxHeap = true;
	private int index = 0;
	

	private Top_N(int top, boolean isMaxHeap) {
		this.top = top;
		maxOrMinHeap = new int[top];
		maxHeap = isMaxHeap;
	}

	public static Top_N getInstance(int top) {
		return getInstance(top, true);
	}
	/**
	 * 构造最大堆或者最小堆
	 * @param top
	 * @param isMaxHeap true为最大堆，false为最小堆
	 * @return
	 */
	public static Top_N getInstance(int top, boolean isMaxHeap) {
		return new Top_N(top, isMaxHeap);
	}
	
	public int[] result(){
		return maxOrMinHeap;
	}

	public Top_N add(int arg0) {
		if (maxHeap) {
			if (index < top) {
				maxOrMinHeap[index] = arg0;
				index++;
			}else if(arg0 < maxOrMinHeap[0]){
				maxOrMinHeap[0] = arg0;
				heapAdjust(0);
			}
		} else {
			if (index < top) {
				maxOrMinHeap[index] = arg0;
				index++;
			}else if(arg0 >= maxOrMinHeap[0]){
				maxOrMinHeap[0] = arg0;
				heapAdjust(0);
			}
		}
		return this;
	}

	public Top_N addList(List<Integer> integerList) {
		for(Integer arg0: integerList){
			if (arg0 != null) {
				add(arg0);
			}
		}
		return this;
	}

	private void heapAdjust(int rootNodeIndex) {
		int leftNodeIndex = 2 * (rootNodeIndex + 1) - 1;
		int rightNodeIndex = 2 * (rootNodeIndex + 1);

		int compareNodeIndex = rootNodeIndex;
		if (maxHeap) {
			if (leftNodeIndex < top
					&& maxOrMinHeap[leftNodeIndex] > maxOrMinHeap[compareNodeIndex]) {
				compareNodeIndex = leftNodeIndex;
			}
			if (rightNodeIndex < top
					&& maxOrMinHeap[rightNodeIndex] > maxOrMinHeap[compareNodeIndex]) {
				compareNodeIndex = rightNodeIndex;
			}
		} else {
			if (leftNodeIndex < top
					&& maxOrMinHeap[leftNodeIndex] <= maxOrMinHeap[compareNodeIndex]) {
				compareNodeIndex = leftNodeIndex;
			}
			if (rightNodeIndex < top
					&& maxOrMinHeap[rightNodeIndex] <= maxOrMinHeap[compareNodeIndex]) {
				compareNodeIndex = rightNodeIndex;
			}
		}
		if (compareNodeIndex != rootNodeIndex) {
			int swap = maxOrMinHeap[compareNodeIndex];
			maxOrMinHeap[compareNodeIndex] = maxOrMinHeap[rootNodeIndex];
			maxOrMinHeap[rootNodeIndex] = swap;
			heapAdjust(compareNodeIndex);
		}
	}
}
