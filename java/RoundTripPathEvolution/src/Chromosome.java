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
	
	
}
