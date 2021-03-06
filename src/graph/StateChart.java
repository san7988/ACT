package graph;

import java.util.List;

public class StateChart extends Graph{
	public StateChart(State state) {
		super(state);
	}
	
	@SuppressWarnings("unchecked")
	public StateChart(List<State> states,List<Transition> transitions) {
		super((List<INode>)(List<?>) states,(List<IEdge>)(List<?>)transitions);
	}
	
	@SuppressWarnings("unchecked")
	public List<State> getStateSet() {
		return (List<State>)(List<?>) super.getNodeSet();
	}

	@SuppressWarnings("unchecked")
	public List<Transition> getTransitionSet() {
		return (List<Transition>)(List<?>)super.getEdgeSet();
	}
	
	public INode addState(INode state) {
		return super.addNode(state);
	}

	public INode deleteState(INode state) {
		return super.deleteNode(state);
	}

	public IEdge addTransition(IEdge transition) {
		return super.addEdge(transition);
	}

	public IEdge deleteTransition(IEdge transition) {
		return super.deleteEdge(transition);
	}
	
	public boolean hasState(INode state) {
		return super.hasNode(state);
	}

	public boolean hasTransition(IEdge transition) {
		return super.hasEdge(transition);
	}

	public int getNumberOfStates() {
		return super.getNumberOfNodes();
	}
	
	public int getNumberOfTransitions() {
		return super.getNumberOfEdges();
	}

	public INode getStateById(int id){
		return super.getNodeById(id);
	}
}
