package lec25;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class WeightedUndirectedGraphImpl implements WeightedUndirectedGraph {

	private Map<Vertex, List<WeightedUndirectedEdge>> _adj_list;

	
	public WeightedUndirectedGraphImpl() {

		_adj_list = new HashMap<Vertex, List<WeightedUndirectedEdge>>();
	}
	
	@Override
	public void addVertex(Vertex v) {
		if (hasVertex(v)) {
			return;
		}
		
		_adj_list.put(v, new ArrayList<WeightedUndirectedEdge>());
	}

	@Override
	public void removeVertex(Vertex v) {
		if (!hasVertex(v)) {
			return;
		}
		
		List<WeightedUndirectedEdge> edges = _adj_list.get(v);
		
		// For each edge, remove it from the list of edges associated
		// with the other vertex.
		for (WeightedUndirectedEdge e : edges) {
			Vertex other_vertex = e.getOtherEndpoint(v);
			_adj_list.get(other_vertex).remove(e);
		}
		
		_adj_list.remove(v);
	}

	@Override
	public boolean hasVertex(Vertex v) {
		return _adj_list.containsKey(v);
	}
	
	@Override
	public int getVertexCount() {
		return _adj_list.size();
	}

	@Override
	public Vertex[] getAdjacent(Vertex v) {
		if (!hasVertex(v)) {
			throw new RuntimeException("v not in graph");
		}
		List<WeightedUndirectedEdge> edges = _adj_list.get(v);
		Vertex[] adjacent = new Vertex[edges.size()];
		for (int i=0; i<edges.size(); i++) {
			adjacent[i] = edges.get(i).getOtherEndpoint(v);
		}
		return adjacent;
	}
	
	@Override
	public WeightedUndirectedEdge[] getEdgesOf(Vertex v) {
		if (!hasVertex(v)) {
			throw new RuntimeException("v not in graph");
		}
		List<WeightedUndirectedEdge> edge_list = _adj_list.get(v);
		return edge_list.toArray(new WeightedUndirectedEdge[edge_list.size()]);
	}
	
	@Override
	public void addEdge(Vertex a, Vertex b, int weight) {
		if (hasEdge(a,b)) {
			return; // Already have an edge between these vertices
		}
		
		WeightedUndirectedEdge edge = new WeightedUndirectedEdgeImpl(a, b, weight);
		
		_adj_list.get(a).add(edge);
		_adj_list.get(b).add(edge);
		
	}

	@Override
	public void removeEdge(Vertex a, Vertex b) {
		WeightedUndirectedEdge edge = findEdge(a,b);
		
		if (edge == null) {
			return;	// Edge does not exist.
		}
		
		_adj_list.get(a).remove(edge);
		_adj_list.get(b).remove(edge);
	}

	@Override
	public boolean hasEdge(Vertex a, Vertex b) {
		return findEdge(a,b) != null;
	}
	
	@Override
	public WeightedUndirectedEdge findEdge(Vertex a, Vertex b) {
		
		if ((!hasVertex(a)) || (!hasVertex(b))) {
			return null; // At least one of the vertices not even in the graph
		}
		
		List<WeightedUndirectedEdge> a_list = _adj_list.get(a);
		
		for (WeightedUndirectedEdge e : a_list) {
			if (e.getOtherEndpoint(a) == b) {
				return e;
			}
		}
		return null;
	}

	@Override
	public Set<Vertex> getVertices() {
		return _adj_list.keySet();
	}

	@Override
	public Set<WeightedUndirectedEdge> getEdges() {
		Set<WeightedUndirectedEdge> edge_set = new HashSet<WeightedUndirectedEdge>();
		for (List<WeightedUndirectedEdge> edge_list : _adj_list.values()) {
			edge_set.addAll(edge_list);
		}
		return edge_set;
	}

}
