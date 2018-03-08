/**
 * Jan Toma
 * CS310 Section 2
 */
package dns_resolver;

public class IPAddress implements Comparable<IPAddress> {

	int network;
	int subnet;
	int subnet2;
	int host;
	String ip;

	/**
	 * The constructor for the IPAddress class.
	 * 
	 * @param ip the dotted-decimal IP address.
	 */
	public IPAddress(String ip) {
		this.ip = ip;
		String data[] = ip.split("\\.");
		try{
			network = Integer.parseInt(data[0]);
			subnet = Integer.parseInt(data[1]);
			subnet2 = Integer.parseInt(data[2]);
			host = Integer.parseInt(data[3]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("IPAddress: Index out of bounds");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public int compareTo(IPAddress o) {
		return ip.compareTo(o.ip);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + host;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + network;
		result = prime * result + subnet;
		result = prime * result + subnet2;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPAddress other = (IPAddress) obj;
		if (host != other.host)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (network != other.network)
			return false;
		if (subnet != other.subnet)
			return false;
		if (subnet2 != other.subnet2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return network + "." + subnet + "." + subnet2 + "." + host;
	}
}
