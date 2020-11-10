package lec25;


import java.util.Set;

public interface WeightedUndirectedGraph {

	void addVertex(Vertex v);
	void removeVertex(Vertex v);
	boolean hasVertex(Vertex v);
	int getVertexCount();
	Vertex[] getAdjacent(Vertex v);
	WeightedUndirectedEdge[] getEdgesOf(Vertex v);

	void addEdge(Vertex a, Vertex b, int weight);
	void removeEdge(Vertex a, Vertex b);
	boolean hasEdge(Vertex a, Vertex b);
	WeightedUndirectedEdge findEdge(Vertex a, Vertex b);
	
	Set<Vertex> getVertices();
	Set<WeightedUndirectedEdge> getEdges();	
}
