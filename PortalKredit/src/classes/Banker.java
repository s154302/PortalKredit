package classes;
import java.util.ArrayList;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Banker {

	private int bankerID, phoneNo, regNo;
	private String firstName, lastName, fullName, password, email;
	private ArrayList clients;

	public void createClient() {

	}

	public void openAccount() {

	}

	public void closeAccount() {

	}

	public void editAccount() {

	}

	public void editClient() {

	}

	public void deposit(double amount) {

	}

	public void withdraw(double amount) {

	}

	public void setBankerID(int bankerID) {
		this.bankerID = bankerID;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName() {
		try{
			this.fullName = getFirstName() + " " + getLastName();
		}catch(Exception e){}
		
	}
	
	public String getFullName(){
		return fullName;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}
	
	public int getRegNo(){
		return regNo;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	

}
