package com.hbhs.algorithm.string.multiPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * AC算法<br>
 * 要思想是，把字符串库放到一个trie树里面，然后有内容要匹配时，只要一次遍历，就可以将所有字符串匹配好了<br>
 * 经典的AC算法由三部分构成，goto表，fail表和output表，<bR>
 * goto表是由模式集合P中的所有模式构成的状态转移自动机，<br>
 * fail表就是当我们处在状态机的某个状态D[p]时，此时的输入字符c使得D[p][c]=0，那么我们应该转移到状态机的哪个位置来继续进行<br>
 * output表定义那些情况下的字符是有效的，他包括前缀，后缀以及包含关系
 * @author walter.xu
 *
 */
public class AC {
	/**
	 * AC算法多字符串匹配
	 * @param text 待匹配的字符串
	 * @param patternArray 多个模式串数组
	 * @return 返回模式串数组对应的匹配位置列表
	 */
	public static Map<String, List<Integer>> ac(String text, String[] patternArray){
		// 依据模式串数组创建一个Trie树
		TrieNode root = buildRootNode(patternArray);
		// 将text作为输入生成自动机，直到结束
		TrieNode nextNode = root;
		char[] charArray = text.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			nextNode = nextNode.gotoNextNode(charArray, i);
		}
		// 根据最终的nextNode找到整个树的根节点
		root = getRootNode(nextNode);
		// 生成返回的结果
		Map<String, List<Integer>> mapResult = genResultFromNode(root, patternArray);
		return mapResult;
	}
	/**
	 * 从节点中获取到最终的结果
	 * @param root
	 * @param patternArray
	 * @return
	 */
	private static Map<String, List<Integer>> genResultFromNode(TrieNode root, String[] patternArray){
		Map<String, List<Integer>> resultMap = new HashMap<String, List<Integer>>();
		// 初始化
		setNodeToMap(root, patternArray, resultMap);
		
		return resultMap;
	}
	/**
	 * 设置当前节点到map中
	 * @param currentNode 当前待查询节点
	 * @param patternArray 模式串数组
	 * @param resultMap 结果集合
	 */
	private static void setNodeToMap(TrieNode currentNode, String[] patternArray, Map<String, List<Integer>> resultMap){
		if (currentNode!=null) {
			// 仅当当前节点存在，切该节点已经匹配到匹配串的时候才做处理，1表示有匹配，0表示没有匹配
			if (currentNode.getStatus()==1) {
				// 获取到匹配串的所有串以及位置列表
				List<Integer> patternList = currentNode.getMatchPatternList();
				List<Integer> positionList = currentNode.getMatchPositionList();
				// 仅当两者均不为空的时候做处理
				if (patternList!=null&&patternList.size()>0&&positionList!=null&&positionList.size()>0) {
					// 以此迭代每个串
					for(Integer pattern: patternList){
						if (pattern!=null&&pattern<patternArray.length) {
							// 获取到结果集合中该串的位置结合，如果为空，则新建，否则使用已经有的集合
							List<Integer> result = resultMap.get(patternArray[pattern]);
							if (result == null) {
								result = new ArrayList<Integer>();
								resultMap.put(patternArray[pattern], result);
							}
							// 依次将节点设置进去，注意记录的节点为匹配的截止节点，需根据实际情况计算出其开始节点
							for(Integer position: positionList){
								result.add(position - patternArray[pattern].length()+1);
							}
						}
					}
				}
				
			}
			// 依次设置当前节点的所有子节点
			Iterator<Character> keyIterator = currentNode.getSubNodeList().keySet().iterator();
			while(keyIterator.hasNext()){
				TrieNode subNode = currentNode.getSubNodeList().get(keyIterator.next());
				setNodeToMap(subNode, patternArray, resultMap);
				
			}
		}
	}
	/**
	 * 依据当前节点找到根节点，根节点的条件为：其当前字符为空
	 * @param currentNode
	 * @return
	 */
	private static TrieNode getRootNode(TrieNode currentNode){
		if (currentNode.getCurrentChar()==null) {
			return currentNode;
		}else{
			return getRootNode(currentNode.getParentNode());
		}
	}
	/**
	 * 根据多个模式串建立一个Trie树
	 * @param patternArray
	 * @return
	 */
	private static TrieNode buildRootNode(String[] patternArray){
		TrieNode root = new TrieNode();
		// 新增每一个串
		for(String pattern: patternArray){
			root.addSubString(pattern);
		}
		// 设置每个串中包含的模式串列表 
		buildMatchStr(root, patternArray);
		// 设置匹配失败时跳转的节点-全部为根节点
		buildFailedNode(root);
		return root;
	}
	/**
	 * 设置匹配串的结果列表
	 * @param root
	 * @param patternArray
	 */
	private static void buildMatchStr(TrieNode root, String[] patternArray){
		if (root!=null) {
			// 根据当前节点 验证每一个模式串，如果当前匹配的节点是以模式串为结尾的，那么该模式串就匹配
			for (int i = 0; i < patternArray.length; i++) {
				if (root.getMatchStr()!=null&&
						root.getMatchStr().endsWith(patternArray[i])) {
					// 增加这个模式串在数组的位置，并且将该节点的状态设置'已经匹配到模式串(1)'
					root.getMatchPatternList().add(i);
					// 0表示该节点没有匹配模式串，1表示有匹配模式串
					root.setStatus(1);
				}
			}
			// 以此设置该节点的所有子节点
			Iterator<Character> subIterator = root.getSubNodeList().keySet().iterator();
			while(subIterator.hasNext()){
				buildMatchStr(root.getSubNodeList().get(subIterator.next()), patternArray);
			}
		}
	}
	/**
	 * 将所有节点的失败节点设置为整个树的根节点
	 * @param root
	 */
	private static void buildFailedNode(TrieNode root){
		if (root!=null) {
			root.setFailedNode(root.getParentNode()==null?root:root.getParentNode().getFailedNode());
			Iterator<Character> subIterator = root.getSubNodeList().keySet().iterator();
			while(subIterator.hasNext()){
				buildFailedNode(root.getSubNodeList().get(subIterator.next()));
			}
		}
		
	}
	/**
	 * 打印树
	 * @param root
	 */
	protected static void printRoot(TrieNode root){
		if (root!=null) {
			System.out.println(root);
			Iterator<Character> keyIterator = root.getSubNodeList().keySet().iterator();
			while(keyIterator.hasNext()){
				Character key = keyIterator.next();
				printRoot(root.getSubNodeList().get(key));
			}
		}
	}
}
/**
 * Trie树节点
 * @author walter.xu
 *
 */
class TrieNode {
	// 当前节点的字符，如果为根节点，则为空
	private Character currentChar;  
	// 匹配的字符串，该字符串由其父匹配串+当前节点字符组成
	private String matchStr;
	// 已经匹配的模式串列表 为模式串再数组中的id组成
	private List<Integer> matchPatternList = new ArrayList<Integer>();
	private int status = 0; // 1表示未接受态
	// 所有匹配的位置列表
	private List<Integer> matchPositionList = new ArrayList<Integer>();
	// 子节点列表
	private Map<Character, TrieNode> subNodeList = new HashMap<Character, TrieNode>();
	// 父亲节点
	private TrieNode parentNode;
	// 事变节点
	private TrieNode failedNode;
	/**
	 * 自动机的执行方法 流程为：
	 * @param textArray
	 * @param i
	 * @return
	 */
	public TrieNode gotoNextNode(char[] textArray, int i){
		//1 获取待匹配字符
		char currentChar = textArray[i];
		//2 该带匹配字符是否在 该节点有子节点，如果有，则返回该子节点，如果没有则返回失败节点
		TrieNode nextNode = this.getSubNodeList().get(currentChar);
		if (nextNode != null) {
			// 2.1 表示该节点已经有匹配，则新增
			if (nextNode.getStatus() == 1) {
				nextNode.getMatchPositionList().add(i);
			}
			return nextNode;
		} else {
			//2.2 失败返回失败节点
			return this.getFailedNode();
		}
	}
	/**
	 * 添加模式串
	 * @param pattern
	 */
	public void addSubString(String pattern){
		char[] charArray = pattern.toCharArray();
		addSubChar(charArray, 0);
	}
	/**
	 * 按字符添加模式节点
	 * @param charArray
	 * @param index
	 */
	private void addSubChar(char[] charArray, int index){
		boolean lastChar = (index == charArray.length-1)?true:false;
		
		TrieNode currentNode = subNodeList.get(charArray[index]);
		if (currentNode == null) {
			currentNode = new TrieNode(charArray[index]);
			currentNode.setParentNode(this);
			subNodeList.put(currentNode.getCurrentChar(), currentNode);
		}
		// 不是最后个字符，则一直添加
		if (!lastChar) {
			currentNode.addSubChar(charArray, ++index);
		}
	}
	public TrieNode(){}
	private TrieNode(char c){
		this.currentChar = c;
	}

	public Character getCurrentChar() {
		return currentChar;
	}

	public void setCurrentChar(Character currentChar) {
		this.currentChar = currentChar;
	}

	public String getMatchStr() {
		return matchStr;
	}

	public void setMatchStr(String matchStr) {
		this.matchStr = matchStr;
	}

	public List<Integer> getMatchPatternList() {
		return matchPatternList;
	}

	public void setMatchPatternList(List<Integer> matchPatternList) {
		this.matchPatternList = matchPatternList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Integer> getMatchPositionList() {
		return matchPositionList;
	}

	public void setMatchPositionList(List<Integer> matchPositionList) {
		this.matchPositionList = matchPositionList;
	}

	public Map<Character, TrieNode> getSubNodeList() {
		return subNodeList;
	}

	public void setSubNodeList(Map<Character, TrieNode> subNodeList) {
		this.subNodeList = subNodeList;
	}

	public TrieNode getFailedNode() {
		return failedNode;
	}

	public void setFailedNode(TrieNode failedNode) {
		this.failedNode = failedNode;
	}

	public TrieNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TrieNode parentNode) {
		this.parentNode = parentNode;
		// 设置父节点的时候，同时根据父亲节点的已匹配串生成当前节点的已匹配串
		if (parentNode!=null&&parentNode.getMatchStr()!=null) {
			this.matchStr = parentNode.getMatchStr()+this.getCurrentChar();
		}else{
			this.matchStr = getCurrentChar()==null?null:getCurrentChar().toString();
		}
	}
	@Override
	public String toString(){
		return matchStr + ", "+status+", failed["+failedNode.getMatchStr()+"], pattern"+Arrays.toString(matchPatternList.toArray())+", positions:"+Arrays.toString(matchPositionList.toArray());
	}
}