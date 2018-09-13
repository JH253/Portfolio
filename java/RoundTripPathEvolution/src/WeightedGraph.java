import java.util.Set;

public class WeightedGraph <T> {

	private Set<T> vertices;
	private Set<Triple<T, Integer, T>> edges; 
	
	public WeightedGraph(Set<T> vertices, Set<Triple<T, Integer, T>> edges) 
			throws WeightedGraphCondtionsException 
	{
		meetsPrecondtions(vertices, edges);
		this.vertices = vertices;
		this.edges = edges;
	}
	
	protected void meetsPrecondtions(Set<T> vertices, Set<Triple<T, Integer, T>> edges) 
			throws WeightedGraphCondtionsException
	{
		boolean edgeCondtion = edgesConnectExistingVerticies(vertices, edges);
		if(!edgeCondtion) {
			throw new WeightedGraphCondtionsException("edge connecting non existing vertices");
		}
	} 
	
	private boolean edgesConnectExistingVerticies(Set<T> vertices, Set<Triple<T, Integer, T>> edges) {
		if(vertices.size() == 0 && edges.size() > 0) {
			return false;
		} else {
			for(Triple<T, Integer, T> e: edges) {
				if(vertices.contains(e.a) && vertices.contains(e.c)) {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
}