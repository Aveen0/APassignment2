package p1;

public class CardPayment {

	private String card_number;
	private int exp_month;
	private int exp_year;
	private int cvv;
	
	public CardPayment()
	{
		card_number = "";
		exp_month = 0;
		exp_year = 0;
		cvv = 0;
	}
	
	public CardPayment(String card_number, int exp_month, int exp_year, int cvv)
	{
		this.card_number = card_number;
		this.exp_month = exp_month;
		this.exp_year = exp_year;
		this.cvv = cvv;
	}
	
	public void setCardNumber(String card_number)
	{
		this.card_number=card_number;
	}
	
	public void setExpiryMonth(int exp_month)
	{
		this.exp_month=exp_month;
	}
	
	public void setExpiryYear(int exp_year)
	{
		this.exp_year=exp_year;
	}
	
	public void setCVV(int cvv)
	{
		this.cvv=cvv;
	}
	
	//
	
	public String getCardNumber()
	{
		return card_number;
	}
	
	public int getExpiryMonth()
	{
		return exp_month;
	}
	
	public int getExpiryYear()
	{
		return exp_year;
	}
	
	public int getCVV()
	{
		return cvv;
	}

}
