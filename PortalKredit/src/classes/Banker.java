package classes;
import java.util.ArrayList;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Banker {

	private String firstName, lastName, fullName, email, bankerID, phoneNo, regNo;
	private ArrayList<Client> clients;

	public Banker(){
		
	}
	
	public Banker(String firstName, String lastName, String email, String bankerID, String phoneNo, String regNo){
		setFirstName(firstName);
		setLastName(lastName);
		this.email = email;
		this.bankerID = bankerID;
		this.phoneNo = phoneNo;
		this.regNo = regNo;
	}
	
	public String toString(){
		String bankerString = "BankerID: " + getBankerID() 
		+ " First Name: " + getFirstName() 
		+ " Last Name: " + getLastName()
		+ " Full Name: " + getFullName()
		+ " Email: " + getEmail()
		+ " PhoneNo: " + getPhoneNo()
		+ " RegNo: " + getRegNo();
		int i = 0;
		for(Client client : clients){
			try{
				bankerString = bankerString + "Client: " + i + " id: " +  client.getClientID() + " ";
				i++;
			}catch(Exception e){}

		}
		
		return bankerString;
	}
	
	public void setBankerID(String bankerID) {
		this.bankerID = bankerID;
	}
	
	public String getBankerID(){
		return bankerID;
	}

	public void setFullName() {
		try{
			this.fullName = getFirstName() + " " + getLastName();
		}catch(Exception e){}
		
	}
	
	public String getFullName(){
		return fullName;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public String getRegNo(){
		return regNo;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public ArrayList<Client> getClients(){
		return clients;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Client getClient(String clientID) {
		// TODO Auto-generated method stub
		for(Client client : clients){
			try{
				if(client.getClientID() == clientID){
					return client;
				}
			}catch(Exception e){}

		}
		return null;
	}
	
	

}