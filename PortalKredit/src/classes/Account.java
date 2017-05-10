package classes;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Account {

	private int regNo;
	private String currency, accountNumber, accountType, clientID;
	private double balance;
	private ArrayList<Transaction> transactions;

	public void fetchData(Date beginDate, Date endDate) {

	}

	public String toString(){
		String accountString = 
				"AccountNumber: " + getAccountNumber()
				+ " RegNo: " + getRegNo()
				+ " Balance: " + getBalance()
				+ " Currency: " + getCurrency();
		
		return accountString;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

}
