package lec25;

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



}
