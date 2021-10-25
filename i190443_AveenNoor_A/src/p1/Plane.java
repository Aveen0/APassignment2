package p1;
public class Plane
{
	private String plane_no;
	private String plane_type;
	private int capacity;
	
	public Plane()
	{
		plane_no = "";
		plane_type = "";
		capacity = 0;
	}
	
	public Plane(String plane_no, String plane_type, int capacity)
	{
		this.plane_no = plane_no;
		this.plane_type = plane_type;
		this.capacity = capacity;
	}
	
/*	public void setPlaneNo(String plane_no)
	{
		this.plane_no = plane_no;
	}
	
	public void setPlaneType(String plane_type)
	{
		this.plane_type = plane_type;
	}
	
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	} */
	
	public String printPlane()
	{
		String str = "";
		str+="\nPlane Number: "+plane_no;
		str+="\nPlane Type: "+plane_type;
		str+="\nPlane Capacity: "+capacity;
		
		return str;
	}
	
	
}
