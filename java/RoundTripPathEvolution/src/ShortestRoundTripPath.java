import java.io.IOException;
import java.util.PriorityQueue;

public class ShortestRoundTripPath {

	public ShortestRoundTripPath(String path) throws IOException {
		// Set up for testing chromosome crossing
		LocationFileReader lfr = new LocationFileReader(path);
		ChromosomeBuilder cb = new ChromosomeBuilder(lfr);
		
		PriorityQueue<Chromosome> population = cb.getPopulation(50);
		
		new Chromosome(population.poll(), population.poll(), 0.0d);
		
	}

	public static void main(String[] args) {

		try {
			//Testing the shortest path
			new ShortestRoundTripPath(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
