import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// This class' purpose is to read in locations from file
public class LocationFileReader {
	
	private Stream<String> slocations;
	
	public LocationFileReader(String path) 
			throws IOException {
		//Creates a stream from the input path,
		//Note: Streams are lazy so strings aren't computed
		slocations = Files.lines(Paths.get(path));
	}

	//Reads the file and returns a list (so the stream could be closed later).
	public List<Location> read() 
			throws IOException {
		return slocations
		.map((x) -> {
			//Splits the string at the comma, splitting long and lat
			String[] locs = x.split(",");
			//Returns a new location using the splitted values
			return new Location(locs[0],locs[1]); })
		//Collects to a list, so we can close the input stream.
		.collect(Collectors.toList());
	}
	
	//Closes the stream.
	public void close() {
		//Closes the file string stream.
		slocations.close();
	}
	
}
