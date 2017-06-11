package classes;

public class Branch {

	private String regNo, bankName, postal, country, street, phone, city, fullAddress;
	public Branch(){
		
	}
	
	public Branch(String regNo, String bankName, String postal, String country, String street, String phone, String city){
		this.regNo = regNo;
		this.bankName = bankName;
		this.postal = postal;
		this.country = country;
		this.street = street;
		this.phone = phone;
		this.city = city;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
		setFullAddress();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
		setFullAddress();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
		setFullAddress();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	String getFullAddress() {
		return fullAddress;
	}

	private void setFullAddress() {
		try{
			this.fullAddress = getStreet() + " " + getPostal() + " " + getCity() + " " + getCountry();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		setFullAddress();
	}
}
