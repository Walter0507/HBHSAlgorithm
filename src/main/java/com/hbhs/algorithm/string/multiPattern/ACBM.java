package com.hbhs.algorithm.string.multiPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ACBM算法<BR>
 * 结合AC以及BM算法
 * 
 * @author walter.xu
 *
 */
public class ACBM {
	
	/**
	 * ACBM
	 * @param text
	 * @param patternArray
	 * @return
	 */
	public static Map<String, List<Integer>> acbm(String text, String[] patternArray){
		// 1 计算所有匹配串的最最小串的长度
		int minLength = getMinPatternLength(patternArray);
		// 2 计算匹配串的坏字符移动映射表
		Map<Character, Integer> badCharacterMap = buildBacCharacterMap(patternArray, minLength);
		// 3 建立反向的自动机
		BMTrieNode root = buildRootNode(patternArray);

		char[] textArray = text.toCharArray();
		// 4 执行自动机，并做对应映射关系
		int alingnAt = minLength-1;
		while(alingnAt < textArray.length){
			if (alingnAt + minLength >= textArray.length) {
				break;
			}
			// 匹配自动机字符并返回下一次需要移动的steps,steps为空时表示需要移动最低
			Integer steps = root.matchCurrentNode(textArray, alingnAt, alingnAt, badCharacterMap);
			alingnAt += (steps==null?minLength:steps);
		}
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
	private static Map<String, List<Integer>> genResultFromNode(BMTrieNode root, String[] patternArray){
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
	private static void setNodeToMap(BMTrieNode currentNode, String[] patternArray, Map<String, List<Integer>> resultMap){
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
								result.add(position);
							}
						}
					}
				}
				
			}
			// 依次设置当前节点的所有子节点
			Iterator<Character> keyIterator = currentNode.getSubNodeList().keySet().iterator();
			while(keyIterator.hasNext()){
				BMTrieNode subNode = currentNode.getSubNodeList().get(keyIterator.next());
				setNodeToMap(subNode, patternArray, resultMap);
				
			}
		}
	}
	
	/**
	 * 生成自动机
	 * @param patternArray
	 * @return
	 */
	private static BMTrieNode buildRootNode(String[] patternArray){
		BMTrieNode root = new BMTrieNode();
		for (String pattern: patternArray) {
			root.addSubString(pattern);
		}
		buildMatchStr(root, patternArray);
		return root;
	}
	
	/**
	 * 设置匹配串的结果列表
	 * @param root
	 * @param patternArray
	 */
	private static void buildMatchStr(BMTrieNode root, String[] patternArray){
		if (root!=null) {
			// 根据当前节点 验证每一个模式串，如果当前匹配的节点是以模式串为结尾的，那么该模式串就匹配
			for (int i = 0; i < patternArray.length; i++) {
				if (root.getMatchStr()!=null&&
						root.getMatchStr().startsWith(patternArray[i])) {
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
	
	private static Map<Character, Integer> buildBacCharacterMap(String[] patternArray, int minLength){
		Map<Character, Integer> badCharacterMap = new HashMap<Character, Integer>();
		for (String pattern: patternArray) {
			int totelLength = pattern.length();
			for (int i = 0; i < minLength; i++) {
				Integer steps = badCharacterMap.get(pattern.charAt(totelLength-1-i));
				if (steps == null) {
					badCharacterMap.put(pattern.charAt(totelLength-1-i), i+1);
				}
			}
		}
		return badCharacterMap;
	}
	
	private static int getMinPatternLength(String[] patternArray){
		int minLength = patternArray[0].length();
		for (int i = 1; i < patternArray.length; i++) {
			if (patternArray[i].length() < minLength) {
				minLength = patternArray[i].length();
			}
		}
		return minLength;
	}
	
	
	/**
	 * 打印树
	 * @param root
	 */
	protected static void printRoot(BMTrieNode root){
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
 * 树节点
 * @author walter.xu
 *
 */
class BMTrieNode {
	// 当前节点的字符，如果为根节点，则为空
	private Character currentChar;
	// 匹配的字符串，该字符串由其父匹配串+当前节点字符组成
	private String matchStr="";
	// 已经匹配的模式串列表 为模式串再数组中的id组成
	private List<Integer> matchPatternList = new ArrayList<Integer>();
	private int status = 0; // 1表示未接受态
	// 所有匹配的位置列表
	private List<Integer> matchPositionList = new ArrayList<Integer>();
	// 子节点列表
	private Map<Character, BMTrieNode> subNodeList = new HashMap<Character, BMTrieNode>();
	// 父亲节点
	private BMTrieNode parentNode;

	/**
	 * 匹配当前节点
	 * @param textArray 匹配串字符串
	 * @param alingnAt 移动到的窗口位置
	 * @param compareAt 待匹配的位置
	 * @param badCharacterMap 错误字典表
	 * @return 需要移动的位置
	 */
	public Integer matchCurrentNode(char[] textArray, int alingnAt, int compareAt, Map<Character, Integer> badCharacterMap){
		// 如果子节点为空，或者移动比较完成，则移动一位
		if (compareAt<0||this.getSubNodeList()==null||getSubNodeList().size()==0) {
			return 1;
		}
		// 获取到比较的字符
		char currentChar = textArray[compareAt];
		BMTrieNode preNode = this.getSubNodeList().get(currentChar);
		if (preNode != null) {
			// 2.1 表示该节点已经有匹配，则新增当前匹配位置
			if (preNode.getStatus() == 1) {
				preNode.getMatchPositionList().add(compareAt);
			}
			// 同时减少匹配量，并且比较该字符的前一个字符
			compareAt--;
			return preNode.matchCurrentNode(textArray, alingnAt, compareAt, badCharacterMap);
		} else {
			// 如果未匹配上，则查找字典获取到需要跳转的字符
			char nextCharInText = textArray[alingnAt+1];
			return badCharacterMap.get(nextCharInText);
		}
	}

	/**
	 * 添加模式串
	 * 
	 * @param pattern
	 */
	public void addSubString(String pattern) {
		char[] charArray = pattern.toCharArray();
		addSubChar(charArray, charArray.length-1);
	}

	/**
	 * 按字符添加模式节点
	 * 
	 * @param charArray
	 * @param index
	 */
	private void addSubChar(char[] charArray, int index) {
		boolean firstChar = (index == 0) ? true : false;

		BMTrieNode currentNode = subNodeList.get(charArray[index]);
		if (currentNode == null) {
			currentNode = new BMTrieNode(charArray[index]);
			currentNode.setParentNode(this);
			subNodeList.put(currentNode.getCurrentChar(), currentNode);
		}
		// 不是最后个字符，则一直添加
		if (!firstChar) {
			currentNode.addSubChar(charArray, --index);
		}
	}

	public BMTrieNode() {
	}

	private BMTrieNode(char c) {
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
	
	public void setMatchStr(char[] charArray, int index) {
		for (int i = 0; i <= index; i++) {
			this.matchStr += charArray[i];
		}
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

	public Map<Character, BMTrieNode> getSubNodeList() {
		return subNodeList;
	}

	public void setSubNodeList(Map<Character, BMTrieNode> subNodeList) {
		this.subNodeList = subNodeList;
	}

	public BMTrieNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(BMTrieNode parentNode) {
		this.parentNode = parentNode;
		// 设置父节点的时候，同时根据父亲节点的已匹配串生成当前节点的已匹配串
				if (parentNode!=null&&parentNode.getMatchStr()!=null) {
					this.matchStr = this.getCurrentChar()+parentNode.getMatchStr();
				}else{
					this.matchStr = getCurrentChar()==null?null:getCurrentChar().toString();
				}
	}

	@Override
	public String toString() {
		return matchStr + ", " + status + ", pattern"
				+ Arrays.toString(matchPatternList.toArray()) + ", positions:"
				+ Arrays.toString(matchPositionList.toArray());
	}
}
