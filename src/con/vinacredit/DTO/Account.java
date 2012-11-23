package con.vinacredit.DTO;

public class Account {

	private String Email;
	private String Pass;
	private String FirstName;
	private String LastName;
	private String CompanyName;
	private byte[] ImageAcc;
	private String Address;
	
	

	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public byte[] getImageAcc() {
		return ImageAcc;
	}
	public void setImageAcc(byte[] imageAcc) {
		ImageAcc = imageAcc;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Account(){}
	public Account(String Email, String Pass, String FirstName, String LastName, String CompanyName, byte[] ImageAcc, String Address){
	
		this.Email 			= Email;
		this.Pass			= Pass;
		this.FirstName 		= FirstName;
		this.LastName		= LastName;
		this.CompanyName	= CompanyName;
		this.ImageAcc		= ImageAcc;
		this.Address		= Address;
		
		
	}
}
