import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ChromosomeBuilder {
	
	private LocationFileReader lfr;
	private List<Location> locations;
//	private HashMap<Integer,Location> enumeratedLoc;
	
	public ChromosomeBuilder(LocationFileReader lfr) 
			throws IOException {
		this.lfr = lfr;
		HashMap<Integer,Location> enumeratedLoc = new HashMap<>();
		locations = lfr.read();
//		ListIterator<Location> it = locations.listIterator();
//		while(it.hasNext()) {
//			enumeratedLoc.put(it.nextIndex(), it.next());
//		}
		
		List<Chromosome> p = getPopulation(50);
 	}
	
	public List<Chromosome> getPopulation(int popNum){
		List<Chromosome> c = new ArrayList<>();
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
