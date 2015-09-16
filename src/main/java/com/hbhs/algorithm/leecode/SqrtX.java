package com.hbhs.algorithm.leecode;

/**
 * <b>Sqrt(x)</b><br>
 * <br>Implement int sqrt(int x).
 * <br>Compute and return the square root of x.
 * @author walter.xu
 *
 */
public class SqrtX {
	public static void main(String[] args) {
//		System.out.println(solution(1));
//		System.out.println(solution(3));
//		System.out.println(solution(4));
		System.out.println(solution(10));
		System.out.println(solution(36));
	}
	
	public static int solution(int x){
		if (x<=0) {
			return 0;
		}
		if (x==1) {
			return 1;
		}
		int start = 0, end = x, result = x/2;
		while(result != x/result){
			if (result<x/result&&(result+1)>x/(result+1)) {
				break;
			}
			if (result<x/result) {
				start = result;
				result = (result+end)/2;
			}else{
				end = result;
				result = (start+result)/2;
			}
		}
		return result;
	}
}
