package p1;
import java.util.Random;

public class Reservation
{
	private int bookingid;
	private int customer_id;
	private Flight flight;
	private int seat_no;
	private CardPayment payment;
	
	public Reservation()
	{
		bookingid = 0;
		customer_id = 0;
		flight = new Flight();
		seat_no = 0;
		payment = new CardPayment();
	}
	
	public Reservation(int customer_id, Flight f, int seat_no, CardPayment p)
	{
		Random r = new Random();
		bookingid = r.nextInt(100000);
		this.customer_id = customer_id;
		flight = f;
		this.seat_no = seat_no;
		payment = p;
	}
	
	public void printTicket()
	{
		System.out.println(" --- TICKET --- \n");
		System.out.println("Booking ID: "+bookingid);
		System.out.println("Customer ID: "+customer_id);
		flight.printFlights();
		System.out.println("Seat Number: "+seat_no);
		System.out.println("Payment Type: Credit Card");
	}
	
	public void setCustomerID(int customer_id)
	{
		this.customer_id = customer_id;
	}
	
	public void setFlight(Flight f)
	{
		flight = f;
	}
	
	public void setSeatNo(int seat_no)
	{
		this.seat_no = seat_no;
	}
	
	public void setCardPayment(CardPayment cp)
	{
		payment = cp;
	}
	
	//
	
	public int getBookingID()
	{
		return bookingid;
	}
	
	public int getCustomerID()
	{
		return customer_id;
	}
	
	public Flight getFlight()
	{
		return flight;
	}
	
	public int getSeatNo()
	{
		return seat_no;
	}
	
	public CardPayment getCardPayment()
	{
		return payment;
	}


}
