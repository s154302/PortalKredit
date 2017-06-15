package classes;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Account {

	private String currency, accountNumber, accountType, clientID, accountName, regNo;
	private double balance, interestRate;
	private ArrayList<Transaction> transactions;
	

	public Account (){
		
	}
	
	public Account(String accountNumber, String regNo, String accountType,
			String clientID, double balance, String currency, double interestRate, String accountName){
		this.accountNumber = accountNumber;
		this.regNo = regNo;
		this.accountType = accountType;
		this.clientID = clientID;
		this.balance = balance; 
		this.currency = currency;
		this.interestRate = interestRate;
		this.accountName = accountName;
	}
	
	public String toString(){
		String accountString = 
				"AccountNumber: " + getAccountNumber()
				+ " RegNo: " + getRegNo()
				+ " Balance: " + getBalance()
				+ " Currency: " + getCurrency()
				+ " AccountName: " + accountName
				+ " Transactions: " + transactions;
		
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

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
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

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
