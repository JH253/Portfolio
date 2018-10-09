import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShortestRoundTripPath {

	public ShortestRoundTripPath(String path) throws IOException {
		LocationFileReader lfr = new LocationFileReader(path);
		ChromosomeBuilder cb = new ChromosomeBuilder(lfr);
		
		List<Chromosome> population = cb.getPopulation(50);
		for(Chromosome c: population) {
			System.out.println(c);
		}
	}

	public static void main(String[] args) {
		Location a = new Location(49.6083333d, 6.4058333d);
		Location b = new Location(49.8530556d, 6.1872222d);
		
		System.out.println("Distance between:");
		System.out.println("a\t:\t" + a);
		System.out.println("b\t:\t" + b);
		System.out.println("distance: " + a.distanceTo(b) + "km");
//		try {
//			new ShortestRoundTripPath(args[0]);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
