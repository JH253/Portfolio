import java.util.List;

public class Chromosome {
	
	private final List<Location> phenotype;

	
	public Chromosome(List<Location> phenotype) {
		this.phenotype = phenotype;
	}
	
	@Override
	public String toString() {
		String locations = "";			
		for(int i = 0; i < phenotype.size(); i++) {
			locations += phenotype.get(i).toString() + "\n";
		}
		return locations;
	}
	
	//scores this chromosome (the total round trip distance) NEEDS TESTING
	public double fitness() {
		double totalDistance = 0;
		for(int i = 0, j = 1; i < phenotype.size(); i++, j++) {
			totalDistance += phenotype.get(i)
					.distanceTo(phenotype.get(j % phenotype.size()));
		}
		return totalDistance;
	}
	
	
}
