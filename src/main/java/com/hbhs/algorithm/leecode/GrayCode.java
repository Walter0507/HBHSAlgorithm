package com.hbhs.algorithm.leecode;

import java.util.ArrayList;
import java.util.List;
/**
 * <b>二进制码→格雷码（编码）：</b><br>
 * <br>此方法从对应的n位二进制码字中直接得到n位格雷码码字，步骤如下：
 * <bR>对n位二进制的码字，从右到左，以0到n-1编号
 * <br>如果二进制码字的第i位和i+1位相同，则对应的格雷码的第i位为0，否则为1（当i+1=n时，二进制码字的第n位被认为是0，即第n-1位不变）
 * <br>G(i)=B(i)^B(i+1)
 * <br>
 * <br>
 * <b>格雷码→二进制码（解码）：</b><br>
 * <br>从左边第二位起，将每位与左边一位解码后的值异或，作为该位解码后的值（最左边一位依然不变）。依次异或，直到最低位。依次异或转换后的值（二进制数）就是格雷码转换后二进制码的值。
 * <br>公式表示：B(i)=G(i)^B(i+1)（G：格雷码，B：二进制码）
 * <br>原码：p[n:0]；格雷码：c[n:0](n∈N）；编码：c=G(p）；解码：p=F(c）；
 * <br>
 * <br>如果采集器器采到了格雷码：1010
 * <br>就要将它变为自然二进制：
 * <br>0 与第四位 1 进行异或结果为 1
 * <br>上面结果1与第三位0异或结果为 1
 * <br>上面结果1与第二位1异或结果为 0
 * <br>上面结果0与第一位0异或结果为 0
 * <br>因此最终结果为：1100 这就是二进制码即十进制 12
 * <br>
 * @author walter.xu
 *
 */
public class GrayCode {

	public static void main(String[] args) {
		solution(0);
	}
	
	
	
	
	public static List<Integer> solution(int n){
		if (n < 0) {
			return null;
		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 1<<n; i++) {
			result.add(i^i>>1);
			System.out.println(formatGrayCode(i^i>>1));
		}
		return result;
	}
	
	private static String formatGrayCode(int n){
		String str = "";
		while(n!=0){
			str = n%2+str;
			n = n/2;
		}
		return str;
	}
}
