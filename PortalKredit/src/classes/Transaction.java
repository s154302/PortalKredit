package classes;

import java.sql.Date;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Transaction {
	private int transactionID, regNo, recieveRegNo ; 
	private String accountNumber, recieveAccount, currency  ;
	private double amount ;
	private Date dateOfTransaction;
	
	public Transaction(){
		
	}
	
	public Transaction(int transactionID, String accountNumber,
			int regNo, String recieveAccount, int recieveRegNo,
			Date dateOfTransaction, double amount, String currency){
		this.transactionID = transactionID;
		this.accountNumber = accountNumber;
		this.regNo = regNo;
		this.recieveAccount = recieveAccount;
		this.recieveRegNo = recieveRegNo;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.currency = currency;
	}
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getRegNo() {
		return regNo;
	}
	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}
	public int getRecieveRegNo() {
		return recieveRegNo;
	}
	public void setRecieveRegNo(int recieveRegNo) {
		this.recieveRegNo = recieveRegNo;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getRecieveAccount() {
		return recieveAccount;
	}
	public void setRecieveAccount(String recieveAccount) {
		this.recieveAccount = recieveAccount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	
}
