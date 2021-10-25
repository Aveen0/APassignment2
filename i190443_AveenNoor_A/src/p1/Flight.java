package p1;
public class Flight
{
	private Plane plane;
	private String flight_no;
	private String date;
	private String time;
	private String origin;
	private String destination;
	private boolean isDirect;
	private double fare;
	
	public Flight()
	{
		plane=new Plane();
		flight_no = "";
		date = "";
		time = "";
		origin = "";
		destination = "";
		isDirect = false;
		fare = 0.0;
	}
	
	public Flight(Plane p, String flight_no, String date, String time, String origin, String destination, boolean isDirect, double fare)
	{
		plane = p;
		this.flight_no = flight_no;
		this.date = date;
		this.time = time;
		this.origin = origin;
		this.destination = destination;
		this.isDirect = isDirect;
		this.fare = fare;
	}
	
	public void setPlane(Plane p)
	{
		plane = p;
	}
	
	public void setFlightNo(String flight_no)
	{
		this.flight_no = flight_no;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	
	public void setDestination(String destination)
	{
		this.destination = destination;
	}
	
	public void setIsDirect(boolean isDirect)
	{
		this.isDirect = isDirect;
	}
	
	public void setFare(double fare)
	{
		this.fare = fare;
	}
	
	//
	
	public Plane getPlane()
	{
		return plane;
	}
	
	public String getFlightNo()
	{
		return flight_no;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getTime()
	{
		return time;
	}
	
	public String getOrigin()
	{
		return origin;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public boolean getIsDirect()
	{
		return isDirect;
	}
	
	public double getFare()
	{
		return fare;
	}
	
	public void printFlights()
	{
		System.out.println();
		System.out.println(plane.printPlane());
		System.out.println("Flight Number: "+flight_no);
		System.out.println("Flight Date: "+date);
		System.out.println("Flight Time: "+time);
		System.out.println("Flight Origin: "+origin);
		System.out.println("Flight Destination: "+destination);
		if(isDirect)
			System.out.println("Flight Type: Direct");
		else
			System.out.println("Flight Type: Indirect");
		System.out.println("Flight Fare: "+fare);
	}

}
