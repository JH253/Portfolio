import java.util.Set;

public class Tour <T> extends WeightedGraph<T> {

	public Tour(Set<T> vertices, Set<Triple<T, Integer, T>> edges) throws WeightedGraphCondtionsException {
		super(vertices, edges);
	}
	
	@Override
	protected void meetsPrecondtions(Set<T> vertices, Set<Triple<T, Integer, T>> edges) 
			throws WeightedGraphCondtionsException
	{
		super.meetsPrecondtions(vertices, edges);
		// ensure a loop is created.
	}

}
