package graph;

import java.util.List;
//import java.util.Set;

public interface IGraph {
	public INode getRoot();
	public INode addNode(INode node);
	public INode deleteNode(INode node);
	public boolean hasNode(INode node);
	public int getNumberOfNodes();
	public IEdge addEdge(IEdge edge);
	public IEdge deleteEdge(IEdge edge);
	public boolean hasEdge(IEdge edge);
	public int getNumberOfEdges();
	public List<INode> getNodeSet();
	public List<IEdge> getEdgeSet();
	public INode getNodeById(int id);
}
