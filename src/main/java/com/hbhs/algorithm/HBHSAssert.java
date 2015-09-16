package com.hbhs.algorithm;

public class HBHSAssert {
	public static void isTrue(boolean isTure, String msg){
		if (isTure) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public static void isNull(Object o, String msg){
		if (o==null||"".equals(o.toString())) {
			throw new IllegalArgumentException(msg);
		}
	}
}
