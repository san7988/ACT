package graph;

public class myGraph{
	public static void main(String[] args)
	{
		State state = new State();
		Collection.addState(state);
		//state.setStateChart(stateChart);
		//state.setName("0");
		//stateChart = (StateChart)state.getStateChart();	//((State)rootState).
		//stateChart = new Graph(root);
		//Make a graph
		for(int i=0;i<10;i++){
			Collection.addState(new State());
		}
		addTransition(0,2);
		addTransition(0,3);
		addTransition(1,4);		//disconnected
		addTransition(3,5);
		addTransition(5,2);

		/*
		System.out.println("Nodes in the graph : " + graph.getNodeSet().toString());
		System.out.println("Edges in the graph are as follows: ");
		for(int i=0;i<graph.getNumberOfEdges();i++)
			System.out.println("Edge Id "+ graph.getEdgeSet().get(i).getEdgeId()+" : "+ graph.getEdgeSet().get(i).getTail().getId()+ " "+graph.getEdgeSet().get(i).getHead().getId());
		*/
		/*System.out.println(Collection.getStateList().toString());
		System.out.println(Collection.getTransitionList().toString());*/
		StateChart stateChart = new StateChart(Collection.getStateList(),Collection.getTransitionList());
		//System.out.println(stateChart.getStateSet().toString());
		stateChart.setRoot(Collection.getStateById(0));
		//System.out.println(stateChart.getRoot());
		DFSRecursionTesting test = new DFSRecursionTesting();
		test.dfs(stateChart, stateChart.getRoot());
		//ArrayList<Stack<IEdge>> t = test.dfs(stateChart, stateChart.getRoot());
		//test.print(t);
	}
	
	private static void addTransition(int src, int dest)
	{
		INode stateFrom = Collection.getStateById(src);
		INode stateTo = Collection.getStateById(dest);
		//String trig = "Edge"+i++;
		try {
			Collection.addTransition(new Transition(stateFrom,stateTo));
			//Collection.addTransition(new Transition(stateFrom,stateTo,trig));
		} catch (Exception e) {
			System.out.println("Something went wrong while adding edge to the graph");
		}
	}

/*public class GraphtoStringVisitor {
	private IGraph mGraph;
	private String mOutputString;
	private Set<INode> mVisited = new HashSet<INode>();
	public GraphtoStringVisitor(IGraph graph) {
		this.mGraph = graph;
	}

	public void visit() {
		this.mOutputString = "Graph = " + this.mGraph.getId();
		this.mOutputString = this.mOutputString + "\n";
		this.visit(this.mGraph.getRoot());
	}

	private void visit(INode node) {
		if(this.mVisited.contains(node)) {
			return;
		}
		this.mVisited.add(node);
		this.mOutputString = this.mOutputString + "\nnode = " + node.getId() + "\n";
		for(IEdge e : node.getOutgoingEdgeList()) {
				this.visit(e);
		}
		for(IEdge e : node.getOutgoingEdgeList()) {
			this.visit(e.getHead());
		}
	}

	private void visit(IEdge e) {
		this.mOutputString = this.mOutputString + "\n";
		this.mOutputString = this.mOutputString + "edge " + e.getId() + " = (";
		this.mOutputString = this.mOutputString + e.getTail().getId() + ", ";
		this.mOutputString = this.mOutputString + e.getHead().getId() + ")";
	}

	public String getOutputString() {
		return this.mOutputString;
	}
	*/
}
