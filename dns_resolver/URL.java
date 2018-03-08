/**
 * Jan Toma
 * CS310 Section 2
 */
package dns_resolver;

/**
 * A URL Object is a representation of the URL that we have been giving. 
 * It knows how to compare URLs!
 * 
 * @author Jan Toma CS310
 *
 */
public class URL implements Comparable<URL> {
	String u;
	public URL(String obj) {
		u = obj;
	}
	
	@Override
	public int compareTo(URL obj) {
		return u.compareTo(obj.u);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URL other = (URL) obj;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		return result;
	}
	
	public String toString() {
		return u;
	}
}