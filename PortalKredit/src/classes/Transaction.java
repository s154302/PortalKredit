package classes;

import java.sql.Date;

/**
 * Created by Alexander Armstrong on 5/03/2017.
 */
public class Transaction {
	private String accountNumber, recieveAccount, currency, note, transactionID, regNo, recieveRegNo;
	private double amount, balance;
	private Date dateOfTransaction;
	
	public Transaction(){
		
	}
	
	public Transaction(String transactionID, String accountNumber,
			String regNo, String recieveAccount, String recieveRegNo,
			Date dateOfTransaction, double amount, String currency, double balance, String note){
		this.transactionID = transactionID;
		this.accountNumber = accountNumber;
		this.regNo = regNo;
		this.recieveAccount = recieveAccount;
		this.recieveRegNo = recieveRegNo;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.currency = currency;
		this.balance = balance;
		this.note = note;
	}
	
	public String toString(){
		String transactionString  = "TransactionId: " + transactionID + " AccountNumber: " + accountNumber + " RegNo: "
				+ regNo + " RecieveAccount " + recieveAccount + " RecieveRegNo: " + recieveRegNo +  " Amount: " + amount
				+ " Currency: " + currency + " Balance: " + balance + " Note: " + note;
		try{
			transactionString = transactionString + " Date: "
					+ dateOfTransaction.toString();
		}catch(NullPointerException e){}
		
		return transactionString;
	}
	
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getRecieveRegNo() {
		return recieveRegNo;
	}
	public void setRecieveRegNo(String recieveRegNo) {
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
