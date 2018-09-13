
public class Triple <A, B, C>{

	public final A a;
	public final B b;
	public final C c;
	
	public Triple(A a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(
				"" + a.hashCode() + 
				"" + b.hashCode() + 
				"" + c.hashCode()
				);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if(obj instanceof Triple) {
			return ((Triple<A, B, C>) obj).a.equals(this.a) &&
					((Triple<A, B, C>) obj).b.equals(this.b) &&
					((Triple<A, B, C>) obj).c.equals(this.c);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "(" + a.toString() + 
				", " + b.toString() + 
				", " + c.toString() + ")"; 
	}
	
}
