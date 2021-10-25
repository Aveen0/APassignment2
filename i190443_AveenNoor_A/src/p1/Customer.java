package p1;
public class Customer
{
	private int custid;
	private String name;
	private String gender;
	private int age;
	private String address;
	private String passport;
	private String password;
	
	public Customer()
	{
		custid = 0;
		name = "";
		gender = "";
		age = 0;
		address = "";
		passport = "";
		password = "";
	}
	
	public Customer(int custid, String name, String gender, int age, String address, String passport, String password)
	{
		this.custid = custid;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.passport = passport;
		this.password = password;
	}
	
	public void setCustomerID(int id)
	{
		custid = this.custid;
	}
	
	public void setCustomerName(String name)
	{
		name = this.name;
	}
	
	public void setCustomerGender(String gender)
	{
		gender = this.gender;
	}
	
	public void setCustomerAge(int age)
	{
		age = this.age;
	}
	
	public void setCustomerAddress(String address)
	{
		address = this.address;
	}
	
	public void setCustomerPassport(String passport)
	{
		passport = this.passport;
	}
	
	public void setCustomerPassword(String password)
	{
		password = this.password;
	}
	
	public int getCustomerID()
	{
		return custid;
	}
	
	public String getCustomerName()
	{
		return name;
	}
	
	public String getCustomerGender()
	{
		return gender;
	}
	
	public int getCustomerAge()
	{
		return age;
	}
	
	public String getCustomerAddress()
	{
		return address;
	}
	
	public String getCustomerPassport()
	{
		return passport;
	}
	
	public String getCustomerPassword()
	{
		return password;
	}
	
	public String toString()
	{
		String str = getCustomerID()+","+name+","+gender+","+age+","+address+","+passport+","+password;
		return str;
	}

}
