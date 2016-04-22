package graph;

import java.util.ArrayList;
import java.util.List;

public class State extends Node {
	private String name;
	private String desc;
	ArrayList<String> input = new ArrayList<String>();
	//private boolean[] inputBool;	//unary
	ArrayList<String> clickable = new ArrayList<String>();
	public State(String stateName, String stateDesc, ArrayList<String> stateInput, ArrayList<String> stateClickable) {
		super();
		this.name = stateName;
		this.desc = stateDesc;
		for(int i=0;i<stateInput.size();i++){
			this.input.add(stateInput.get(i));	
		}
		stateInput.clear();
		for(int i=0;i<stateClickable.size();i++)
			this.clickable.add(stateClickable.get(i));		
		stateClickable.clear();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<String> getInput() {
		return input;
	}
	public void setInput(ArrayList<String> input) {
		this.input = input;
	}
	public ArrayList<String> getClickable() {
		return clickable;
	}
	public void setClickable(ArrayList<String> clickable) {
		this.clickable = clickable;
	}
	
	//parental properties accessor
	public IGraph getStateChart() {
		return this.mGraph;
	}

	public void setStateChart(IGraph stateChart) {
		this.mGraph = stateChart;
	}
	
	public List<IEdge> getIncomingTransitionList() {
		return this.mIncomingEdgeList;
	}

	public void addOutgoingTransition(IEdge edge) {
		this.mOutgoingEdgeList.add(edge);
	}
	
	public List<IEdge> getOutgoingTransitionList() {
		return this.mOutgoingEdgeList;
	}
	
	public void addIncomingTransition(IEdge edge) {
		this.mIncomingEdgeList.add(edge);
	}
	
	//changing state of Node
	public boolean isIncomingTransition(IEdge e) {	
		return this.mIncomingEdgeList.contains(e);
	}

	public boolean isOutgoingTransition(IEdge e) {
		return this.mOutgoingEdgeList.contains(e);
	}

	public void deleteIncomingTransition(IEdge edge) {
		this.mIncomingEdgeList.remove(edge);
	}

	public void deleteOutgoingTransition(IEdge edge) {
		this.mOutgoingEdgeList.remove(edge);
	}
}
