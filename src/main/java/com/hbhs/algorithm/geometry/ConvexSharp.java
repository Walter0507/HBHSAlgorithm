package com.hbhs.algorithm.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;


/**
 * 求多个节点的凸边形<BR>
 * @author walter.xu
 *
 */
public class ConvexSharp {
	// 所有节点组合
	private List<ConvexSharpNode> nodeList = new ArrayList<ConvexSharpNode>();
	// 待求的多边形的节点组合
	private List<CoordsNode> convexNodeList = new ArrayList<CoordsNode>();

	/**
	 * 插入一个坐标节点
	 * @param x
	 * @param y
	 */
	public void insertNode(double x, double y){
		nodeList.add(new ConvexSharpNode(x, y));
	}
	/**
	 * 插入一个坐标节点
	 * @param x
	 * @param y
	 */
	public void insertNode(int x, int y){
		nodeList.add(new ConvexSharpNode(x, y));
	}
	
	public void insertSegment(Segment segment){
		if (segment!=null) {
			insertNode(segment.getStartNode());
			insertNode(segment.getEndNode());
		}
	}
	
	public void insertConvex(ConvexSharp convex){
		if (convex!=null) {
			List<CoordsNode> nodeList = convex.getConvexNodes();
			for(CoordsNode node: nodeList){
				insertNode(node);
			}
		}
	}
	
	/**
	 * 插入一个坐标节点
	 * @param nodeList
	 */
	public void insertNodes(List<CoordsNode> nodeList){
		if (nodeList==null||nodeList.size()==0) {
			return;
		}
		for(CoordsNode node: nodeList){
			insertNode(node);
		}
	}
	/**
	 * 插入一个坐标节点
	 * @param node
	 */
	public void insertNode(CoordsNode node){
		if (node!=null) {
			nodeList.add(new ConvexSharpNode(node.getX(), node.getY()));
			// 排序，1 按照x从小到大排序，2 如果x相同，将Y较小的放前面
			Collections.sort(nodeList,new Comparator<CoordsNode>() {
				public int compare(CoordsNode arg0, CoordsNode arg1) {
					if (arg0.getX()==arg1.getX()) {
						return (int)((arg0.getY()-arg1.getY())*100);
					}
					return (int)((arg0.getX()-arg1.getX())*100);
				}
			});
		}
	}
	/**
	 * 返回凸多边形的所有节点
	 * @return
	 */
	public List<CoordsNode> getConvexNodes(){
		return convexNodeList;
	}
	/**
	 * 返回凸多边形的所有线段
	 * @return
	 */
	public List<Segment> getConvexSegments(){
		List<CoordsNode> nodeList = this.getConvexNodes();
		if (nodeList == null||nodeList.size()<2) {
			return null;
		}
		
		List<Segment> segmentList = new ArrayList<Segment>();
		for (int i = nodeList.size()-2; i >= 0; i--) {
			segmentList.add(new Segment(nodeList.get(i+1), nodeList.get(i),false));
		}
		// 如果节点为2个，则表示仅为一条线
		if (nodeList.size()!=2) {
			segmentList.add(new Segment(nodeList.get(0), nodeList.get(nodeList.size()-1),false));
		}
		return segmentList;
	}
	/**
	 * GRAHAM-SCAN算法
	 */
	public List<CoordsNode> grahamScan(){
		// 清空并重新计算所有的凸点
		convexNodeList.clear();
		if (nodeList == null||nodeList.size()<3) {
			for(ConvexSharpNode node: nodeList){
				convexNodeList.add(node);
			}
			return convexNodeList;
		}
		// 使用一个栈来做处理
		Stack<ConvexSharpNode> nodeStack = new Stack<ConvexSharpNode>();
		// 已经扫描节点列表
		List<ConvexSharpNode> alreadyLoadNodeList = new ArrayList<ConvexSharpNode>();
		// 1 获取第一个节点，并且以该节点为参考，求出所有节点的极值
		ConvexSharpNode compareNode = nodeList.get(0);
		for (int i = 0; i < nodeList.size(); i++) {
			nodeList.get(i).setCrossValue(compareNode);
		}
		// 2 将第一，二个节点放入到栈中，并将这两个节点从原来节点中删除
		nodeStack.push(nodeList.get(0));
		alreadyLoadNodeList.add(nodeList.get(0));
		nodeStack.push(nodeList.get(1));
		alreadyLoadNodeList.add(nodeList.get(1));
		// 删除
		nodeList.remove(0);
		nodeList.remove(0);
		// 为防止节点列表中有多个开始节点(x值最小)的X值是相同的，我们仅取出Y值最大的那个
		while (nodeList.size()>0) {
			// 不相同时退出
			if (nodeList.get(0).getX()!=nodeStack.get(1).getX()) {
				break;
			}
			// 吧前一个栈中的值pop出来
			nodeStack.pop();
			// 并把待插入的节点插入，因为带插入的节点X与POP出来的X相同，所以整个线段是相连的
			nodeStack.push(nodeList.get(0));
			alreadyLoadNodeList.add(nodeList.get(0));
			// 同时把该节点从待插入中删除
			nodeList.remove(0);
		}
		// 3 剩下的所有节点肯定和已经入栈的节点X不相同，那么按照极值从小到大排序
		Collections.sort(nodeList, new Comparator<ConvexSharpNode>() {
			public int compare(ConvexSharpNode arg0, ConvexSharpNode arg1) {
				return (int)((arg0.getCrossValue()-arg1.getCrossValue())*100);
			}
		});
		// 4 把排序后的极值最小的一个放入栈中，并且移除。
		nodeStack.push(nodeList.get(0));
		alreadyLoadNodeList.add(nodeList.get(0));
		nodeList.remove(0);
		// 5 分别找出参照节点(栈的顶点以及下一个顶点)
		ConvexSharpNode compareStartNode = nodeStack.get(nodeStack.size()-2);
		ConvexSharpNode compareEndNode = nodeStack.get(nodeStack.size()-1);
		for (int i = 0; i < nodeList.size(); i++) {
			// 计算下一个节点在参考线段的防线，大于0表示在顺时针，小于0表示逆时针
			double direction = SegmentCross.direction(compareStartNode, compareEndNode, nodeList.get(i));
			// 如果待匹配节点是在逆时针或者刚好在线段延长线上，则表示当前栈中的顶点不是凸包的一个顶点，应该POP出
			while(direction<=0){
				// pop出节点
				nodeStack.pop();
				if (nodeStack.size()<3) {
					break;
				}
				// 更换匹配的线段 并重新计算direction值
				compareStartNode = nodeStack.get(nodeStack.size()-2);
				compareEndNode = nodeStack.get(nodeStack.size()-1);
				direction = SegmentCross.direction(compareStartNode, compareEndNode, nodeList.get(i));
			}
			// 将当前节点插入，并且加入到已经匹配的节点中，并重新设置匹配的线段
			nodeStack.push(nodeList.get(i));
			alreadyLoadNodeList.add(nodeList.get(i));
			compareStartNode = nodeStack.get(nodeStack.size()-2);
			compareEndNode = nodeStack.get(nodeStack.size()-1);
		}
		
		// 重新计算
		while(nodeStack!=null&&!nodeStack.empty()){
			convexNodeList.add(nodeStack.pop());
		}
		// 保持输入的所有节点完整
		nodeList = alreadyLoadNodeList;
		
		return convexNodeList;
	}
}
class ConvexSharpNode extends CoordsNode{
	private boolean isHead = false;
	private double crossValue;
	public ConvexSharpNode(double x, double y) {
		super(x, y);
	}
	public ConvexSharpNode(int x, int y){
		super(x,y);
	}

	public boolean isHead() {
		return isHead;
	}
	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}
	public double getCrossValue() {
		return crossValue;
	}
	public void setCrossValue(double crossValue) {
		this.crossValue = crossValue;
	}
	public void setCrossValue(ConvexSharpNode compareNode) {
		this.crossValue = super.getX()*compareNode.getY()-super.getY()*compareNode.getX();
	}
	public String toString(){
		return super.toString()+":"+crossValue;
	}
}