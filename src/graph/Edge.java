package graph;

import graph.IEdge;
import graph.IGraph;
import graph.INode;
//import utilities.IdGenerator;

/**
 * @author ZZ4JWJ
 *
 */
public class Edge implements IEdge {

	protected IGraph mGraph;		
	protected INode mTail; 		//source
	protected INode mHead; 		//target
	protected int edgeId;
	/*private String trigger;
	private ArrayList<String> guard;
	private String action;*/
	protected static int EDGEID = 0;
		
	//private String mId;
	
	public Edge(INode tail, INode head) throws Exception {
		if(Collection.hasState(tail) && Collection.hasState(head)){
			this.mTail = tail;
			this.mHead = head;
			setEdgeId(EDGEID++);
			//this.setGraph(graph);
			//graph.addEdge(this);
		}
	}

	@Override
	public IGraph getGraph() {
		return this.mGraph;
	}

	@Override
	public void setGraph(IGraph graph) {
		this.mGraph = graph;
	}

	@Override
	public INode getHead() {
		return this.mHead;
	}

	@Override
	public INode getTail() {
		return this.mTail;
	}

	public int getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(int edgeId) {
		this.edgeId = edgeId;
	}

	public String toString(){
		return Integer.toString(edgeId);
	}
}
