import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ShortestRoundTripPath {

	public static void main(String[] args) {
		List<Location> locations;
		
		try {
			LocationFileReader lfr = new LocationFileReader(args[0]);
			locations = lfr.read();
			lfr.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found, please input valid path.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error when reading file.");
			e.printStackTrace();
		}
	}

}
