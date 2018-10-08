
public class Location implements Cloneable {
	
	private final double longitude;
	private final double latitude;

	public Location(String longitude, String latitude) {
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
	}

	@Override
	public String toString() {
		return String.format("(%.4f,%.4f)", longitude,latitude);
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}
