package classes;
import java.util.ArrayList;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Banker {

	private int bankerID;
	private String bankerName, branch, password;
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

	public void setBankerName(String bankerName) {
		this.bankerName = bankerName;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	

}
