package lec23;

public class LectureExample {

	public static void main(String[] args) {
		Vertex v1 = new LabeledVertex("1");
		Vertex v2 = new LabeledVertex("2");
		Vertex v3 = new LabeledVertex("3");
		Vertex v4 = new LabeledVertex("4");
		Vertex v5 = new LabeledVertex("5");
		Vertex v6 = new LabeledVertex("6");
		Vertex v7 = new LabeledVertex("7");
		
		WeightedUndirectedGraph g = new WeightedUndirectedGraphImpl();
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);
		g.addVertex(v5);
		g.addVertex(v6);
		g.addVertex(v7);

		g.addEdge(v1, v2, 2);
		g.addEdge(v1, v3, 4);
		g.addEdge(v1, v4, 1);
		g.addEdge(v2, v4, 3);
		g.addEdge(v2, v5, 10);
		g.addEdge(v3, v4, 2);
		g.addEdge(v3, v6, 5);
		g.addEdge(v4, v5, 7);
		g.addEdge(v4, v6, 8);
		g.addEdge(v4, v7, 4);
		g.addEdge(v5, v7, 6);
		g.addEdge(v6, v7, 1);
		
		WeightedUndirectedGraph kruskal_result = MST.kruskalMST(g);
		printGraph(kruskal_result);
		WeightedUndirectedGraph prim_result = MST.primMST(g, v1);
		printGraph(prim_result);
	}
	
	public static void printGraph(WeightedUndirectedGraph g) {
		System.out.println("Graph " + g.toString() + ":");
		for (Vertex v : g.getVertices()) {
			System.out.print("  " + v.toString() + " connected to: ");
			for (Vertex adj : g.getAdjacent(v)) {
				System.out.print(adj.toString() + " ");
			}
			System.out.println();
		}
	}
}
