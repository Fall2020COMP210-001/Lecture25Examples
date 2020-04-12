package lec23;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MST {

	public static WeightedUndirectedGraph primMST(WeightedUndirectedGraph g, Vertex start) {
		WeightedUndirectedGraph mst = new WeightedUndirectedGraphImpl();
		
		// Create priority queue of edges
		Queue<WeightedUndirectedEdge> pq = new PriorityQueue<WeightedUndirectedEdge>();
		
		// Add start vertex to result.
		mst.addVertex(start);

		// Add edges of start vertex in original graph to priority queue
		for (WeightedUndirectedEdge e : g.getEdgesOf(start)) {
			pq.add(e);
		}

		// Keep looking for more vertices to add to MST until the 
		// count of vertices in the MST equals the count in the original graph
		
		while (mst.getVertexCount() != g.getVertexCount()) {
			
			// Get the smallest edge from the original graph
			// that we haven't yet processed.
			WeightedUndirectedEdge e = pq.remove();

			// Unpack the vertexes of the edge for easier access
			Vertex a = e.getEndpointA();
			Vertex b = e.getEndpointB();
			
			if (mst.hasVertex(a) && mst.hasVertex(b)) {
				// Both vertices are already in MST, skip processing this edge.
				continue;
			}


			// One or the other is not in MST, figure out which and add it
			// to the MST and recreate the edge in the MST.
			Vertex out_vertex = mst.hasVertex(a) ? b : a;
			
			mst.addVertex(out_vertex);
			mst.addEdge(a, b, e.getWeight());

			// Add any edges from original graph of the out vertex
			// into our priority queue of edges to process

			for (WeightedUndirectedEdge out_edge: g.getEdgesOf(out_vertex)) {
				pq.add(out_edge);
			}
		}
		
		return mst;
	}
	
	public static WeightedUndirectedGraph kruskalMST(WeightedUndirectedGraph g) {
		WeightedUndirectedGraph mst = new WeightedUndirectedGraphImpl();
		
		// Add all vertices from original graph to result graph.
		// Give each one a unique "kruskal" id.
		// This is how we are going to detect cycles.
		
		int next_id = 0;
		for(Vertex v : g.getVertices()) {
			v.setKruskalID(next_id);
			mst.addVertex(v);
			next_id++;
		}
		
		// Create priority queue of edges from original graph.
		Queue<WeightedUndirectedEdge> pq = new PriorityQueue<WeightedUndirectedEdge>(g.getEdges());
		

		// Process edges from the priority queue until 
		// we have added enough edges to the MST. We know the
		// MST should have one fewer edge than the number of vertexes
		
		int edge_count = 0;		
		while (edge_count < mst.getVertexCount()-1) {
			
			// Get next edge from original graph to consider.
			WeightedUndirectedEdge e = pq.remove();

			// Unpack vertex endpoints for easy reference.
			Vertex a = e.getEndpointA();
			Vertex b = e.getEndpointB();

			// Only add this edge to MST if the endpoints have
			// different kruskal id's. The kruskal id of all vertices
			// connected in the MST should be the same value (whatever it is).
			// That is our invariant for detecting cycles.
			
			if (a.getKruskalID() != b.getKruskalID()) {
				
				// We can add this edge to MST since it will not create a cycle.
				mst.addEdge(a, b, e.getWeight());
				edge_count++;
				
				// Relabel the vertex with the larger id and any vertexes connected
				// to it in the MST with the smaller id. This will ensure that 
				// all vertexes of the components of the MST we just joined will all
				// have the smaller id.
				//
				// To do this, create a "relabel" queue of vertexes that need to be
				// relabeled. Process the queue by relabeling the next vertex from the
				// queue with the smaller id and adding any of its neighbors that have
				// the wrong id to the queue. Keep processing until queue is empty.
				
				int min_id = a.getKruskalID() < b.getKruskalID() ? a.getKruskalID() : b.getKruskalID();

				Queue<Vertex> relabel_queue = new LinkedList<Vertex>();
				if (a.getKruskalID() != min_id) {
					relabel_queue.add(a);
				} else {
					relabel_queue.add(b);
				}
				
				while (relabel_queue.size() > 0) {
					Vertex v = relabel_queue.remove();
					v.setKruskalID(min_id);
					for (Vertex adj : mst.getAdjacent(v)) {
						if (adj.getKruskalID() != min_id) {
							relabel_queue.add(adj);
						}
					}
				}
			}
		}
		
		return mst;
	}
}
