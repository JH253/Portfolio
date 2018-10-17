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
	
	
	
	// Using order 1 crossover
	public Chromosome(Chromosome mother, Chromosome father, double mutationRate) {
		this.mutationRate = mutationRate;
		//REMOVE THIS - Testing purposes
		this.phenotype = null;
		Random randGen = new Random();
		//between 50-100 cuts
		int numCuts = randGen.nextInt(50) + 50;
		//Chops mother and futhers chromosome at random cuts
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
		
		List<List<Location>> child = new ArrayList<>();
		for(int i = 0; i < motherSnips.size(); i++) {
			if(mask.get(i) == 1) {
				child.set(i, motherSnips.get(i));
			}else {
				child.set(i, null);
			}
		}
		
		
		
		System.out.println("mask size: " + mask.size());
		System.out.println("motherSnips: " + motherSnips.size());
		System.out.println("fatherSnips: " + fatherSnips.size());
		System.out.println();


	}
	

	
	private List<Integer> bitMask(int numBits, Random randGen){
		List<Integer> bits = new ArrayList<>();
		for(int i = 0; i < numBits; i++) {
			bits.add((randGen.nextInt(100) <= 50)? 1 : 0);
		}
		return bits;
	}
	
	//Cuts randomly between 2 -> c.size - 2 
	private List<Integer> snipList(int numSnips, Random randGen, Chromosome c){
		List<Integer> cuts = new ArrayList<>();
		for(int i = 0; i < numSnips; i++) {
			int value = randGen.nextInt(c.phenotype.size() - 4) + 2;
			//No duplicate values so no empty lists
			while(cuts.contains(value)) {
				value = randGen.nextInt(c.phenotype.size() - 4) + 2;
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
