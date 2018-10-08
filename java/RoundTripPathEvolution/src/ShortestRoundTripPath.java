import java.io.IOException;
import java.util.List;

public class ShortestRoundTripPath {

	public ShortestRoundTripPath(String path) throws IOException {
		LocationFileReader lfr = new LocationFileReader(path);
		ChromosomeBuilder cb = new ChromosomeBuilder(lfr);
	}

	public static void main(String[] args) {
		
		try {
			new ShortestRoundTripPath(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
