package com.hbhs.algorithm.geometry;

/**
 * 线段
 * @author walter.xu
 *
 */
public class Segment {
	private String name;
	private CoordsNode startNode;       //  开始节点
	private CoordsNode endNode;         //  截止节点  
	private boolean isVertical = false; //  是否垂直
	private double cross;               // 叉积
	public Segment(CoordsNode startNode,CoordsNode endNode){
		this(startNode, endNode, true);
	}
	public Segment(CoordsNode startNode,CoordsNode endNode, boolean xLessFirst){
		if (startNode==null||endNode==null) {
			throw new IllegalArgumentException("Segment's start or end node should not be null.");
		}
		if (startNode.equals(endNode)) {
			throw new IllegalArgumentException("Segment's start and end node should not be the same.");
		}
		if (xLessFirst) {
			if (startNode.getX()> endNode.getX()) {
				this.startNode = endNode;
				this.endNode = startNode;
			}else{
				this.startNode = startNode;
				this.endNode = endNode;
			}
		}else{
			this.startNode = startNode;
			this.endNode = endNode;
		}
		if (startNode.getX() == endNode.getX()) {
			this.isVertical = true;
		}
		this.name = "[("+this.startNode.getX()+","+this.startNode.getY()+")("+this.getEndNode().getX()+","+this.endNode.getY()+")]";
		cross = this.startNode.getX()*this.endNode.getY() - this.endNode.getX()*this.startNode.getY();
	}
	public CoordsNode getStartNode() {
		return startNode;
	}
	public void setStartNode(CoordsNode startNode) {
		this.startNode = startNode;
	}
	public CoordsNode getEndNode() {
		return endNode;
	}
	public void setEndNode(CoordsNode endNode) {
		this.endNode = endNode;
	}
	public boolean isVertical() {
		return isVertical;
	}
	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCross() {
		return cross;
	}
	public void setCross(double cross) {
		this.cross = cross;
	}
	@Override
	public String toString(){
		return this.name;
	}
	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof Segment)) {
			return false;
		}
		return this.name.equals(((Segment)arg0).getName());
	}
}
