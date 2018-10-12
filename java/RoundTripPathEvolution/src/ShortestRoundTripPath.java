import java.io.IOException;
import java.util.PriorityQueue;

public class ShortestRoundTripPath {

	public ShortestRoundTripPath(String path) throws IOException {
		LocationFileReader lfr = new LocationFileReader(path);
		ChromosomeBuilder cb = new ChromosomeBuilder(lfr);
		
		PriorityQueue<Chromosome> population = cb.getPopulation(50);
		
		new Chromosome(population.poll(), population.poll(), 0.0d);
		
	}

	public static void main(String[] args) {

		try {
			new ShortestRoundTripPath(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
