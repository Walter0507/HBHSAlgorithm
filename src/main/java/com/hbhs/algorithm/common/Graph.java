package com.hbhs.algorithm.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 图 数据结构
 * @author walter.xu
 *
 */
public class Graph<T> {
	private Map<Integer, GraphNode<T>> nodeMap = new HashMap<Integer, GraphNode<T>>();
	
	public void clear(){
		nodeMap.clear();
	}
	public synchronized void addNode(T from, T to, Double cost, boolean directed){
		if (from == null||to==null||cost==null) {
			return ;
		}
		GraphNode<T> fromNode = new GraphNode<T>(from);
		GraphNode<T> toNode = new GraphNode<T>(to);
		addNode(fromNode, toNode, cost);
		if (!directed) {
			addNode(toNode, fromNode, cost);
		}
	}
	public synchronized void removeNode(T node){
		getGraphMap().remove(node.hashCode());
		Iterator<GraphNode<T>> nodeIterator = getGraphMap().values().iterator();
		while(nodeIterator.hasNext()){
			GraphNode<T> graphNode = nodeIterator.next();
			if (graphNode.getNextNodeMap().get(node)!=null) {
				graphNode.getNextNodeMap().remove(node);
			}
		}
	}
	
	private void addNode(GraphNode<T> fromNode, GraphNode<T> toNode, Double cost){
		if (getGraphMap().get(fromNode.hashCode())==null) {
			setGraphMap(fromNode);
		}
		getGraphMap().get(fromNode).addNextNode(toNode, cost);
	}
	
	private Map<Integer, GraphNode<T>> getGraphMap() {
		return nodeMap;
	}
	private void setGraphMap(GraphNode<T> node){
		nodeMap.put(node.hashCode(), node);
	}
	
	protected List<GraphNode<T>> getGraph(){
		return null;//TODO
	}

}

class GraphNode<T>{
	private T value;
	private Map<GraphNode<T>, Double> nextNodeMap = new HashMap<GraphNode<T>, Double>();
	public GraphNode(T value){
		this.value = value;
	}
	
	public void addNextNode(GraphNode<T> nextNode, Double cost){
		nextNodeMap.put(nextNode, cost);
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Map<GraphNode<T>, Double> getNextNodeMap() {
		return nextNodeMap;
	}
	public void setNextNodeMap(Map<GraphNode<T>, Double> nextNodeMap) {
		this.nextNodeMap = nextNodeMap;
	}
	
}