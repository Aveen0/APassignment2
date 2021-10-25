package p1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Driver
{
	public static void main(String[] args) throws IOException, NumberFormatException, IndexOutOfBoundsException, InputMismatchException
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter customer ID: ");
		String id = input.nextLine();
		
		System.out.print("Enter customer Password: ");
		String password = input.nextLine();
		
		try
		{
			File customer_file=new File("customer.txt");
			FileReader fr=new FileReader(customer_file);
			BufferedReader br=new BufferedReader(fr);
			String line;
			String n = br.readLine();
			int sz = 0;
			try
			{
				sz = Integer.parseInt(n);
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Wrong Conversion!");
			}
			
			Customer customers[] = new Customer[sz];
			int i=0;
			while((line=br.readLine())!=null)  
			{
				String[] cust_pieces = line.split(",");
				
				customers[i] = new Customer(Integer.parseInt(cust_pieces[0]), cust_pieces[1], cust_pieces[2], Integer.parseInt(cust_pieces[3]), cust_pieces[4], cust_pieces[5], cust_pieces[6]);
				i++;
			}
			fr.close();
			
			boolean found = false;
			int int_id = 0;
			for(int j=0; j<Integer.parseInt(n); j++)
			{
				try
				{
					int_id = Integer.parseInt(id);
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Wrong Conversion!");
				}
				
				if(int_id==customers[j].getCustomerID() && password.equals(customers[j].getCustomerPassword()))
				{
					found = true;
					break;
				}
			}
			
			if(found)
			{
				System.out.println("Logged in successfully!");
				int option;
				
				do
				{
				
					option = menu();			
					
					if(option == 1)			// View all flights
					{
						viewAllFlights();
					}
					else if(option == 2)	// Make Reservation
					{
						System.out.println("WELCOME TO RESERVATION MENU.\n");
						viewAllFlights();
						
						input.nextLine();
						System.out.println("\n\nEnter Flight Number for your reservation: ");
						String res_fl_no = input.nextLine();
						
						Flight res_fl = getParticularFlight(res_fl_no);
						
						if(res_fl.getFlightNo().equals(""))
						{
							System.out.println("Wrong Flight Number Entered.");
						}
						else
						{
							int res_seat_no;
							String res_card_no;
							
							do
							{
								System.out.println("Enter Seat Number (1-30): ");
								res_seat_no = input.nextInt();
								if(res_seat_no<0 || res_seat_no>30)
								{
									System.out.println("Wrong Seat Number entered. Try Again!");
								}
							}while(res_seat_no<0 || res_seat_no>30);
							
							System.out.println("Enter Payment Information...");
							input.nextLine();
							System.out.println("Enter Card Number: ");
							res_card_no = input.nextLine();
							int exp_month=0, exp_year = 0,cvv = 0;
							
							do
							{
								input.nextLine();
								System.out.println("Enter Card Expiry Month: ");
								try
								{
									exp_month = input.nextInt();
								
								
									if(exp_month<1 || exp_month>12)
									{
										System.out.println("Wrong Expiry Month");
									}
								}
								catch(InputMismatchException e)
								{
									System.out.println("Only integers are allowed!");
								}
							}while(exp_month<1 || exp_month>12);
							
							do
							{
								input.nextLine();
								System.out.println("Enter Card Expiry Year: ");
								try
								{
									exp_year = input.nextInt();
									if(exp_year<2021)
									{
										System.out.println("Wrong Expiry Year");
									}
								}
								catch(InputMismatchException e)
								{
									System.out.println("Only integers are allowed!");
								}
							}while(exp_year<2021);
							
							do
							{
								input.nextLine();
								System.out.println("Enter Card CVV: ");
								try
								{
									cvv = input.nextInt();
									if(cvv<100)
									{
										System.out.println("Wrong CVV");
									}
								}
								catch(InputMismatchException e)
								{
									System.out.println("Only integers are allowed!");
								}
							}while(cvv<100);
							
							CardPayment c_p = new CardPayment(res_card_no, exp_month, exp_year, cvv);
							
							Reservation reservation = new Reservation(Integer.parseInt(id),res_fl,res_seat_no,c_p);
							
							String file_data = "";
							file_data += reservation.getBookingID()+",";
							file_data += reservation.getCustomerID()+",";
							file_data += reservation.getFlight().getFlightNo()+",";
							file_data += reservation.getSeatNo()+",";
							file_data += reservation.getCardPayment().getCardNumber()+",";
							file_data += reservation.getCardPayment().getExpiryMonth()+","+reservation.getCardPayment().getExpiryYear()+",";
							file_data += reservation.getCardPayment().getCVV();
							
							FileWriter fw = null;
					        BufferedWriter bw = null;
					        PrintWriter pw = null;

					        try {
					            fw = new FileWriter("reservation.txt", true);
					            bw = new BufferedWriter(fw);
					            pw = new PrintWriter(bw);
					            
					            pw.println(file_data);
					            
					            pw.close();
					            bw.close();
					            fw.close();
							}
							catch(IOException e)
							{
								System.out.println("Error creating file for writing.");
							}
							
							reservation.printTicket();
									
						}
						
					}
					else if(option == 3)		// Cancel A Reservation
					{
						if(showMyReservations(Integer.parseInt(id)))
						{
							input.nextLine();
							System.out.println("Enter reservation number to cancel: ");
							String res_cancel = input.nextLine();
							
							FileWriter fw2 = null;
							BufferedWriter bw2 = null;
							PrintWriter pw2 = null;
							
							try
							{
								File file_cancel=new File("reservation.txt");
								FileReader fr_cancel=new FileReader(file_cancel);
								BufferedReader br_cancel=new BufferedReader(fr_cancel);
								
								String line_cancel;
								Reservation reservation;
								String[] all_reservations = new String[100];
								int y=0;
								while((line_cancel=br_cancel.readLine())!=null)  
								{
									try
									{
										all_reservations[y] = line_cancel;
									}
									catch(ArrayIndexOutOfBoundsException aofbe)
									{
										System.out.println("Array is out of bound...!");
									}
									y++;
								}
								
								fw2 = new FileWriter("reservation.txt");
					            bw2 = new BufferedWriter(fw2);
					            pw2 = new PrintWriter(bw2);
					          
								for(int ii=0; ii<y; ii++)
								{
									if(!res_cancel.equals(all_reservations[ii].split(",")[0]))
									{
										pw2.println(all_reservations[ii]);
									}
								}
								
								
								
							}
							catch(IOException e)
							{
								System.out.println("There is an error opening file!"+e);
							}
							
							pw2.close();
							bw2.close();
							pw2.close();
							
							System.out.println("Reservation has been cancelled...!");
							
						}
						else
						{
							System.out.println("No Reservation Found!");
						}
						
					}
					else if(option == 4)		// Modify A Reservation
					{
						if(showMyReservations(Integer.parseInt(id)))
						{
							input.nextLine();
							System.out.println("Enter reservation number to modify: ");
							String res_modify = input.nextLine();
							
							FileWriter fw3 = null;
							BufferedWriter bw3 = null;
							PrintWriter pw3 = null;
							
							try
							{
								File file_modify=new File("reservation.txt");
								FileReader fr_modify=new FileReader(file_modify);
								BufferedReader br_modify=new BufferedReader(fr_modify);
								
								String line_modify;
								Reservation reservation;
								String[] all_reservations = new String[100];
								int x=0;
								while((line_modify=br_modify.readLine())!=null)  
								{
									try
									{
										all_reservations[x] = line_modify;
									}
									catch(ArrayIndexOutOfBoundsException aofbe)
									{
										System.out.println("Array is out of bound...!");
									}
									x++;
								}
								
								System.out.println("Enter new flight no: ");
								String new_flightno = input.nextLine();
								int new_seatno;
								
								do
								{
									System.out.println("Enter seat no (1-30): ");
									new_seatno = input.nextInt();
									if(new_seatno<1 || new_seatno>30)
									{
										System.out.println("Wrong Seat Number. Enter Again!");
									}
								}while(new_seatno<1 || new_seatno>30);
								
								for(int l=0; l<x; l++)
								{
									if(res_modify.equals(all_reservations[l].split(",")[0]))
									{
										try
										{
											all_reservations[l] = all_reservations[l].replace(all_reservations[l].split(",")[2], new_flightno);
											all_reservations[l] = all_reservations[l].replace(all_reservations[l].split(",")[3], new_seatno+"");
										}
										catch(ArrayIndexOutOfBoundsException aofbe)
										{
											System.out.println("Array is out of bounds...!");
										}
									}
								}
								
								
								fw3 = new FileWriter("reservation.txt");
					            bw3 = new BufferedWriter(fw3);
					            pw3 = new PrintWriter(bw3);
					          
								for(int ii=0; ii<x; ii++)
								{
									pw3.println(all_reservations[ii]);
								}
								
							}
							catch(IOException e)
							{
								System.out.println("There is an error opening file!"+e);
							}
							
							pw3.close();
							bw3.close();
							pw3.close();
							
							System.out.println("Reservation Changes have been saved...!");
							
						}
						else
						{
							System.out.println("No Reservation Found!");
						}
					}
					else if(option == 5)			// Search Flights
					{
						System.out.println("1. Search by Date");
						System.out.println("2. Search by Origin and Destination");
						System.out.println("3. Search by Direct/Indirect flight");
						System.out.println("4. Search by lowest fare");
						System.out.println("5. Go to Main Menu");
						
						int sc_option;
						
						do
						{
							System.out.println("Enter Search Criteria Option: ");
							sc_option = input.nextInt();
							if(sc_option<1 || sc_option>5)
							{
								System.out.println("Wrong option selected. Try Again!");
							}
						}while(sc_option<1 || sc_option>5);
						
						if(sc_option == 1)			// search by date
						{
							input.nextLine();
							System.out.println("Enter Flight Date in formay 'yy-mm-yyyy': ");
							String key_date = input.nextLine();
							search(key_date, sc_option);
						}
						else if(sc_option == 2)
						{
							input.nextLine();
							System.out.println("Enter Flight Origin: ");
							String key_origin = input.nextLine();
							System.out.println("Enter Flight Destination: ");
							String key_destination = input.nextLine();
							search(key_origin+","+key_destination, sc_option);
							
						}
						else if(sc_option == 3)
						{
							input.nextLine();
							System.out.println("Enter Flight Type (Direct/Indirect): ");
							String key_dir_ind = input.nextLine();
							search(key_dir_ind.toLowerCase(), sc_option);
						}
						else if(sc_option == 4)
						{
							search("lowest",sc_option);
						}
						
					}
					else if(option == 0)
					{
						System.out.println("Thank you for using the system.");
					}
					
					
					
				}while(option != 0);
				
			}
			else
			{
				System.out.println("Wrong id/password.");
			}
			
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

	}
	
	public static int menu()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n\n*** MENU ***");
		System.out.println("1. View All Flights");
		System.out.println("2. Make a Reservation");
		System.out.println("3. Cancel a Reservation");
		System.out.println("4. Modify a Reservation");
		System.out.println("5. Search Flights");
		System.out.println("0. Exit");
		
		int option;
		do
		{
			System.out.println("\nEnter Option: ");
			option = input.nextInt();
			if(option<0 || option>5)
			{
				System.out.println("Wrong option entered. Try again!");
			}
		}while(option<0 || option>5);
		
		return option;
	}
	
	public static void viewAllFlights() throws IndexOutOfBoundsException, IOException
	{
		try
		{
			File flight_file=new File("flight.txt");
			FileReader fr_flight=new FileReader(flight_file);
			BufferedReader br_flight=new BufferedReader(fr_flight);
			String no_flights = br_flight.readLine();
			
			Flight flights[] = new Flight[Integer.parseInt(no_flights)];
			int count_flight=0;
			String line_flight;
			while((line_flight=br_flight.readLine())!=null)  
			{
				String[] flight_pieces = line_flight.split(",");
				Plane pp = new Plane(flight_pieces[6],flight_pieces[7],Integer.parseInt(flight_pieces[8]));
				boolean d_in=false;
				if(flight_pieces[5].equals("true"))
					d_in = true;
				try
				{
					flights[count_flight] = new Flight(pp,flight_pieces[0],flight_pieces[1],flight_pieces[2],flight_pieces[3],flight_pieces[4],d_in,Double.parseDouble(flight_pieces[9]));
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Array is out of bound...");
					System.out.println("System Error: "+e);
				}
				count_flight++;
			}
			
			fr_flight.close();
			
			System.out.println(" >>> FLIGHTS INFORMATION <<<");
			
			for(int z=0; z<count_flight; z++)
			{
				flights[z].printFlights();
			}
			
		}
		catch(IOException e)
		{
			System.out.println("There is some error opening file");
		}
	}
	
	public static void search(String key, int opt) throws IndexOutOfBoundsException, IOException
	{
		try
		{
			File flight_file=new File("flight.txt");
			FileReader fr_flight=new FileReader(flight_file);
			BufferedReader br_flight=new BufferedReader(fr_flight);
			String no_flights = br_flight.readLine();
			
			Flight flights[] = new Flight[Integer.parseInt(no_flights)];
			int count_flight=0;
			String line_flight;
			while((line_flight=br_flight.readLine())!=null)  
			{
				String[] flight_pieces = line_flight.split(",");
				Plane pp = new Plane(flight_pieces[6],flight_pieces[7],Integer.parseInt(flight_pieces[8]));
				boolean d_in=false;
				if(flight_pieces[5].equals("true"))
					d_in = true;
				try
				{
					flights[count_flight] = new Flight(pp,flight_pieces[0],flight_pieces[1],flight_pieces[2],flight_pieces[3],flight_pieces[4],d_in,Double.parseDouble(flight_pieces[9]));
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Array index is out of bounds...");
					System.out.println("System Error: "+e);
				}
				count_flight++;
			}
			
			fr_flight.close();
			
			
			if(opt == 1)		// search by date
			{
				System.out.println(" >>> FLIGHTS INFORMATION FOR DATE: "+key+" <<<");
				
				for(int z=0; z<count_flight; z++)
				{
					if(flights[z].getDate().equals(key))
					{
						flights[z].printFlights();
					}
				}
			}
			else if(opt == 2)		// search by Origin and Destination
			{
				String[] o_d = key.split(",");
				System.out.println(" >>> FLIGHTS INFORMATION FOR ORIGIN: "+o_d[0]+" AND DESTINATION: "+o_d[1]+" <<<");
				
				for(int z=0; z<count_flight; z++)
				{
					if(flights[z].getOrigin().equals(o_d[0]) && flights[z].getDestination().equals(o_d[1]))
					{
						flights[z].printFlights();
					}
				}
			}
			else if(opt == 3)		// Search by Direct/Indirect flight
			{
				boolean direct = false;
				if(key.equals("direct"))
					direct = true;
				
				if(direct)
				{
					System.out.println(" >>> FLIGHTS INFORMATION FOR DIRECT FLIGHTS <<<");
					
					for(int z=0; z<count_flight; z++)
					{
						if(flights[z].getIsDirect() == true)
						{
							flights[z].printFlights();
						}
					}
				}
				else
				{
					System.out.println(" >>> FLIGHTS INFORMATION FOR INDIRECT FLIGHTS <<<");
					
					for(int z=0; z<count_flight; z++)
					{
						if(flights[z].getIsDirect() == false)
						{
							flights[z].printFlights();
						}
					}
				}
				
			}
			else if(opt == 4)		// Search by Lowest Fare
			{
				System.out.println(" >>> FLIGHTS INFORMATION FOR LOWEST FARE FLIGHTS <<<");
				
				double lowest_fare = Double.MAX_VALUE;
				int ind = 0;
				
				for(int z=0; z<count_flight; z++)
				{
					if(flights[z].getFare() < lowest_fare)
					{
						lowest_fare = flights[z].getFare();
						ind = z;
					}
				}
				flights[ind].printFlights();
				
			}
			
		}
		catch(IOException e)
		{
			System.out.println("There is some error opening file");
		}
	}
	
	public static Flight getParticularFlight(String flight_no) throws IndexOutOfBoundsException, IOException
	{
		Flight particular_flight = new Flight();
		
		try
		{
			File flight_file=new File("flight.txt");
			FileReader fr_flight=new FileReader(flight_file);
			BufferedReader br_flight=new BufferedReader(fr_flight);
			String no_flights = br_flight.readLine();
			
			Flight flights[] = new Flight[Integer.parseInt(no_flights)];
			int count_flight=0;
			String line_flight;
			while((line_flight=br_flight.readLine())!=null)  
			{
				String[] flight_pieces = line_flight.split(",");
				Plane pp = new Plane(flight_pieces[6],flight_pieces[7],Integer.parseInt(flight_pieces[8]));
				boolean d_in=false;
				if(flight_pieces[5].equals("true"))
					d_in = true;
				try
				{
					flights[count_flight] = new Flight(pp,flight_pieces[0],flight_pieces[1],flight_pieces[2],flight_pieces[3],flight_pieces[4],d_in,Double.parseDouble(flight_pieces[9]));
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println("Array index is out of bounds...");
					System.out.println("System Error: "+e);
				}
				count_flight++;
			}
			
			fr_flight.close();
			int ind = 0;
			boolean found = false;
			for(int z=0; z<count_flight; z++)
			{
				if(flights[z].getFlightNo().equals(flight_no))
				{
					ind = z;
					found = true;
					break;
				}
			}
			
			if(found)
			{
				particular_flight = flights[ind];
			}
		}
		catch(IOException e)
		{
			System.out.println("There is some error opening file");
		}
		
		return particular_flight;
	}
	
	public static boolean showMyReservations(int mycustid) throws IOException
	{
		boolean found = false;
		try
		{
			File file=new File("reservation.txt");
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			
			String line;
			Reservation reservation;
			int i=1;
			while((line=br.readLine())!=null)  
			{
				String[] res_pieces = line.split(",");
				if(Integer.parseInt(res_pieces[1]) == mycustid)
				{	
					found = true;
					System.out.println("\n >>> RESERVATION NO. "+i+" <<<");
					System.out.println("Booking ID: "+res_pieces[0]);
					System.out.println("Customer ID: "+res_pieces[1]);
					System.out.println("Flight Number: "+res_pieces[2]);
					System.out.println("Seat Number: "+res_pieces[3]);
					System.out.println("Payment Type: Credit Card");
					i++;
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		return found;
	}

}
