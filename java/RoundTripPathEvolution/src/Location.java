
public class Location implements Cloneable {
	
	private final double longitude;
	private final double latitude;

	public Location(String longitude, String latitude) {
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
	}
	
	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
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
	
	@Override
	public int hashCode() {
		String slong = ("" + longitude);
		String first = slong.substring(3, slong.indexOf('.')) +
				slong.substring(slong.indexOf('.') + 1, slong.length());
		String slat = ("" + latitude);
		String second = "" + slat.charAt(slat.indexOf('.')  - 1);
		
		return Integer.parseInt(first + second);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Location) {
			return this.longitude == ((Location) obj).longitude &&
					this.latitude == ((Location) obj).latitude;
		} else {
			return false;
		}
	}
	
}
