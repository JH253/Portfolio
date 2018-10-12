import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class ChromosomeBuilder {
	
	private List<Location> locations;

	public ChromosomeBuilder(LocationFileReader lfr) 
			throws IOException {
		locations = lfr.read();
 	}
	
	public PriorityQueue<Chromosome> getPopulation(int popNum){
		PriorityQueue<Chromosome> c = 
				new PriorityQueue<Chromosome>(
						popNum, (a,b) -> 
							(int)(a.fitness() - b.fitness())
				);
		
		for(int i = 0; i < popNum; i++) {
			List<Location> randLoc = new ArrayList<>();
			for(Location l : locations) {
				randLoc.add((Location)l.clone());
			}
			randomise(randLoc);
			c.add(new Chromosome(randLoc));
		}
		return c;
	}
	
	public void randomise(List<Location> l) {
		Random randGen = new Random();
		int swaps = l.size() / 3;
		for(int j = 0; j < swaps; j++) {
			swap(
					l,
					randGen.nextInt(l.size()), 
					randGen.nextInt(l.size())
				);
		}
	}
	
	public void swap(List<Location> l, int a, int b) {
		Location temp = l.get(a);
		l.set(a, l.get(b));
		l.set(b, temp);
	}
}
