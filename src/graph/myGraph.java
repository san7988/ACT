package graph;

import java.util.ArrayList;

public class myGraph{
	public static void main(String[] args) throws Exception
	{
		String stateName = "S1";
		String stateDesc = "Description1";
		ArrayList<String> stateInput = new ArrayList<String>();//{"I1","I2","I3"};
		stateInput.add("I1");
		stateInput.add("I2");
		stateInput.add("I3");
		ArrayList<String> stateClickable = new ArrayList<String>();//{"I1","I2","I3"};
		stateClickable.add("C1");
		stateClickable.add("C2");
		
		State state = new State(stateName,stateDesc,stateInput,stateClickable);
		Collection.addState(state);
		//state.setStateChart(stateChart);
		//state.setName("0");
		//stateChart = (StateChart)state.getStateChart();	//((State)rootState).
		//stateChart = new Graph(root);
		//Make a graph
		stateInput = null; stateClickable = null;
		int stateNum = 2,input = 3,click = 3,j=0;
		for(int i=2;i<10;i++){
			stateInput = new ArrayList<String>();
			stateClickable  = new ArrayList<String>();
			stateName = "S"+stateNum;
			stateDesc = "Description"+stateNum++;
			for(j=0;j<3;j++){
				stateInput.add("I"+input++);
				
				stateClickable.add("C"+click++);
			}
			Collection.addState(new State(stateName,stateDesc,stateInput,stateClickable));
			stateInput = null;
			stateClickable = null;
		}
		Collection.printState();
		
		String transitionDesc = "JUST LIKE THAT";
		String transitionSourceStateName = "S1";
		String transitionDestinationStateName = "S2";
		//convert stateNames into Nodes so as to call constructor for transition
		State transitionSourceState = Collection.getStateByName(transitionSourceStateName);
		//System.out.println("Check:TransitionSourceState : "+transitionSourceState.toString());

		
		
		
		
		State transitionDestinationState = Collection.getStateByName(transitionDestinationStateName);
		//System.out.println("Check:TransitionDestState : "+transitionDestinationState.toString());
		String transitionTrigger = "C1";	//check that s1 has c1 in it.
		String transitionGuardBinaryOperationLhs = "I1";	//check that variables are ci in s1
		String transitionGuardBinaryOperationOp = "=";
		String transitionGuardBinaryOperationRhs = "I2";	//check
		//check that lhs and rhs are defined at s1. may be at the time fo adding guard to transition
		BinaryOperation transitionGuardBinaryOperation = new BinaryOperation(transitionGuardBinaryOperationLhs, transitionGuardBinaryOperationOp, transitionGuardBinaryOperationRhs); 
		//make some binary expressions
		String transitionGuardUnaryOperationVar = "I3";	//check
		String transitionGuardUnaryOperationOp = "!";
		UnaryOperation  transitionGuardUnaryOperation = new UnaryOperation(transitionGuardUnaryOperationVar,transitionGuardUnaryOperationOp);
		Collection.addOperation(transitionGuardUnaryOperation);
		Collection.addOperation(transitionGuardBinaryOperation);
		Guard transitionGuard = new Guard(Collection.getOperationList());
		
		String transitionAction = "A1";		//Maybe implemented as name,value pair (eg- save I1,I2 pair)
		int i=0;
		Transition transition = new Transition("T"+i++,transitionDesc,transitionSourceState, transitionDestinationState, transitionTrigger, transitionGuard, transitionAction);
		Collection.addTransition(transition);
		Collection.addTransition(new Transition("T"+i++,transitionDesc,Collection.getStateByName("S1"),Collection.getStateByName("S3"),transitionTrigger,transitionGuard,transitionAction));
		Collection.addTransition(new Transition("T"+i++,transitionDesc,Collection.getStateByName("S1"),Collection.getStateByName("S4"),transitionTrigger,transitionGuard,transitionAction));
		Collection.addTransition(new Transition("T"+i++,transitionDesc,Collection.getStateByName("S2"),Collection.getStateByName("S5"),"C5",transitionGuard,transitionAction));
		Collection.addTransition(new Transition("T"+i++,transitionDesc,Collection.getStateByName("S4"),Collection.getStateByName("S6"),"C12",transitionGuard,transitionAction));
		Collection.addTransition(new Transition("T"+i++,transitionDesc,Collection.getStateByName("S6"),Collection.getStateByName("S3"),"C16",transitionGuard,transitionAction));


		/*
		addTransition(0,2);
		addTransition(0,3);
		addTransition(1,4);		//disconnected
		addTransition(3,5);
		addTransition(5,2);*/
		
		
		
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
	
	@SuppressWarnings("unused")
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
