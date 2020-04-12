package lec23;

public class WeightedUndirectedEdgeImpl implements WeightedUndirectedEdge {

	private Vertex _end_a;
	private Vertex _end_b;
	private int _weight;
	
	public WeightedUndirectedEdgeImpl(Vertex end_a, Vertex end_b, int weight) {
		_end_a = end_a;
		_end_b = end_b;
		_weight = weight;
	}
	
	@Override
	public Vertex getEndpointA() {
		return _end_a;
	}

	@Override
	public Vertex getEndpointB() {
		return _end_b;
	}

	@Override
	public int getWeight() {
		return _weight;
	}

	@Override
	public boolean isEndpoint(Vertex v) {
		return (v == _end_a || v == _end_b);
	}

	@Override
	public Vertex getOtherEndpoint(Vertex v) {
		if (!isEndpoint(v)) {
			throw new RuntimeException("Vertex is not an endpoint of edge");
		}
		
		return (v == _end_a) ? _end_b : _end_a;
	}

	@Override
	public int compareTo(WeightedUndirectedEdge other) {
		
		if (_weight < other.getWeight()) {
			return -1;
		} else if (_weight > other.getWeight()) {
			return 1;
		} else {
			return 0;
		}
	}

}
