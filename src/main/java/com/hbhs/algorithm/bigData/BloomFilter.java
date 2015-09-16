package com.hbhs.algorithm.bigData;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
/**
 * <B>Bloom 过滤器</B>
 * Bloom Filter是一种空间效率很高的随机数据结构，它的原理是，当一个元素被加入集合时，
 * 通过K个Hash函数将这个元素映射成一个位阵列（Bit array）中的K个点，把它们置为1。检索时，
 * 我们只要看看这些点是不是都是1就（大约）知道集合中有没有它了：如果这些点有任何一个0，则被检索元素一定不在；
 * 如果都是1，则被检索元素很可能在。<BR><BR>
 * 
 * 
 * 如 何根据输入元素个数n，确定位数组m的大小及hash函数个数。当hash函数个数k=(ln2)*(m/n)时错误率最小。
 * 在错误率不大于E的情况 下，m至少要等于n*lg(1/E)才能表示任意n个元素的集合。但m还应该更大些，
 * 因为还要保证bit数组里至少一半为0，则m应 该>=nlg(1/E)*lge 大概就是nlg(1/E)1.44倍(lg表示以2为底的对数)。 <BR><BR>
 * 
 * 哈希函数个数k、位数组大小m、加入的字符串数量n的关系可以参考参考文献1。该文献证明了对于给定的m、n，
 * 当 k = ln(2)* m/n 时出错的概率是最小的。<BR><BR>
 * 
 * 举个例子我们假设错误率为0.01，则此时m应大概是n的13倍。这样k大概是8个。
 * @author walter.xu
 *
 */
public class BloomFilter {
	// BIT SET 对象
	private BitSet bitSet = null;
	// 默认的BITSET 大小，为2^10;
	private static int DEFAULT_BIT_SIZE = 1<<10;
	// 默认的素数种子
	private static final int[] HASH_SEEDS = new int[]{5, 7, 11, 13, 31, 37, 61, 91};
	// 默认的hash类
	private List<SimpleHashing> hashMethodList = new ArrayList<SimpleHashing>();
	/**
	 * 构造函数
	 * @param totalSize
	 * @param filterSize
	 */
	public BloomFilter(int totalSize, int filterSize){
		// 计算误差最小情况下的，依据总数以及过滤器的个数，得出的需要计算的总的BIT数
		double value = totalSize*filterSize/Math.log10(2);
		// 非法参数验证
		if (totalSize<0||filterSize<0||filterSize>8||(value>(1<<31-1))) {
			throw new IllegalArgumentException("Not valid for parameter["+totalSize+", "+filterSize+"]");
		}
		// 如果当前BIT不够，则扩大
		while(DEFAULT_BIT_SIZE < value){
			DEFAULT_BIT_SIZE = (DEFAULT_BIT_SIZE << 1);
		}
		// 构造
		bitSet = new BitSet(DEFAULT_BIT_SIZE);
		// set hashing classes
		for (int i = 0; i < filterSize; i++) {
			hashMethodList.add(new SimpleHashing(DEFAULT_BIT_SIZE, HASH_SEEDS[i]));
		}
	}
	/**
	 * Set bitset object to true in hashing position.
	 * @param arg0
	 */
	public void add(String arg0){
		for (int i = 0; i < hashMethodList.size(); i++) {
			bitSet.set(hashMethodList.get(i).hash(arg0), true);
		}
	}
	/**
	 * 
	 * @param arg0
	 * @return
	 */
	public boolean match(String arg0){
		if (arg0==null||"".equals(arg0)) {
			return false;
		}
		boolean isExist = true;
		for (SimpleHashing hash: hashMethodList) {
			isExist = isExist && bitSet.get(hash.hash(arg0));
		}
		return isExist;
	}
}
/**
 * 
 * @author walter.xu
 *
 */
class SimpleHashing{
	private int cap;
	private int seed;
	
	public SimpleHashing(int cap, int seed){
		this.cap = cap;
		this.seed = seed;
	}
	
	public int hash(String arg0){
		if (arg0==null||"".equals(arg0)) {
			return 0;
		}
		int result = 0;
		for (int i = 0; i < arg0.length(); i++) {
			result = result*seed + arg0.charAt(i);
		}
		return doCap(result);
	}
	public int hash(int arg0){
		return doCap(seed*arg0);
	}
	public int hash(long arg0){
		return doCap((int)(seed*arg0));
	}
	private int doCap(int result){
		if (result > cap) {
			result %= cap;
		}
		return (cap-1)&result;
	}
}
