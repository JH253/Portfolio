import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// This class' purpose is to read in locations from file
public class LocationFileReader {
	
	private List<String> allLines;
	private Supplier<Stream<String>> supplierLocations;
	
	public LocationFileReader(String path) 
			throws IOException {
		//Reads all the lines into memory
		allLines = Files.readAllLines(Paths.get(path));
		supplierLocations = () -> allLines.stream();
	}

	//Reads the file and returns a list (so the stream could be closed later).
	public List<Location> read() 
			throws IOException {
		return supplierLocations
		.get()
		.map((x) -> {
			//Splits the string at the comma, splitting long and lat
			String[] locs = x.split(",");
			//Returns a new location using the splitted values
			return new Location(Double.parseDouble(locs[0])/1000.0,
					Double.parseDouble(locs[1])/1000.0); 
			}
		)
		//Collects to a list, so we can close the input stream.
		.collect(Collectors.toList());
	}
	
	
}
