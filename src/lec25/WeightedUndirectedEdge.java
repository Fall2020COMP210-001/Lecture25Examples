package lec25;


public interface WeightedUndirectedEdge extends Comparable<WeightedUndirectedEdge> {

	Vertex getEndpointA();
	Vertex getEndpointB();
	int getWeight();

	default boolean isEndpoint(Vertex v) {

		return (v == getEndpointA() || v == getEndpointB());
	}

	default Vertex getOtherEndpoint(Vertex v) {
		if (!isEndpoint(v)) {
			throw new RuntimeException("Vertex is not an endpoint of edge");
		}

		return (v == getEndpointA()) ? getEndpointB() : getEndpointA();
	}

	default int compareTo(WeightedUndirectedEdge other) {

		if (getWeight() < other.getWeight()) {
			return -1;
		} else if (getWeight() > other.getWeight()) {
			return 1;
		} else {
			return 0;
		}
	}
}
