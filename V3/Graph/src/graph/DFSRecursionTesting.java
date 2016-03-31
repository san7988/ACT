package graph;
import java.util.ArrayList;
import java.util.Stack;

public class DFSRecursionTesting {
    private Stack<IEdge> stack = new Stack<IEdge> ();
    private ArrayList<Stack<IEdge>> al = new ArrayList<Stack<IEdge>>();
    private void dfs_rec(IGraph g, boolean[] visited, INode v){
    	//System.out.print(v.getId()+" ");
        visited[v.getId()] = true;		//mark visited and print
        //printVisited(visited);
        //stack.push(v);
        //System.out.println(stack.toString());
        boolean deadEnd = true;
        
        for(IEdge w : v.getOutgoingEdgeList()){	//for cols (elt) in v's row (search unvisited adjacent node)
            deadEnd = false;
            if(!visited[w.getHead().getId()]){
            	stack.push(w);
                dfs_rec(g, visited, w.getHead());		//for unvisited elt do same thing (hold the loop)
            }
            else									//when cycle comes
            {
            	stack.push(w);
            	spitOut(stack);
            	stack.pop();
			}
        }
        if(deadEnd)
        {
        	//System.out.println("Muhahaha");
        	spitOut(stack);
        }
        if(!stack.isEmpty())			//to take care of the root
        	stack.pop();				//pop the edge that brought me to this node
    }
    public void printVisited(boolean [] b)
    {
    	for(int i=0;i<b.length;i++)
    		System.out.print(b[i]?"1":"0");				//java.lang.Boolean.toString(b[i]));
    	System.out.println();
    }

    // Usually dfs_rec() would be sufficient. However, if we don't want to pass
    // a boolean array to our function, we can use another function dfs().
    // We only have to pass the adjacency list and the source node to dfs(),
    // as opposed to dfs_rec(), where we have to pass the boolean array additionally.
    public ArrayList<Stack<IEdge>> dfs(IGraph g, INode s){
        int n = g.getNumberOfNodes();						//no of rows (nodes)
        boolean[] visited = new boolean[n];				//make visited array
        //printVisited(visited);
        dfs_rec(g, visited, s);					//Pass Graph,visited array, src
        print(al);		//STUB AVAILABLE (TODO, IN PROGRESS,STUB AVAILABLE, OBSOLETE)
        return al;
    }

    private Stack<IEdge> getCopy(Stack<IEdge> stack)
    {
    	Stack<IEdge> stack1 = new Stack<IEdge>();
    	Stack<IEdge> tempStack= new Stack<IEdge>();
    	//System.out.println("Input Stack is " + stack.toString());
    	while(!stack.empty())
    	{
    		IEdge a = stack.pop();
    		tempStack.push(a);
    	}
    	while(!tempStack.empty())
    	{
    		IEdge a = tempStack.pop();
    		stack.push(a);
    		stack1.push(a);
    	}
    	//System.out.println("Output Stack is " + stack1.toString());
		return stack1;    	
    }
    
	private void spitOut(Stack<IEdge> stack)
	{
		//System.out.println(stack.toString());
		Stack<IEdge> stack1 = getCopy(stack);
		al.add(stack1);
		//System.out.println(al.toString());
	}
	public void print(ArrayList<Stack<IEdge>> al)
	{
		//System.out.println(al.size());
		for (int i = 0; i < al.size(); i++) {
			for(int j=0;j<al.get(i).size();j++){
				System.out.print(al.get(i).get(j).getEdgeId()+" ");
				//System.out.print("\tAddDetail:"+Transition.class.cast(al.get(i).get(j)).getTrigger()+"\t\t");
			}
			System.out.println();
			
        }
	}
/*
    // ----------------------------------------------------------------------
    // Testing our implementation
    public static void main(String[] args) {

        // Create adjacency list representation
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();					//make rows
        final int n = 7;

        // Add an empty adjacency list for each vertex
        for(int v=0; v<n; v++){
            adjLists.add(new ArrayList<Integer>());														//make columns
        }

        // insert neighbors of vertex 0 into adjacency list for vertex 0
        adjLists.get(0).add(1);																			//add columns to 1st row
        adjLists.get(0).add(2);
        adjLists.get(0).add(3);

        // insert neighbors of vertex 1 into adjacency list for vertex 1
        adjLists.get(1).add(5);
        adjLists.get(1).add(6);

        // insert neighbors of vertex 2 into adjacency list for vertex 2
        adjLists.get(2).add(4);

        // insert neighbors of vertex 3 into adjacency list for vertex 3
        adjLists.get(3).add(2);
        adjLists.get(3).add(4);

        // insert neighbors of vertex 4 into adjacency list for vertex 4
        adjLists.get(4).add(1);

        // insert neighbors of vertex 5 into adjacency list for vertex 5
        // -> nothing to do since vertex 5 has no neighbors

        // insert neighbors of vertex 6 into adjacency list for vertex 5
        adjLists.get(6).add(4);

        // Print vertices in the order in which they are visited by dfs()
        DFSRecursionTesting dfsRecursionTesting= new DFSRecursionTesting();
        dfsRecursionTesting.dfs(adjLists, 0);																				//pass arraylist (adj mat and src)

    }
*/
}