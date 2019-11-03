package assignment4;

public class Edge<V>
{



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
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
		Edge<V> other = (Edge<V>) obj;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}

	private V u;
	private V v;
	
	public Edge(V u, V v)
	{
		this.u = u;
		this.v = v;
	}
	public void setU(V u)
	{
		this.u = u;
	}
	
	public void setV(V v)
	{
		this.v = v;
	}
	
	public V getU()
	{
		return u;
	}
	
	public V getV()
	{
		return v;
	}
	
	public String toString()
	{
		return "(" + u + "," + v + ") ";
	}
	
	

}