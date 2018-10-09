
public class Location implements Cloneable {
	
	private final double longitude;
	private final double latitude;

	public Location(double latitude, double longitude) {
		this.longitude = Math.toRadians(longitude);
		this.latitude = Math.toRadians(latitude);
	}

	@Override
	public String toString() {
		return String.format("(%.4f,%.4f)", 
				Math.toDegrees(latitude), 
				Math.toDegrees(longitude));
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	//distance in km (should be accurate to 2dp)
	public double distanceTo(Location locB) {
        double x1 = this.latitude;
        double y1 = this.longitude;
        double x2 = locB.latitude;
        double y2 = locB.longitude;

        double a = Math.pow(Math.sin((x2-x1)/2), 2)
                 + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2-y1)/2), 2);

        double angle = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

        return 6371* angle;

	}
	
	
	
	
}
