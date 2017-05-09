package classes;
import java.util.ArrayList;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Client {

	private int CPR, phoneNo, postal;
	private String email, fullAddress, fullName, firstName, lastName, clientID, street, country, city;
	private ArrayList<Account> accounts;

	public void transaction() {

	}

	public void sendMessage() {

	}

	public void changePassword() {

	}

	public void limitedEditInformation() {

	}

	public void fetchAccounts() {

	}
	
	public String toString (){
		String clientString = "ClientID: " + getClientID()
			+ " FirstName: " + getFirstName()
			+ " LastName: " + getLastName()
			+ " FullName: " + getFullName()
			+ " Email: " + getEmail()
			+ " CPR: " + getCpr()
			+ " PhoneNo: " + getPhoneNo()
			+ " Street: " + getStreet()
			+ " City: " + getCity()
			+ " Country: " + getCountry()
			+ " Postal: " + getPostal()
			+ " FullAddress: " + getFullAddress();
				
		return clientString;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getClientID(){
		return clientID;
	}

	public void setCPR(int CPR) {
		this.CPR = CPR;
	}
	
	public int getCpr (){
		return CPR;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public int getPhoneNo (){
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
}
