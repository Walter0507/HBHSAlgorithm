package com.hbhs.algorithm.geometry;
/**
 * <b>线段</b><BR>
 * <B>叉积</B>：叉积是关于线段算法的中心。关于每一个向量P1，P2，可以把叉积P1xP2看作由点(0, 0), P1, P2以及(P1+P2)=(x1+x2, y1+y2)所形成
 * 平行四边形，另外一种等价方式是使用矩阵来定义叉积，使得P1xP2=x1*y2-x2*y1=-P2xP1<BR>
 * <ul>
 * <li>如果P1xP2为正数，即相对原点(0, 0)来说，P1在P2的顺时针方向</li>
 * <li>如果P1xP2为负数，即相对原点(0, 0)来说，P1在P2的逆时针方向</li>
 * <li>如果P1xP2为0，即相对原点(0, 0)来说，两个向量是共线的，即要么是同一个方向，要么是完全相反的的方向</li>
 * </ul>
 * 为了确定有向线段P0P1是否在有向线段P0P2的顺时针方向，只需要把P0作为原点即可。亦即
 * (P0P1)x(P0P2)=[P1-P0, P2-P0]=(x1-x0)*(y2-y0)-(x2-x0)*(y1-y0).<br>
 * <B>确定连续线段是向左转还是向右转</B><BR>
 * 针对线段P0P1和P1P2是向左转还是右转，亦即寻找出角P0P1P2的转向。只需要查询其叉积(P2-P0)*(P1-P0)即可<br>
 * <ul>
 * <li>如果叉积为负，则P0P2在P0P1的逆时针方向</li>
 * <li>如果叉积为正，则P0P2在P0P1的顺时针方向</li>
 * <li>如果叉积为0，则P0P2在P0P1共线</li>
 * </ul>
 * <B>确定两个线段相交</B><BR>
 * 为了确定两个线段是否相交，需要检查每个线段是否跨越了包含另一条线段的直线，两个线段相交，当且仅当下面两个条件中任一个成立，或者同时成立<BR>
 * <UL>
 * <LI>每个线段都跨越包含了另一线段的直线</LI>
 * <LI>一个线段的某一个端点在另外一条直线上(该情况源自边界情况)</LI>
 * </UL>
 * 伪代码为：<BR>
 * <code>
 * SEGMENTS-INTERSECT(p1,p2,p3,p4)<br>
 *   d1 <- DIRECTION(p3,p4,p1);<br>
 *   d2 <- DIRECTION(p3,p4,p2);<br>
 *   d3 <- DIRECTION(p1,p2,p3);<br>
 *   d4 <- DIRECTION(p1,p2,p4);<br>
 *   if((d1>0 and d2<0)or(d1<0 and d2>0))and((d3>0 and d4<0)or(d3<0 and d4>0))<br>
 *     return true;<br>
 *   elseif d1==0 and ON-SEGMENT(p3,p4,p1)<br>
 *     return true;<br>
 *   elseif d2==0 and ON-SEGMENT(p3,p4,p2)<br>
 *     return true;<br>
 *   elseif d3==0 and ON-SEGMENT(p1,p2,p3)<br>
 *     return true;<br>
 *   elseif d4==0 and ON-SEGMENT(p1,p2,p4)<br>
 *     return true;<br>
 *   else<br>
 *     return false;<br>
 *     <br>
 * DIRECTION(pi,pj,pk)<br>
 *   return (pk-pi)*(pj-pi);<br>
 * <br>
 * ON-SEGMENT(pi,pj,pk)<br>
 *   if min(xi,xj)<=xk<=max(xi,xj)and min(yi,yj)<=yk<=max(yi,yk)<br>
 *     return true<br>
 *   else <br>
 *     return false<br>
 * </code>
 * 
 * @author walter.xu
 *
 */
public class SegmentCross {
	
	/**
	 * 查找两个线段的交点
	 * @param segmentOne
	 * @param segmentTwo
	 * @return
	 */
	public static CoordsNode findCrossNode(Segment segmentOne, Segment segmentTwo){
		boolean isCrossed = isCrossed(segmentOne, segmentTwo);
		if (!isCrossed) {
			return null;
		}
		if (segmentOne.isVertical()) {
			double aOfSegmentTwo = getAofSegment(segmentTwo);
			double bOfSegmentTwo = getBofSegment(segmentTwo);
			return new CoordsNode(segmentOne.getStartNode().getX(), aOfSegmentTwo*segmentOne.getStartNode().getX()+bOfSegmentTwo);
		}
		if (segmentTwo.isVertical()) {
			double aOfSegmentOne = getAofSegment(segmentOne);
			double bOfSegmentOne = getBofSegment(segmentOne);
			return new CoordsNode(segmentTwo.getStartNode().getX(), aOfSegmentOne*segmentTwo.getStartNode().getX()+bOfSegmentOne);
		}
		double aOfSegmentOne = getAofSegment(segmentOne);
		double bOfSegmentOne = getBofSegment(segmentOne);
		double aOfSegmentTwo = getAofSegment(segmentTwo);
		double bOfSegmentTwo = getBofSegment(segmentTwo);
		if (aOfSegmentOne == aOfSegmentTwo) {
			return null;
		}
		double crossNodeX = (bOfSegmentTwo-bOfSegmentOne)/(aOfSegmentOne-aOfSegmentTwo);
		double crossNodeY = (aOfSegmentOne*bOfSegmentTwo-aOfSegmentTwo*bOfSegmentOne)/(aOfSegmentOne-aOfSegmentTwo);
		return new CoordsNode(crossNodeX, crossNodeY);
	}
	private static double getAofSegment(Segment segment){
		return (segment.getStartNode().getY()-segment.getEndNode().getY())/(segment.getStartNode().getX()-segment.getEndNode().getX());
	}
	
	private static double getBofSegment(Segment segment){
		return (segment.getStartNode().getX()*segment.getEndNode().getY()-segment.getEndNode().getX()*segment.getStartNode().getY())
				/(segment.getStartNode().getX()-segment.getEndNode().getX());
	}
	
	/**
	 * 两条线段是否交叉
	 * @param segmentOne 线段1
	 * @param segmentTwo 线段2
	 * @return
	 */
	public static boolean isCrossed(Segment segmentOne, Segment segmentTwo) {
		return isCrossed(new double[]{segmentOne.getStartNode().getX(),segmentOne.getStartNode().getY()}, 
				new double[]{segmentOne.getEndNode().getX(),segmentOne.getEndNode().getY()}, 
				new double[]{segmentTwo.getStartNode().getX(),segmentTwo.getStartNode().getY()}, 
				new double[]{segmentTwo.getEndNode().getX(),segmentTwo.getEndNode().getY()});
	}
	/**
	 * 两条线段是否交叉
	 * @param lineOneNodeOne 线段1开始节点
	 * @param lineOneNodeTwo 线段1截止节点
	 * @param lineTwoNodeOne 线段2开始节点
	 * @param lineTwoNodeTwo 线段2截止节点
	 * @return
	 */
	public static boolean isCrossed(double[] lineOneNodeOne,
			double[] lineOneNodeTwo, double[] lineTwoNodeOne, double[] lineTwoNodeTwo) {
		// 针对二维空间，如果长度不小于2，直接返回
		if (lineOneNodeOne == null || lineOneNodeTwo == null
				|| lineTwoNodeOne == null || lineTwoNodeTwo == null
				|| lineOneNodeOne.length <2 || lineOneNodeTwo.length <2
				|| lineTwoNodeOne.length <2 || lineTwoNodeTwo.length <2) {
			return false;
		}
		// 分别查询节点在各自线段的方向
		double direction1 = direction(lineOneNodeOne, lineOneNodeTwo, lineTwoNodeOne);
		double direction2 = direction(lineOneNodeOne, lineOneNodeTwo, lineTwoNodeTwo);
		double direction3 = direction(lineTwoNodeOne, lineTwoNodeTwo, lineOneNodeOne);
		double direction4 = direction(lineTwoNodeOne, lineTwoNodeTwo, lineOneNodeTwo);
		// 如果direction1和direction2为两个方向，同时direction3和direction4也为两个方向，那么表示两个线段相交
		// 如果不登陆，则分别验证各个节点是否在指定的线段上
		if (((direction1>0&&direction2<0)||(direction1<0&&direction2>0))
				&&((direction3>0&&direction4<0)||(direction3<0&&direction4>0))) {
			return true;
		}else if (direction1==0&&onSegment(lineOneNodeOne, lineOneNodeTwo, lineTwoNodeOne)) {
			return true;
		}else if (direction2==0&&onSegment(lineOneNodeOne, lineOneNodeTwo, lineTwoNodeTwo)) {
			return true;
		}else if (direction3==0&&onSegment(lineTwoNodeOne, lineTwoNodeTwo, lineOneNodeOne)) {
			return true;
		}else if (direction4==0&&onSegment(lineTwoNodeOne, lineTwoNodeTwo, lineOneNodeTwo)) {
			return true;
		}
		return false;
	}
	/**
	 * 查询节点<CODE>compareNode</CODE>在线段<CODE>[lineNodeOne,lineNodeOne]</CODE>的叉积，定义其顺时针或者逆时针方向
	 * @param lineNodeOne 线段开始节点
	 * @param lineNodeTwo 线段截止节点
	 * @param compareNode 待匹配的节点
	 * @return 0 表示节点在线段上，正数表示顺时针方向，负数表示逆时针方向
	 */
	public static double direction(double[] lineNodeOne, double[] lineNodeTwo, double[] compareNode){
		return (compareNode[0]-lineNodeOne[0])*(lineNodeTwo[1]-lineNodeOne[1])-
				(lineNodeTwo[0]-lineNodeOne[0])*(compareNode[1]-lineNodeOne[1]);
	}
	/**
	 * 查询节点<CODE>compareNode</CODE>在线段<CODE>[lineNodeOne,lineNodeOne]</CODE>的叉积，定义其顺时针或者逆时针方向
	 * @param lineStartNode
	 * @param lineEndNode
	 * @param compareNode
	 * @return 0 表示节点在线段上，正数表示顺时针方向，负数表示逆时针方向
	 */
	public static double direction(CoordsNode lineStartNode, CoordsNode lineEndNode, CoordsNode compareNode){
		return (compareNode.getX()-lineStartNode.getX())*(lineEndNode.getY()-lineStartNode.getY())-
				(lineEndNode.getX()-lineStartNode.getX())*(compareNode.getY()-lineStartNode.getY());
	}
	
	/**
	 * 验证是否在线段上。前提条件，必须该节点的direction方法为0
	 * @param lineNodeOne 线段开始节点
	 * @param lineNodeTwo 线段截止节点
	 * @param compareNode 待匹配的节点
	 * @return
	 */
	private static boolean onSegment(double[] lineNodeOne, double[] lineNodeTwo, double[] compareNode){
		if (compareNode[0]>=min(lineNodeOne[0],lineNodeTwo[0])&&
				compareNode[0]<=max(lineNodeOne[0],lineNodeTwo[0])&&
				compareNode[1]>=min(lineNodeOne[1],lineNodeTwo[1])&&
				compareNode[1]<=max(lineNodeOne[1],lineNodeTwo[1])) {
			return true;
		}
		return false;
	}
	private static double min(double arg0, double arg1){
		return arg0<arg1?arg0:arg1;
	}
	private static double max(double arg0, double arg1){
		return arg0>arg1?arg0:arg1;
	}
}