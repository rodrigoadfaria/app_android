package con.vinacredit.DTO;

public class Bill {

	private String txtBill;
	private String TimeSale;
	private String SumItem;
	private String Email;
	private String DateSale;
	private String checkCard;
	
	
	public String getTxtBill() {
		return txtBill;
	}
	public void setTxtBill(String txtBill) {
		this.txtBill = txtBill;
	}
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
		
	public String getCheckCard() {
		return checkCard;
	}
	public void setCheckCard(String checkCard) {
		this.checkCard = checkCard;
	}
	public Bill(){}
	public Bill(String TimeSale, String SumItem, String Email, String DateSale){
		this.TimeSale	= TimeSale;
		this.SumItem	= SumItem;
		this.Email		= Email;
		this.DateSale	= DateSale;
	}
	
	public Bill(String checkCard, String TimeSale, String SumItem){
		this.checkCard	= checkCard;
		this.TimeSale 	= TimeSale;
		this.SumItem	= SumItem;
	}
	
}
