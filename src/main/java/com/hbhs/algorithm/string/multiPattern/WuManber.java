package com.hbhs.algorithm.string.multiPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hbhs.algorithm.HBHSAssert;

/**
 * Wu and Manber 算法<BR>
 * Wu-Manber算法用于多模匹配，采用哈希的方式以及BM算法中的坏字符规则达到快速检索、跳跃式步进的效果。<BR>
 * 预定义如下<BR>
 * 1 一般来说，WM算法中的每一个匹配串长度都应该大于5，否则性能会降低<BR>
 * 2 BM算法中的坏字符是一个个字符，而WM算法中的坏字符是一块一块的字符，而组的长度一般选取为2或者3<BR>
 * <B>Wu-Manber算法需要建立两张哈希表，以及一个前缀单链表。</B><BR>
 * 哈希表1为坏字符组滑动距离表；哈希表2为关联表，其中存放前缀单链表的头结点。这样可以顺藤摸瓜得到哈希值相同的所有模式串的信息。<BR>
 * 用例如下：<BR>
 * <B>预处理建表过程：</B>假设模式组如下
 * 假设现在要预处理的模式串集合为："abcdef","123456","ab3456","12cdef". 假设M_VALUE(最小模式串长度)为5.经过预处理后得到如下：<BR>
 * "ab","bc","cd","de",<BR>
 * "12","23","34","45",<BR>
 * "ab","b3","34","45",<BR>
 * "12","2c","cd","de".<BR>
 * 按照从右到左的顺序可以得到 每个字符块的未匹配时候所需要移动的安全距离为多少，合并后如下：<BR>
 * ab, bc, cd, de, 12, 23, 34, 45, b3, 2c<br>
 * 3,  2,  1,  0,  3,  2,  1,  0,  2,  2<bR>
 * 这表中已经建立了所有的字符快所需要移动的步数，如果值为0，表示这个字符快已经匹配了，说明可能找到了匹配块<BR>
 * 我们需要用一个单链表用来保存所有的匹配串，该匹配串和当前上述后缀为0的块关联，用于保存某个块为'XY'的后缀传中匹配的模式串链表<BR>
 * 这个链表结构有助于我们验证和存储相关的匹配信息<BR>
 * <B>Wu-Manber算法匹配过程</B><br>
 * 1 查shift哈希表进行目标串的移动。<br>
 * 2 如果shift哈希表对应项为0，则根据关联哈希表得到前缀单链表头结点。<br>
 * 3 遍历前缀单链表，对于前缀相同的模式串进行完整匹配检查，得到是否匹配的结果。<br>
 * 
 * @author walter.xu
 *
 */
public class WuManber {

	/**
	 * Wu-Manber算法
	 * @param text 带匹配的字符串
	 * @param patternArray 模式串数组
	 * @return 返回的模式传以及对应的位置列表
	 */
	public static Map<String, List<Integer>> wm(String text, String[] patternArray){
		// 获取最少的模式串长度
		int minLength = getMinLengthInPattern(patternArray);
		// 验证匹配串中的最小长度，其不能少于2
		HBHSAssert.isTrue(minLength<2, "最小长度不能少于2");
		// 建立SHIFT映射表，包括模式串前缀单链表结构
		Map<String, ShiftTable> shiftMap = buildSHIFTMap(patternArray, minLength);
		// 匹配字符串
		matchText(text, minLength, shiftMap);
		// 返回结果集合
		Map<String, List<Integer>> resultMap = buildResultMap(shiftMap);
		return resultMap;
	}
	/**
	 * 建立结果结合，从各个保存的前缀链表节点中好到所有的匹配结果集合
	 * @param map
	 * @return
	 */
	private static Map<String, List<Integer>> buildResultMap(Map<String, ShiftTable> map){
		Map<String, List<Integer>> resultMap = new HashMap<String, List<Integer>>();
		// 迭代所有的ShiftTable节点
		Iterator<ShiftTable> shiftIterator = map.values().iterator();
		while(shiftIterator.hasNext()){
			ShiftTable shift = shiftIterator.next();
			// 依次迭代
			PrefixLinkNode currentNode = shift.getNode();
			while(currentNode!=null){
				resultMap.put(currentNode.getPattern(), currentNode.getPositionList());
				currentNode = currentNode.getNextNode();
			}
		}
		return resultMap;
	}
	/**
	 * 匹配字符串
	 * @param text 带匹配字符串串
	 * @param minLength 模式串数组最小长度
	 * @param shiftMap SHIFT映射表结构
	 */
	private static void matchText(String text, int minLength, Map<String, ShiftTable> shiftMap){
		// 获取最长长度
		int maxLength = text.length();
		// 当前匹配位置
		int alignAt = minLength;
		// 仅当当前匹配位置在可匹配范围内时执行匹配规则
		while(alignAt <= maxLength){
			// 获取到匹配位置的最后两位
			String tailKey = text.substring(alignAt-2, alignAt);
			// 如果在映射表中没有找到，则说明该映射关键字是在模式串中没有的，那么移动模式串最小长度值minLength，否则按映射表中的值移动
			ShiftTable table = shiftMap.get(tailKey);
			// 设置默认的为minLength
			int steps = minLength;
			if (table!=null) {
				// 找到匹配关系时则按照匹配表映射
				steps = table.matchText(text, alignAt, minLength);
			}
			// 移动当前匹配关系
			alignAt += steps;
		}
	}
	
	private static Map<String, ShiftTable> buildSHIFTMap(String[] patternArray, int minLength){
		
		Map<String, ShiftTable> shiftMap = new HashMap<String, ShiftTable>();
		for(String pattern: patternArray){
			for (int i = minLength; i > 1; i--) {
				String subStr = pattern.substring(i-2, i);
				ShiftTable shiftTable = shiftMap.get(subStr);
				if (shiftTable==null) {
					shiftTable = new ShiftTable(minLength-i);
					shiftMap.put(subStr, shiftTable);					
				}
				shiftTable.addPattern(pattern);
			}
		}
		return shiftMap;
	}
	/**
	 * 获取所有模式串做中短串的长度
	 * @param patternArray
	 * @return
	 */
	private static int getMinLengthInPattern(String[] patternArray){
		int minLength = patternArray[0].length();
		for(String pattern: patternArray){
			int currentLength = pattern.length();
			if (currentLength<minLength) {
				minLength = currentLength;
			}
		}
		return minLength;
	}
	protected static void print(Map<String, ShiftTable> map) {
		Iterator<ShiftTable> mapIterator = map.values().iterator();
		while(mapIterator.hasNext()){
			System.out.println(mapIterator.next());
		}
	}
}
/**
 * 跳转表结构，用于保留某个节点的跳转长度，如果跳转长度为0(表示当前已经匹配)，同时需要保留匹配到的字符串的所有前缀节点列表
 * @author walter.xu
 *
 */
class ShiftTable {
	private int shift;
	private PrefixLinkNode node;
	
	public ShiftTable(int shift){
		this.shift = shift;
	}	
	/**
	 * 匹配关系
	 * @param text 带匹配的文本列表
	 * @param alignAt 当前位置
	 * @param minLength 模式串最小长度
	 * @return
	 */
	public int matchText(String text, int alignAt, int minLength){
		// shift为0时表示已经匹配了后两位
		if (shift==0) {
			// 匹配该链表下的所有字符串, 最终移动一位
			node.matchText(text, alignAt, minLength);
			return 1;
		}
		return shift;
	}
	/**
	 * 新增模式函数
	 * @param pattern
	 */
	public void addPattern(String pattern){
		if (this.shift == 0) {
			if (node==null) {
				node = new PrefixLinkNode(pattern);
			}else{
				PrefixLinkNode currentNode = node;
				while(currentNode.getNextNode()!=null){
					currentNode = currentNode.getNextNode();
				}
				currentNode.setNextNode(new PrefixLinkNode(pattern));
			}
		}
	}
	
	public int getShift() {
		return shift;
	}
	public void setShift(int shift) {
		this.shift = shift;
	}
	public PrefixLinkNode getNode() {
		return node;
	}
	public void setNode(PrefixLinkNode node) {
		this.node = node;
	}
	
	@Override
	public String toString(){
		return shift+": "+node;
	}
}

/**
 * 前缀单链表<BR>
 * 用于记录前缀的相关参数
 * @author walter.xu
 *
 */
class PrefixLinkNode{
	private String prefix;
	private String pattern;
	private PrefixLinkNode nextNode;
	private List<Integer> positionList = new ArrayList<Integer>();

	public PrefixLinkNode(String pattern){
		this.prefix = pattern.substring(0,2);
		this.pattern = pattern;
	}
	/**
	 * 匹配文本，验证当前模式节点以及对应的下一个子节点是否匹配，如果匹配则记录下对应的值
	 * @param text 待匹配文本
	 * @param alignAt 当前匹配位置
	 * @param minLength 最小长度
	 */
	public void matchText(String text, int alignAt, int minLength){
		// 获取到前缀 前缀=A[alignAt-minLength,alignAt-minLength+2]
		String prefix = text.substring(alignAt-minLength,alignAt-minLength+2);
		// 当前节点前缀与带匹配的前缀相等的情况下才做完全匹配，否则跳过不做完全匹配
		if (this.prefix.equals(prefix)) {
			//没有越界
			if (alignAt-minLength+pattern.length()<= text.length()) {
				// 获取到带匹配串中的创建字符串与当前节点模式串是否相等，如果相等 则记录下当前匹配值
				String matchStr = text.substring(alignAt-minLength, alignAt-minLength+pattern.length());
				if (matchStr.equals(pattern)) {
					positionList.add(alignAt-minLength);
				}
			}
			
		}
		// 如果下一个节点部位空，则继续校验匹配
		if (this.nextNode!=null) {
			this.nextNode.matchText(text, alignAt, minLength);
		}
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public PrefixLinkNode getNextNode() {
		return nextNode;
	}
	public void setNextNode(PrefixLinkNode nextNode) {
		this.nextNode = nextNode;
	}
	public List<Integer> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<Integer> positionList) {
		this.positionList = positionList;
	}
	@Override
	public String toString(){
		return "NODE{"+pattern+", "+Arrays.toString(positionList.toArray())+", "+nextNode+"}";
	}
}
