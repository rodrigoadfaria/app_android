
package con.vinacredit.DTO;

/**
 * @author tonymai
 *
 */
public class SumBill {

	private String DateSale;
	private String SumBill;
	private String Email;
	
	public String getDateSale() {
		return DateSale;
	}
	public void setDateSale(String dateSale) {
		DateSale = dateSale;
	}
	public String getSumBill() {
		return SumBill;
	}
	public void setSumBill(String sumBill) {
		SumBill = sumBill;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public SumBill(){}
	public SumBill(String DateSale, String SumBill, String Email){
		this.DateSale	= DateSale;
		this.SumBill	= SumBill;
		this.Email		= Email;
	}
	
}
