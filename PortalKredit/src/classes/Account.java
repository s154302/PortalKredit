package classes;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Account {

	private int accountNumber, regno;
	private String accountName, currency;
	private double balance;
	private ArrayList<Transaction> transactions;

	public void fetchData(Date beginDate, Date endDate) {

	}

	public String toString(){
		String accountString = 
				"AccountNumber: " + getAccountNumber()
				+ " RegNo: " + getRegno()
				+ " Balance: " + getBalance()
				+ " Currency: " + getCurrency();
		
		return accountString;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getRegno() {
		return regno;
	}

	public void setRegno(int regno) {
		this.regno = regno;
	}

	//TODO - there is no accountName in the database
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setValuta(String valuta) {
		this.currency = valuta;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

}
