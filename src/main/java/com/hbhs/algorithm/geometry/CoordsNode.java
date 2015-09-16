package com.hbhs.algorithm.geometry;

import java.text.DecimalFormat;

/**
 * 坐标点
 * @author walter.xu
 *
 */
public class CoordsNode {
	private static final DecimalFormat df = new DecimalFormat("###0.00");
	private double x;
	private double y;
	public CoordsNode(int x, int y){
		this.x = x;
		this.y = y;
	}
	public CoordsNode(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString(){
		return "("+df.format(x)+","+df.format(y)+")";
	}
}
