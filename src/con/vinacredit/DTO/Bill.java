package con.vinacredit.DTO;

public class Bill {

	private String TimeSale;
	private String SumItem;
	private String Email;
	private String DateSale;
	
	public String getTimeSale() {
		return TimeSale;
	}
	public void setTimeSale(String timeSale) {
		TimeSale = timeSale;
	}
	public String getSumItem() {
		return SumItem;
	}
	public void setSumItem(String sumItem) {
		SumItem = sumItem;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDateSale() {
		return DateSale;
	}
	public void setDateSale(String dateSale) {
		DateSale = dateSale;
	}
	
	public Bill(){}
	public Bill(String TimeSale, String SumItem, String Email, String DateSale){
		this.TimeSale	= TimeSale;
		this.SumItem	= SumItem;
		this.Email		= Email;
		this.DateSale	= DateSale;
	}
	
}
