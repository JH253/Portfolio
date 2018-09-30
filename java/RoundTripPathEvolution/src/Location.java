
public class Location {
	
	private final double longitude;
	private final double latitude;

	public Location(String longitude, String latitude) {
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
	}

	
	
}
