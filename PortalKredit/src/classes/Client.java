package classes;
import java.util.ArrayList;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Client {

	private int  postal;
	private String email, fullAddress, fullName, firstName, lastName, clientID, street, country, city, CPR, phoneNo, bankerID;
	private ArrayList<Account> accounts;

	public Client(){
		
		
	}
	
	public Client(String email, String firstName, String lastName, String clientID,
			String street, String country, String city, String CPR, String phoneNo, int postal){
		this.email = email;
		setFirstName(firstName);
		setLastName(lastName);
		this.clientID = clientID;
		setStreet(street);
		setCountry(country);
		setCity(city);
		this.CPR = CPR;
		this.phoneNo = phoneNo;
		setPostal(postal);
	}
	
	
	public Account findAccount(String accountNumber, int regNo) {

		for(Account account : accounts){
			if(account.getAccountNumber().equals(accountNumber) && account.getRegNo() == regNo){
				return account;
			}
		}
		
		return null;
	}
	
	public String toString (){
		String clientString = "ClientID: " + getClientID()
			+ " FirstName: " + getFirstName()
			+ " LastName: " + getLastName()
			+ " FullName: " + getFullName()
			+ " Email: " + getEmail()
			+ " CPR: " + getCPR()
			+ " PhoneNo: " + getPhoneNo()
			+ " Street: " + getStreet()
			+ " City: " + getCity()
			+ " Country: " + getCountry()
			+ " Postal: " + getPostal()
			+ " FullAddress: " + getFullAddress();
		int i = 1;
//		for(Account account : accounts){
//			try{
//				clientString = clientString + " " + "Account: " + i +  " AccountNum: " + account.getAccountNumber() + " RegNo: " + account.getRegNo() + " ";
//				i++;
//			}catch(Exception e){}
//		}
				
		return clientString;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getClientID(){
		return clientID;
	}

	public void setCPR(String CPR) {
		this.CPR = CPR;
	}
	
	public String getCPR (){
		return CPR;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getPhoneNo (){
		return phoneNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}

	public void setFullAddress() {
		try{
			this.fullAddress = getStreet() + " " + getPostal() + " " + getCity() + " " + getCountry();
		}catch(Exception e){}
	}
	
	public String getFullAddress(){
		return fullAddress;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName() {
		try{
			this.fullName = getFirstName() + " " + getLastName();
		}catch(Exception e){}
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		setFullName();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		setFullName();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
		setFullAddress();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
		setFullAddress();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		setFullAddress();
	}

	public int getPostal() {
		return postal;
	}

	public void setPostal(int postal) {
		this.postal = postal;
		setFullAddress();
	}

	public String getBankerID() {
		return bankerID;
	}

	public void setBankerID(String bankerID) {
		this.bankerID = bankerID;
	}
}
