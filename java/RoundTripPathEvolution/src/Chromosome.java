import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Chromosome {
	
	private final List<Location> phenotype;
	private double mutationRate;

	
	public Chromosome(List<Location> phenotype) {
		this.phenotype = phenotype;
		this.mutationRate = 0;
	}
	
	//http://user.ceng.metu.edu.tr/~ucoluk/research/publications/tspnew.pdf
	
	// using PMX
	public Chromosome(Chromosome mother, Chromosome father, double mutationRate) {
		this.mutationRate = mutationRate;
		//REMOVE THIS
		this.phenotype = null;
		Random randGen = new Random();
		//between 50-100 cuts
		int numCuts = randGen.nextInt(50) + 50;
		List<Integer> snips = snipList(numCuts, randGen, mother);
		List<List<Location>> motherSnips = new ArrayList<>();
		List<List<Location>> fatherSnips = new ArrayList<>();
		
		//from start to first
		motherSnips.add(mother.snip(0, snips.get(0)));
		fatherSnips.add(father.snip(0, snips.get(0)));
		
		for(int i = 0, j = 1; j < snips.size(); i++, j++) {
			motherSnips.add(mother.snip(snips.get(i), snips.get(j)));
			fatherSnips.add(father.snip(snips.get(i), snips.get(j)));
		}
		//from last to end
		motherSnips.add(mother.snip(snips.get(snips.size() - 1), mother.phenotype.size()));
		fatherSnips.add(father.snip(snips.get(snips.size() - 1), father.phenotype.size()));
		
		List<Integer> mask = bitMask(snips.size() + 1, randGen);
		
		System.out.println("mask size: " + mask.size());
		System.out.println("motherSnips: " + motherSnips.size());
		System.out.println("fatherSnips: " + fatherSnips.size());
		System.out.println();

		
		//loop through both the father and the masks
		for(int i = 0; i < mask.size(); i++) {
			// if mask[i] == 1 then 
			if(mask.get(i).equals(1)) {
				//replace motherclone[i] with fatherclone[i]
				replace(motherSnips, fatherSnips, i);
				System.out.println("Mask is 1");
			}
		}
		
		//rename motherclone to child
		//flatten child
		//phenotype = child
	}
	
	//replace(f,m,i):
	private void replace(List<List<Location>> motherSnips, List<List<Location>> fatherSnips, int i) {
		//for j in m[i]:
		for(int j = 0; j < motherSnips.get(i).size(); j++) {
			//v = find f[i][j] in m
			//swap v with m[i][j]				
		}
	}
	
	private List<Integer> bitMask(int numBits, Random randGen){
		List<Integer> bits = new ArrayList<>();
		for(int i = 0; i < numBits; i++) {
			bits.add((randGen.nextInt(100) <= 50)? 1 : 0);
		}
		return bits;
	}
	
	private List<Integer> snipList(int numSnips, Random randGen, Chromosome mother){
		List<Integer> cuts = new ArrayList<>();
		for(int i = 0; i < numSnips; i++) {
			int value = randGen.nextInt(mother.phenotype.size());
			while(cuts.contains(value)) {
				value = randGen.nextInt(mother.phenotype.size() - 4) + 2;
			}
			cuts.add(value);
		}
		cuts.sort(Comparator.naturalOrder());
		return cuts;
	}
	
	//This should clone as well
	//cuts from a(inclusive) to b(exclusive)
	private List<Location> snip(int a, int b){
		List<Location> temp = new ArrayList<>();
		for(Location l: phenotype.subList(a, b)) {
			temp.add((Location) l.clone());
		}
		return phenotype.subList(a, b);
	}
	
	@Override
	public String toString() {
		String locations = "";			
		for(int i = 0; i < phenotype.size(); i++) {
			locations += phenotype.get(i).toString() + "\n";
		}
		return locations;
	}
	
	//scores this chromosome (the total round trip distance) 
	public double fitness() {
		double totalDistance = 0;
		for(int i = 0, j = 1; i < phenotype.size(); i++, j++) {
			totalDistance += phenotype.get(i)
					.distanceTo(phenotype.get(j % phenotype.size()));	
		}
		return totalDistance;
	}
}
