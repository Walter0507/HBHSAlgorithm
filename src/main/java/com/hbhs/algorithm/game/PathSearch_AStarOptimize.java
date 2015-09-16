package com.hbhs.algorithm.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbhs.algorithm.geometry.ConvexSharp;
import com.hbhs.algorithm.geometry.CoordsNode;
import com.hbhs.algorithm.geometry.Segment;
import com.hbhs.algorithm.geometry.SegmentCross;


/**
 * A*寻路算法<BR>
 * 优化算法
 * TODO 未完成
 * @author walter.xu
 *
 */
public class PathSearch_AStarOptimize {
	protected double x;
	protected double y;
	// 已经阻塞的多边形列表
	private List<AStarConvexSharp> blockConvexList = new ArrayList<AStarConvexSharp>();
	private List<AStarSegment> blockSegmentList = new ArrayList<AStarSegment>();
	public List<AStarSegment> getBlockSegmentList(){
		return blockSegmentList;
	}
	public PathSearch_AStarOptimize(double x, double y){
		this.x = x;
		this.y = y;
	}
	public List<?> A_Star(Node startNode, Node rearchNode, boolean isDebug){
		// 创建一个搜索树
		AStartTreeNode treeNode = new AStartTreeNode(startNode, rearchNode);
		// 已到达的节点列表
		Map<Integer, Node> reachedNodeMap = new HashMap<Integer, Node>();
		// 待匹配的节点列表
		Map<Integer, Node> standByNodeMap = new HashMap<Integer, Node>();
		// 添加起点节点 并将
		treeNode.addNode(startNode);
		// 循环调用，直到待匹配的节点为空或者已经匹配到位置
		while(!treeNode.isMatched()&&treeNode.getCompareNode()!=null){
			System.out.println("Find node: "+treeNode.getCompareNode());
			search(treeNode, treeNode.getReachNode(), standByNodeMap, reachedNodeMap);
		}
		Node result = treeNode.getCompareNode();
		System.out.println(result.getParentNode());
		System.out.println(treeNode.getCompareNode());
		return null;
	}

	private void search(AStartTreeNode treeNode, Node tempEndNode, 
			Map<Integer, Node> standByNodeMap, Map<Integer, Node> reachedNodeMap){
		// 获取待匹配节点与最终节点的
		Node tempStartNode = treeNode.getCompareNode();
		if (tempStartNode == null) {
			return ;
		}
		Segment nearestCrossSegment = this.findNearestCrossSegment(tempStartNode, tempEndNode);
		if (nearestCrossSegment!=null) {
			Node segmentStartNode = new Node(nearestCrossSegment.getStartNode().getX(), 
					nearestCrossSegment.getStartNode().getY(), treeNode.getCompareNode());
			Node segmentEndNode = new Node(nearestCrossSegment.getStartNode().getX(), 
					nearestCrossSegment.getStartNode().getY(), treeNode.getCompareNode());
			addNodeInTree(treeNode, segmentStartNode, tempStartNode, tempEndNode, 
					standByNodeMap, reachedNodeMap);
			addNodeInTree(treeNode, segmentEndNode, tempStartNode, tempEndNode, 
					standByNodeMap, reachedNodeMap);
		}
		// 完成后将该匹配节点添加到已经匹配的节点列表中
		reachedNodeMap.put(tempStartNode.hashCode(), tempStartNode);
		// 需要修改组织结构树，将已经匹配的节点移除，并重新构造最小堆
		treeNode.removeNode();
		treeNode.buildMinHeap();
	}
	
	private void addNodeInTree(AStartTreeNode treeNode, Node tempNode, Node tempStartNode, Node tempEndNode,
			Map<Integer, Node> standByNodeMap, Map<Integer, Node> reachedNodeMap){
		if (reachedNodeMap.get(tempNode.hashCode())==null) {
			if (standByNodeMap.get(tempNode.hashCode())!=null) {
				Node standByTargetNode = standByNodeMap.get(tempNode.hashCode());
				if (standByTargetNode.getFn() < tempNode.getFn()) {
					tempNode.setParentNode(standByTargetNode.getParentNode());
					treeNode.addNode(tempNode, tempStartNode, tempEndNode);
				}else{
					standByNodeMap.put(standByNodeMap.hashCode(), tempNode);
					treeNode.addNode(tempNode, tempStartNode, tempEndNode);
				}
			}
		}
	}
	
	private AStarSegment findNearestCrossSegment(Node startNode, Node endNode){
		if (startNode == null || endNode == null) {
			return null;
		}
		Segment currentSegment = new Segment(startNode, endNode);
		List<AStarSegment> temCrossSegmentList = new ArrayList<AStarSegment>();
		for (int i = 0; i < blockSegmentList.size(); i++) {
			CoordsNode crossNode = SegmentCross.findCrossNode(currentSegment, blockSegmentList.get(i));
			if (crossNode!=null) {
				double xDiff = startNode.getX() - crossNode.getX();
				double yDiff = startNode.getY() - crossNode.getY();
				blockSegmentList.get(i).setLength(xDiff*xDiff+yDiff*yDiff);
				temCrossSegmentList.add(blockSegmentList.get(i));
			}
		}
		if (temCrossSegmentList == null||temCrossSegmentList.size()==0) {
			return null;
		}
		Collections.sort(temCrossSegmentList, new Comparator<AStarSegment>() {
			public int compare(AStarSegment arg0, AStarSegment arg1) {
				return (int)((arg0.getLength() - arg1.getLength())*100);
			}
			
		});
		return temCrossSegmentList.get(0);
	}
	
	/**
	 * 
	 * @param segment
	 */
	public void addBlockSegment(Segment segment){
		if (segment==null) {
			return;
		}
		List<AStarConvexSharp> temCrossSharpList = new ArrayList<AStarConvexSharp>();
		// 迭代每一个凸多边形，并验证是否已经交叉了
		for (int i = 0; i < blockConvexList.size(); ) {
			List<Segment> currentSegmentList = blockConvexList.get(i).getConvexSegments();
			boolean isCross = false;
			for(Segment currentSeg: currentSegmentList){
				isCross = SegmentCross.isCrossed(segment, currentSeg);
				if (isCross) {
					break;
				}
			}
			// 如果已经交叉，那么将这个多边形添加到指定的临时交叉多边形列表中，做后续处理
			if (isCross) {
				temCrossSharpList.add(blockConvexList.get(i));
				blockConvexList.remove(i);
			} else {
				i++;
			}
		}
		AStarConvexSharp newConvex = trans2One(segment, temCrossSharpList);
		newConvex.grahamScan();
		blockConvexList.add(newConvex);
		
		buildBlockedSegmentList();
	}
	/**
	 * 
	 * @param convex
	 */
	public void addBlockConvex(AStarConvexSharp convex){
		// 点不算
		if (convex==null||convex.getConvexNodes()==null||convex.getConvexNodes().size()<2) {
			List<Segment> convexSegmentList = convex.getConvexSegments();
			List<AStarConvexSharp> temCrossSharpList = new ArrayList<AStarConvexSharp>();
			for(Segment segment: convexSegmentList){
				// 迭代每一个凸多边形，并验证是否已经交叉了
				for (int i = 0; i < blockConvexList.size(); ) {
					List<Segment> currentSegmentList = blockConvexList.get(i).getConvexSegments();
					boolean isCross = false;
					for(Segment currentSeg: currentSegmentList){
						isCross = SegmentCross.isCrossed(segment, currentSeg);
						if (isCross) {
							break;
						}
					}
					// 如果已经交叉，那么将这个多边形添加到指定的临时交叉多边形列表中，做后续处理
					if (isCross) {
						temCrossSharpList.add(blockConvexList.get(i));
						blockConvexList.remove(i);
					} else {
						i++;
					}
				}
			}
			
			AStarConvexSharp newConvex = trans2One(convexSegmentList, temCrossSharpList);
			blockConvexList.add(newConvex);
			
			buildBlockedSegmentList();
		}
	}
	/**
	 * 构造阻断的节点列表，从list转换成HASHMAP，便于查询
	 * @param blockedNodeList
	 * @return
	 */
	private void buildBlockedSegmentList(){
		blockSegmentList.clear();
		if (blockConvexList==null||blockConvexList.size()==0) {
			return ;
		}	
		for(AStarConvexSharp convex: blockConvexList){
			convex.grahamScan();
			List<Segment> segList = convex.getConvexSegments();
			for(Segment segment: segList){
				blockSegmentList.add(new AStarSegment(segment));
			}
			
		}
	}
	/**
	 * 
	 * @param segment
	 * @param convexList
	 * @return
	 */
	private AStarConvexSharp trans2One(Segment segment, List<AStarConvexSharp> convexList){
		AStarConvexSharp convex = new AStarConvexSharp();
		convex.insertSegment(segment);
		if (convexList!=null&&convexList.size()>0) {
			for(AStarConvexSharp target: convexList){
				convex.insertConvex(target);
			}
		}
		return convex;
	}
	/**
	 * 
	 * @param segmentList
	 * @param convexList
	 * @return
	 */
	private AStarConvexSharp trans2One(List<Segment> segmentList, List<AStarConvexSharp> convexList){
		AStarConvexSharp convex = new AStarConvexSharp();
		if (segmentList!=null&&segmentList.size()>0) {
			for(Segment segment: segmentList){
				convex.insertSegment(segment);
			}
		}
		
		if (convexList!=null&&convexList.size()>0) {
			for(AStarConvexSharp target: convexList){
				convex.insertConvex(target);
			}
		}
		return convex;
	}
	
}
/**
 * 凸多边形扩展类，用于扩展获取某个凸多边形所有的线段字段
 * @author walter.xu
 *
 */
class AStarConvexSharp extends ConvexSharp{
	// 凸多边形的所有线段
	private List<Segment> segmentList = new ArrayList<Segment>();
	
	@Override
	public List<CoordsNode> grahamScan(){
		super.grahamScan();
		this.segmentList = super.getConvexSegments();
		return this.getConvexNodes();
	}
	@Override
	public List<Segment> getConvexSegments(){
		return this.segmentList;
	}
}
/**
 * 扩展的线段
 * @author walter.xu
 *
 */
class AStarSegment extends Segment{
	private double length;
	public AStarSegment(Segment seg){
		this(seg.getStartNode(), seg.getEndNode());
	}

	public AStarSegment(CoordsNode startNode, CoordsNode endNode) {
		super(startNode, endNode);
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
}

class AStartTreeNode extends TreeNode{
	public AStartTreeNode(Node startNode, Node reachNode) {
		super(startNode, reachNode);
		// 初始化开始节点
		startNode.setGn(0);
		startNode.setHn(cacleNodeDistance(startNode, reachNode));
		startNode.setFn(startNode.getGn()+startNode.getHn());
		reachNode.setGn(0);
		reachNode.setHn(0);
		reachNode.setFn(0);
	}
	
	public void addNode(Node node, Node startNode, Node endNode){
		if (node!=null) {
			node.setParentNode(startNode);
			caclGN(node, startNode, endNode);
			caclHN(node, startNode, endNode);
			caclFN(node);
		}
		
	}
	
	protected void caclGN(Node node, Node tempStartNode, Node tempEndNode){
		node.setGn(cacleNodeDistance(node, tempStartNode)+tempStartNode.getGn());
	}
	protected void caclHN(Node node, Node tempStartNode, Node tempEndNode){
		node.setGn(cacleNodeDistance(tempStartNode, tempEndNode)+tempEndNode.getHn());
	}
	protected void caclFN(Node node){
		node.setFn(node.getGn()+node.getHn());
	}
}
