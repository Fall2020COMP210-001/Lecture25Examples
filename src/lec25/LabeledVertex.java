package lec25;


public class LabeledVertex implements Vertex {

	private String _label;
	private int _kruskal_id;
	
	public LabeledVertex(String label) {
		if (label == null) {
			throw new IllegalArgumentException();
		}
		
		_label = label;		
		_kruskal_id = -1;
	}
	
	@Override
	public String toString() {
		return _label;
	}
	
	public String getLabel() {
		return _label;
	}

	@Override
	public int getKruskalID() {
		return _kruskal_id;
	}

	@Override
	public void setKruskalID(int id) {
		_kruskal_id = id;
	}
}
