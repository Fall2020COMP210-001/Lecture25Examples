package lec23;


public interface WeightedUndirectedEdge extends Comparable<WeightedUndirectedEdge> {

	Vertex getEndpointA();
	Vertex getEndpointB();
	boolean isEndpoint(Vertex v);
	Vertex getOtherEndpoint(Vertex v);
	int getWeight();
}
