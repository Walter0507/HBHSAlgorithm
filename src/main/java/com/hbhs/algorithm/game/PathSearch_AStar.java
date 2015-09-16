package com.hbhs.algorithm.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hbhs.algorithm.HBHSAssert;
import com.hbhs.algorithm.geometry.CoordsNode;
import com.hbhs.algorithm.sort.HeapSort;

/**
 * A*寻路算法<BR>
 * A*(A-Star)算法是一种静态路网中求解最短路最有效的直接搜索方法。之后涌现了很多预处理算法（ALT，CH，HL等等），在线查询效率是A*
 * 算法的数千甚至上万倍。<BR>
 * 如下图，从A到B的最短距离：<Br>
 * # # # # # # # # #<BR>
 * # # # # # # # # #<BR>
 * # A # # # # # B #<BR>
 * # # # # # # # # #<BR>
 * # # # # # # # # #<BR>
 * 执行的查询操作如下<Br>
 * 1. 从点A开始，并且把它作为待处理点存入一个“开启列表”。开启列表就像一张购物清单。尽管现在列表里只有一个元素，但以后就会多起来。
 * 你的路径可能会通过它包含的方格，也可能不会。基本上，这是一个待检查方格的列表。<BR>
 * 2. 寻找起点周围所有可到达或者可通过的方格，跳过有墙，水，或其他无法通过地形的方格。也把他们加入开启列表。为所有这些方格保存点A作为“父方格”。
 * 当我们想描述路径的时候，父方格的资料是十分重要的。后面会解释它的具体用途。<BR>
 * 3. 从开启列表中删除点A，把它加入到一个“关闭列表”，列表中保存所有不需要再次检查的方格。<BR>
 * 4. 为了继续搜索，从开启列表中选择F值(后续提供计算方法)最低的方格。然后，对选中的方格做如下处理<BR>
 * 5. 检查所有相邻格子。跳过那些已经在关闭列表中的或者不可通过的(有墙，水的地形，或者其他无法通过的地形)，把他们添加进开启列表，如果他们还不在里面的话。
 * 把选中的方格作为新的方格的父节点。<BR>
 * 6.
 * 如果某个相邻格已经在开启列表里了，检查现在的这条路径是否更好。换句话说，检查如果我们用新的路径到达它的话，G值是否会更低一些。如果不是，那就什么都不做。
 * 另一方面，如果新的G值更低，那就把相邻方格的父节点改为目前选中的方格（在上面的图表中，把箭头的方向改为指向这个方格）。最后，重新计算F和G的值。<BR>
 * <b>F值计算方法：</b><BR>
 * 公式表示为： f(n)=g(n)+h(n),<BR>
 * -其中 f(n) 是从初始点经由节点n到目标点的估价函数，<BR>
 * -g(n) 是在状态空间中从初始节点到n节点的实际代价，<BR>
 * -h(n) 是从n到目标节点最佳路径的估计代价。<BR>
 * 保证找到最短路径（最优解的）条件，关键在于估价函数h(n)的选取：<BR>
 * -估价值h(n)<=n到目标节点的距离实际值，这种情况下，搜索的点数多，搜索范围大，效率低。但能得到最优解。并且如果h(n)=d(n)，即距离估计h(n)等于最短距离
 * ，那么搜索将严格沿着最短路径进行， 此时的搜索效率是最高的。<BR>
 * -如果 估价值>实际值,搜索的点数少，搜索范围小，效率高，但不能保证得到最优解。<BR>
 * <B>A*方法总结</B><BR>
 * 1，把起始格添加到开启列表。   <BR>
 * 2，重复如下的工作：        <BR>
 *    a) 寻找开启列表中F值最低的格子。我们称它为当前格。      <BR>
 *    b) 把它切换到关闭列表。       <BR>
 *    c) 对相邻的8格中的每一个？            <BR>
 *     * 如果它不可通过或者已经在关闭列表中，略过它。反之如下。            <BR>
 *     * 如果它不在开启列表中，把它添加进去。把当前格作为这一格的父节点。记录这一格的F,G,和H值。    <BR>        
 *     * 如果它已经在开启列表中，用G值为参考检查新的路径是否更好。更低的G值意味着更好的路径。如果是这样，就把这一格的父节点改成当前格，并且重新计算这一格的G和F值。如果你保持你的开启列表按F值排序，改变之后你可能需要重新对开启列表排序。<BR>       
 *    d) 停止，当你            <BR>
 *     * 把目标格添加进了开启列表，这时候路径被找到，或者           <BR>
 *     * 没有找到目标格，开启列表已经空了。这时候，路径不存在。    <BR>
 * 3.保存路径。从目标格开始，沿着每一格的父节点移动直到回到起始格。这就是你的路径。<BR>
 * @author walter.xu
 *
 */
public class PathSearch_AStar {
	/**
	 * A*路径查找算法
	 * @param areaX
	 * @param areaY
	 * @param blockedNodeArray
	 * @param startArray
	 * @param rearchArray
	 * @return
	 */
	public static List<double[]> A_Star(double areaX, double areaY, List<double[]> blockedNodeArray, 
			double[] startArray, double[] rearchArray){
		return A_Star(areaX, areaY, blockedNodeArray, startArray, rearchArray, false);
	}
	/**
	 * A*路径查找算法
	 * @param areaX
	 * @param areaY
	 * @param blockedNodeArray
	 * @param startArray
	 * @param rearchArray
	 * @param isDebug
	 * @return
	 */
	public static List<double[]> A_Star(double areaX, double areaY, List<double[]> blockedNodeArray, 
			double[] startArray, double[] rearchArray, boolean isDebug){
		HBHSAssert.isTrue(startArray.length<2, "start node must be more than 2 elements");
		HBHSAssert.isTrue(rearchArray.length<2, "end node must be more than 2 elements");
		Node startNode = new Node(startArray[0], startArray[1], null);
		Node rearchNode = new Node(rearchArray[0], rearchArray[1], null);
		List<Node> blockedNodeList = null;
		if (blockedNodeArray!=null&&blockedNodeArray.size()>0) {
			blockedNodeList = new ArrayList<Node>();
			for(double[] args: blockedNodeArray){
				if (args!=null&&args.length>=2) {
					blockedNodeList.add(new Node(args[0], args[1], null));
				}
			}
		}
		return A_Star(areaX, areaY, blockedNodeList, startNode, rearchNode, isDebug);
	}
	
	/**
	 * A*路径查找算法
	 * @param areaX 区域的最大X轴长度
	 * @param areaY 区域的最大Y轴长度
	 * @param blockedNodeList 被禁止经过的所有被阻截的节点列表
	 * @param startNode 起始节点
	 * @param rearchNode 截止节点
	 * @return
	 */
	public static List<double[]> A_Star(double areaX, double areaY, List<Node> blockedNodeList, 
			Node startNode, Node rearchNode){
		return A_Star(areaX, areaY, blockedNodeList, startNode, rearchNode, false);
	}
	/**
	 * A*路径查找算法
	 * @param areaX 区域的最大X轴长度
	 * @param areaY 区域的最大Y轴长度
	 * @param blockedNodeList 被禁止经过的所有被阻截的节点列表
	 * @param startNode 起始节点
	 * @param rearchNode 截止节点
	 * @param isDebug 是否需要打印DEBUG模式的图形
	 * @return
	 */
	public static List<double[]> A_Star(double areaX, double areaY, List<Node> blockedNodeList, 
			Node startNode, Node rearchNode, boolean isDebug){
		// 构造未匹配的节点列表，使用HASH，key为对应的节点HASH值
		Map<Integer, Node> standByNodeMap = new HashMap<Integer, Node>();
		// 构造已匹配的节点列表，使用HASH，key为对应的节点HASH值
		Map<Integer, Node> reachedNodeMap = new HashMap<Integer, Node>();
		// 构造所有阻挡的列表
		Map<Integer, Node> blockedNodeMap = buildBlockedNodeMap(blockedNodeList);
		// 构造树结构 同时将第一个开始节点添加进去
		TreeNode treeNode = new TreeNode(startNode, rearchNode);
		treeNode.addNode(startNode);
		int time = 1;
		// 迭代知道找到已经匹配的路径
		while(!treeNode.isMatched()){
			// 查找树结构
			search(treeNode, areaX, areaY, standByNodeMap, reachedNodeMap, blockedNodeMap);
			if (isDebug) {
				// 打印
				printTree(time, areaX, areaY, treeNode, blockedNodeList);
			}
			time++;
		}
		return treeNode.findReachablePath();
	}
	/**
	 * 测试打印使用
	 * @param times
	 * @param areaX
	 * @param arexY
	 * @param tree
	 */
	private static void printTree(int times, double areaX, double arexY, TreeNode tree, List<Node> blockedNodeList){
		System.out.println("The "+times+ " time match result('A': start Node, 'B': end Node, '*': unchoosed node, '#' choosen node):");
		String[][] args = new String[(int)areaX][(int)arexY];
		for (int i = 0; i < args.length; i++) {
			for (int j = 0; j < args[i].length; j++) {
				args[i][j] = "*";
			}
		}
		
		Node currentNode = tree.getCompareNode();
		while(currentNode!=null){
			args[(int)currentNode.getX()][(int)currentNode.getY()] = "#";
			currentNode = currentNode.getParentNode();
		}
		args[(int)tree.getStartNode().getX()][(int)tree.getStartNode().getY()] = "A";
		args[(int)tree.getReachNode().getX()][(int)tree.getReachNode().getY()] = "B";
		for(Node node: blockedNodeList){
			args[(int)node.getX()][(int)node.getY()] = "+";
		}
		for (int i = 0; i < args.length; i++) {
			System.out.println(Arrays.toString(args[i]));
		}
	}
	/**
	 * 搜索路径
	 * @param treeNode 查找结构
	 * @param areaX 
	 * @param areaY
	 * @param standByNodeMap 待匹配的节点列表
	 * @param reachedNodeMap 已经匹配的节点列表
	 * @param blockedNodeMap 阻断的节点列表
	 */
	private static void search(TreeNode treeNode, double areaX, double areaY, 
			Map<Integer, Node> standByNodeMap, Map<Integer, Node> reachedNodeMap, 
			Map<Integer, Node> blockedNodeMap){
		// 获取所有的可使用节点，除开已经匹配过的或者阻断的节点
		List<Node> nearByAvailableNode = getAllAvailableNodeNearBy(treeNode.getCompareNode(), areaX, areaY, 
				reachedNodeMap, blockedNodeMap);
		for(Node node: nearByAvailableNode){
			if (reachedNodeMap.get(node.hashCode())!=null) {
				continue ;
			}
			if (standByNodeMap.get(node.hashCode())!=null) {
				// 如果该节点已经被添加到了待匹配列表中，则需要验证着两个节点的FN值大小，
				// 如果当前节点的FN值更小，则需要替换
				Node standByNode = standByNodeMap.get(node.hashCode());
				if (standByNode.getFn() == node.getFn()) {
					node.setParentNode(standByNode.getParentNode());
				}
				treeNode.setNode(node);
			}else{
				// 没有的话直接添加即可
				standByNodeMap.put(node.hashCode(), node);
				treeNode.addNode(node);
			}
		}
		// 完成后将该匹配节点添加到已经匹配的节点列表中
		reachedNodeMap.put(treeNode.getCompareNode().hashCode(), treeNode.getCompareNode());
		// 需要修改组织结构树，将已经匹配的节点移除，并重新构造最小堆
		treeNode.removeNode();
		treeNode.buildMinHeap();
	}
	/**
	 * 获取到某个节点周围共计8个节点的所有可用节点<Br>
	 * 1 将已经查找过的节点排除<BR>
	 * 2 将已经被阻断的节点排除<BR>
	 * @param currentNode 当前节点
	 * @param areaX 区域X轴最大值
	 * @param areaY 区域Y轴最大值
	 * @param reachedNodeMap 已经匹配的节点列表
	 * @param blockedNodeMap 阻断的节点列表
	 * @return
	 */
	private static List<Node> getAllAvailableNodeNearBy(Node currentNode, double areaX, double areaY, 
			Map<Integer, Node> reachedNodeMap, Map<Integer, Node> blockedNodeMap){
		List<Node> availableNearByNodeList = new ArrayList<Node>();
		double currentX = currentNode.getX();
		double currentY = currentNode.getY();
		if (currentX-1>=0) {
			if (currentY-1>=0) {
				// A[x-1][y-1]
				doAddNode(availableNearByNodeList, new Node(currentX-1, currentY-1, currentNode), 
						reachedNodeMap, blockedNodeMap);
			}
			if(currentY+1<areaY){
				// A[x-1][y+1]
				doAddNode(availableNearByNodeList, new Node(currentX-1, currentY+1, currentNode), 
						reachedNodeMap, blockedNodeMap);
			}
			// A[x-1][y]
			doAddNode(availableNearByNodeList, new Node(currentX-1, currentY, currentNode), 
					reachedNodeMap, blockedNodeMap);
		}
		if (currentX+1<areaX) {
			if (currentY-1>=0) {
				// A[x+1][y-1]
				doAddNode(availableNearByNodeList, new Node(currentX+1, currentY-1, currentNode), 
						reachedNodeMap, blockedNodeMap);
			}
			if(currentY+1<areaY){
				// A[x+1][y+1]
				doAddNode(availableNearByNodeList, new Node(currentX+1, currentY+1, currentNode), 
						reachedNodeMap, blockedNodeMap);
			}
			// A[x+1][y]
			doAddNode(availableNearByNodeList, new Node(currentX+1, currentY, currentNode), 
					reachedNodeMap, blockedNodeMap);
		}
		if (currentY-1>=0) {
			// A[x][y-1]
			doAddNode(availableNearByNodeList, new Node(currentX, currentY-1, currentNode), 
					reachedNodeMap, blockedNodeMap);
		}
		if(currentY+1<areaY){
			// A[x][y+1]
			doAddNode(availableNearByNodeList, new Node(currentX, currentY+1, currentNode), 
					reachedNodeMap, blockedNodeMap);
		}
		return availableNearByNodeList;
	}
	/**
	 * 添加节点
	 * @param availableNearByNodeList
	 * @param node
	 * @param reachedNodeMap
	 * @param blockedNodeMap
	 */
	private static void doAddNode(List<Node> availableNearByNodeList, Node node, 
			Map<Integer, Node> reachedNodeMap, Map<Integer, Node> blockedNodeMap){
		if (reachedNodeMap==null) {
			if (blockedNodeMap==null||blockedNodeMap.get(node.hashCode())==null) {
				availableNearByNodeList.add(node);
			}
		}else{
			if (reachedNodeMap.get(node.hashCode())==null) {
				if (blockedNodeMap==null||blockedNodeMap.get(node.hashCode())==null) {
					availableNearByNodeList.add(node);
				}
			}
		}
	}
	/**
	 * 构造阻断的节点列表，从list转换成HASHMAP，便于查询
	 * @param blockedNodeList
	 * @return
	 */
	private static Map<Integer, Node> buildBlockedNodeMap(List<Node> blockedNodeList){
		if (blockedNodeList==null||blockedNodeList.size()==0) {
			return null;
		}	
		Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		for(Node node: blockedNodeList){
			nodeMap.put(node.hashCode(), node);
		}
		return nodeMap;
	}
}
/**
 * 节点树结构，用于保存树的开始节点以及到大节点以及待匹配节点列表。同时可查询到最优路径
 * @author walter.xu
 *
 */
class TreeNode{
	private List<Node> listNode = new ArrayList<Node>();
	private Node startNode;
	private Node reachNode;
	private boolean isMatch = false;
	/**
	 * 初始化树结构
	 * @param startNode
	 * @param reachNode
	 */
	public TreeNode(Node startNode, Node reachNode){
		this.startNode = startNode;
		this.reachNode = reachNode;
	}
	/**
	 * 获取待匹配节点列表中的最开始一个(理论上FN值最小，路线最优的一个)
	 * @return
	 */
	public Node getCompareNode(){
		return (listNode==null||listNode.size()==0)?null:listNode.get(0);
	}
	/**
	 * 是否已经匹配
	 * @return
	 */
	public boolean isMatched(){
		return isMatch;
	}
	/**
	 * 添加一个节点
	 * @param node
	 */
	public void addNode(Node node){
		if (node!=null) {
			listNode.add(node);
			// 设置GN, HN, FN
			caclGN(node);
			caclHN(node);
			caclFN(node);
		}
	}
	/**
	 * 该方法的调用条件如下<BR>
	 * 1 当前节点已经被使用过了，<BR>
	 * 2 当前节点的FN值比的FN值要小的时候，则替换之前的节点为当前节点，否则不做任何操作
	 * @param node
	 */
	public void setNode(Node node){
		if (node!=null) {
			setNode(0 ,node);
			// 设置GN, HN, FN
			caclGN(node);
			caclHN(node);
			caclFN(node);
		}
	}
	/**
	 * 该方法的调用条件如下<BR>
	 * 1 当前节点已经被使用过了，<BR>
	 * 2 当前节点的FN值比的FN值要小的时候，则替换之前的节点为当前节点，否则不做任何操作
	 * @param index 匹配的节点index
	 * @param node
	 */
	private void setNode(int index, Node node){
		if (index < listNode.size()) {
			if (listNode.get(index).getFn() == node.getFn()) {
				listNode.set(index, node);
				return ;
			}else if (listNode.get(index).getFn() < node.getFn()) {
				setNode(2*index+1, node);
				setNode(2*index+2, node);
			} 
		}
	}
	
	/**
	 * GN为开始节点到当前节点的实际耗费，可根据父节点来执行来执行<BR>
	 * 对角节点按照14的阀值添加，如果是相邻的则按照10的阀值增加
	 * @param node
	 */
	private void caclGN(Node node){
		if (node.getParentNode()!=null) {
			if (node.getX() == node.getParentNode().getX()||
					node.getY()==node.getParentNode().getY()) {
				// 相邻的阀值为10
				node.setGn(node.getParentNode().getGn()+10);
			}else{
				// 斜对角 阀值为14
				node.setGn(node.getParentNode().getGn()+14);
			}
		}
	}
	/**
	 * 计算HN：对角节点按照14的阀值添加，如果是相邻的则按照10的阀值增加
	 * @param node
	 */
	private void caclHN(Node node){
		double diffX = node.getX() > reachNode.getX()?(node.getX() - reachNode.getX()):(reachNode.getX()-node.getX());
		double diffY = node.getY() > reachNode.getY()?(node.getY() - reachNode.getY()):(reachNode.getY()-node.getY());
		node.setHn(diffX>diffY?(diffY*14+(diffX-diffY)*10):(diffX*14+(diffY-diffX)*10));
	}
	
	protected double cacleNodeDistance(Node node1, Node node2){
		double diffX= node1.getX()>node2.getX()?(node1.getX()-node2.getX()):(node2.getX()-node1.getX());
		double diffY= node1.getY()>node2.getY()?(node1.getY()-node2.getY()):(node2.getY()-node1.getY());
		return diffX>diffY?(diffY*14+(diffX-diffY)*10):(diffX*14+(diffY-diffX)*10);
	}
	
	/**
	 * 计算FN值：F(n) = G(n)+H(n);
	 * @param node
	 */
	private void caclFN(Node node){
		node.setFn(node.getGn()+node.getHn());
	}
	
	
	
	/**
	 * 将所有的待匹配节点做堆排序，执行成一个最小堆，使得第一个节点肯定是FN值最小的一个
	 * @return
	 */
	public boolean buildMinHeap(){
		HeapSort.sort(listNode, new Comparator<Node>() {

			public int compare(Node arg0, Node arg1) {
				// 排序方法，按照FN从小到大匹配，如果FN(始点到该节点距离+预计到终点的距离)值一直，则按照HN(预估到终点的距离)值匹配
				if (arg0.getFn() == arg1.getFn()) {
					return arg0.getHn()-arg1.getHn();
				}
				return arg0.getFn() - arg1.getFn();
			}
			
		});
		// 如果首节点显示该节点已经到了终点了，那么直接返回已经匹配成功
		if (listNode.size()>0 &&listNode.get(0).getX() == reachNode.getX()&&listNode.get(0).getY()==reachNode.getY()) {
			this.isMatch = true;
		}
		return isMatch;
	}
	/**
	 * 返回所有的路径坐标，如果没找到，则返回空
	 * @return
	 */
	public List<double[]> findReachablePath(){
		if (!isMatch) {
			return null;
		}
		List<double[]> resultList = new ArrayList<double[]>();
		// 迭代所有的父节点
		Node currentNode = this.getCompareNode();
		addParentNode(resultList, currentNode);
		return resultList;
	}
	private void addParentNode(List<double[]> resultList, Node node){
		if (node!=null) {
			addParentNode(resultList, node.getParentNode());
			resultList.add(new double[]{node.getX(),node.getY()});
		}
	}
	/**
	 * 删除掉第一个节点，该方法在搜索某个节点完成后执行<BR>
	 * 用于定义某个节点已经完成搜索
	 */
	public void removeNode(){
		listNode.remove(0);
	}

	public Node getStartNode() {
		return startNode;
	}

	public Node getReachNode() {
		return reachNode;
	}
}

/**
 * 节点类，用于定义某个节点的位置，以及父节点，以及对应计算方式: f(n)=g(n)+h(n)
 * @author walter.xu
 *
 */
class Node extends CoordsNode{
	
	private Node parentNode;  //  父节点
	private int fn; // 预估从开始节点到到达节点的经过当前节点的最优阀值
	private int gn; // 实际从开始节点到当前节点的阀值
	private int hn; // 预计从该节点到到达节点的最优阀值

	/**
	 * 
	 * @param x X轴值
	 * @param y Y轴值
	 * @param parentNode 父节点
	 */
	public Node(double x, double y, Node parentNode){
		super(x, y);
		this.parentNode = parentNode;
	}
	
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
		
	}
	
	public int getFn() {
		return fn;
	}
	public void setFn(int fn) {
		this.fn = fn;
	}
	public int getGn() {
		return gn;
	}
	public void setGn(int gn) {
		this.gn = gn;
	}
	public void setGn(double gn) {
		this.gn = (int)gn;
	}
	public int getHn() {
		return hn;
	}
	public void setHn(int hn) {
		this.hn = hn;
	}
	public void setHn(double hn) {
		this.hn = (int)hn;
	}
	/**
	 * 重写HASH， 
	 */
	@Override
	public int hashCode() {
		return (int)((getX()*33+getY()*57)*100);
	}
	public String toString(){
		return "["+fn +"("+super.getX()+", "+super.getY()+")";
	}
}

