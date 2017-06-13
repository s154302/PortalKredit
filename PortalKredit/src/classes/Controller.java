package classes;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public final class Controller {
	public static enum Type {
		client, banker, admin
	}

	public static Connection getConnection (DataSource ds1){
		try {
			return ds1.getConnection(Secret.userID, Secret.password);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static void cleanUpConnection(Connection con){
		try{
			if(con!=null){
				con.close();
			}

		}catch(SQLException e){
			
		}
	}
	
	public static void cleanUpResult(ResultSet rs, PreparedStatement ps){
		try{
			if(rs!=null){
				rs.close();
			}
		}catch(SQLException e){
			
		}
		try{
			if(ps!=null){
				ps.close();
			}

		}catch(SQLException e){
			
		}
	}
	

	public static boolean authenticate(String userID, String password, Connection con, HttpSession session) {

		if (userID.substring(userID.length() - 1).equals("C")) {
			boolean bool = clientAuthenticate(userID, password, con);
			if (bool) {
				session.setAttribute("type", Type.client);
			}
			return bool;
		} else if (userID.substring(userID.length() - 1).equals("B")) {
			boolean bool = bankerAuthenticate(userID, password, con);
			if (bool) {
				session.setAttribute("type", Type.banker);
			}
			return bool;
		} else {
			boolean bool = adminAuthenticate(userID, password, con);
			if (bool) {
				session.setAttribute("type", Type.admin);
			}
			return bool;
		}

	}

	public static boolean checkAuth(Type type, HttpSession session) {

		return type.equals((Type) session.getAttribute("type"));
	}

	public static void adminCheckAuth(String redirectUrl, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		if (Controller.checkAuth(Controller.Type.admin, request.getSession())) {
			request.getRequestDispatcher(redirectUrl).forward(request, response);

		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}

	// consider making these private??
	public static boolean clientAuthenticate(String clientID, String password, Connection con) {
		
		String hash = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement("SELECT PASSWORD FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");

			ps.setString(1, clientID);

			rs = ps.executeQuery();

			if (rs.next()) {
				hash = rs.getString("PASSWORD");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}
		if (hash != null) {
			return BCrypt.checkpw(password, hash);
		} else {
			return false;
		}
	}

	// maybe private
	public static boolean bankerAuthenticate(String bankerID, String password, Connection con) {
		
		String hash = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con
					.prepareStatement("SELECT PASSWORD FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=?");

			ps.setString(1, bankerID);

			rs = ps.executeQuery();
			if (rs.next()) {
				hash = rs.getString("PASSWORD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}
		if (hash != null) {
			return BCrypt.checkpw(password, hash);
		} else {
			return false;
		}
	}

	// maybe private ?? - I don't think you can when we say Controller.W/E ;)
	public static boolean adminAuthenticate(String adminID, String password, Connection con) {
		
		String hash = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");

			ps.setString(1, adminID);

			rs = ps.executeQuery();
			if (rs.next()) {
				hash = rs.getString("PASSWORD");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}
		if (hash != null) {
			return BCrypt.checkpw(password, hash);
		} else {
			return false;
		}

	}

	// Fills a banker user object with data and returns it
	public static Banker getBankerInfo(String userId, Connection con) {
		Banker banker = new Banker();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BANKER\" WHERE \"BANKERID\"=?");

			ps.setString(1, userId);
			rs = ps.executeQuery();

			rs.next();

			banker.setBankerID(rs.getString("BANKERID"));
			banker.setFirstName(rs.getString("FIRSTNAME"));
			banker.setLastName(rs.getString("LASTNAME"));
			banker.setRegNo(rs.getString("REGNO"));
			banker.setEmail(rs.getString("EMAIL"));
			banker.setPhoneNo(rs.getString("MOBILE"));

			banker.setClients(getClients(userId, con));

		} catch (SQLException e) {

			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}

		return banker;
	}

	// Fills a single client object with data and returns it
	public static Client getClientInfo(String userId, Connection con) {
		Client client = new Client();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");

			ps.setString(1, userId);
			rs = ps.executeQuery();

			rs.next();
			client = setClientInfo(rs);

			client.setCity(findCity(rs.getString("POSTAL"), rs.getString("COUNTRY"), con));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}

		return client;
	}

	// Returns all accounts, as an ArrayList, associated with a single client
	public static ArrayList<Account> getAccounts(String clientID, Connection con) {

		ArrayList<Account> accountList = new ArrayList<Account>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"CLIENTID\"=?");
			ps.setString(1, clientID);

			rs = ps.executeQuery();

			Account account;
			String accountType;
			while (rs.next()) {
				accountType = rs.getString("ACCOUNTTYPE");
				account = new Account(rs.getString("ACCOUNTNUMBER"), rs.getString("REGNO"), accountType,
						rs.getString("CLIENTID"), rs.getDouble("BALANCE"), rs.getString("CURRENCY"),
						findInterestRate(rs.getString("ACCOUNTTYPE"), con), rs.getString("accountName"));
				account.setInterestRate(findInterestRate(accountType, con));
				account.setTransactions(get3NewestTransactions(account.getAccountNumber(), account.getRegNo(), con));
				accountList.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}
		return accountList;
	}

	// Fills a single account object with data and returns it
	public static Account getAccountInfo(String accountNumber, String regNo, Connection con) {

		Account account = new Account();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\"=? AND \"REGNO\"=?");

			ps.setString(1, accountNumber);

			ps.setString(2, regNo);
			rs = ps.executeQuery();


			rs.next();
			account.setAccountNumber(rs.getString("ACCOUNTNUMBER"));
			account.setRegNo(rs.getString("REGNO"));
			String accountType = rs.getString("ACCOUNTTYPE");
			account.setAccountType(accountType);
			account.setClientID(rs.getString("CLIENTID"));
			account.setBalance(rs.getDouble("BALANCE"));
			account.setCurrency(rs.getString("CURRENCY"));
			account.setAccountName(rs.getString("ACCOUNTNAME"));
			account.setInterestRate(findInterestRate(accountType, con));
			
			account.setTransactions(getNewTransactions(rs.getString("ACCOUNTNUMBER"), rs.getString("REGNO"), con));


		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}

		return account;
	}
	
	public static String getAccountCurrency(String accountNumber, String regNo, Connection con){
		String currency = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = con.prepareStatement("SELECT CURRENCY FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?");
			ps.setString(1, accountNumber);
			ps.setString(2, regNo);
			rs = ps.executeQuery();
			if(rs.next()){
				currency = rs.getString("CURRENCY");
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			cleanUpResult(rs, ps);
		}
		return currency;
	}

	// Returns the 3 newest transactions associated with an account number and
	// regno
	public static ArrayList<Transaction> get3NewestTransactions(String accountNumber, String regNo, Connection con) {

		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM \"DTUGRP16\".\"TRANSACTION\" WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?"
							+ "ORDER BY DATEOFTRANSACTION DESC FETCH FIRST 3 ROWS ONLY");

			ps.setString(1, accountNumber);
			ps.setString(2, regNo);

			rs = ps.executeQuery();

			while (rs.next()) {
				transactionList.add(new Transaction(rs.getString("TRANSACTIONID"), rs.getString("ACCOUNTNUMBER"),
						rs.getString("REGNO"), rs.getString("RECIEVEACCOUNT"), rs.getString("RECIEVEREGNO"),
						rs.getDate("DATEOFTRANSACTION"), rs.getDouble("AMOUNT"), rs.getString("CURRENCY"),
						rs.getDouble("BALANCE"), rs.getString("NOTE")));
			}
			if (transactionList.size() < 3) {
				for (int i = 0; i < 3 - transactionList.size(); i++) {
					transactionList.add(null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}

		return transactionList;
	}

	// Returns all 'new' transactions associated with an account
	public static ArrayList<Transaction> getNewTransactions(String accountNumber, String string, Connection con) {


		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM \"DTUGRP16\".\"TRANSACTION\" WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ? ORDER BY DATEOFTRANSACTION DESC");

			ps.setString(1, accountNumber);
			ps.setString(2, string);

			rs = ps.executeQuery();

			while (rs.next()) {
				transactionList.add(new Transaction(rs.getString("TRANSACTIONID"), rs.getString("ACCOUNTNUMBER"),
						rs.getString("REGNO"), rs.getString("RECIEVEACCOUNT"), rs.getString("RECIEVEREGNO"),
						rs.getDate("DATEOFTRANSACTION"), rs.getDouble("AMOUNT"), rs.getString("CURRENCY"),
						rs.getDouble("BALANCE"), rs.getString("NOTE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}
		return transactionList;
	}
	
	// Returns all 'old' transactions associated with an account
	public static ArrayList<Transaction> getOldTransactions(String accountNumber, String regNo, Connection con) {

		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
				ps = con.prepareStatement(
						"SELECT * FROM \"DTUGRP16\".\"TRANSACTIONOLD\" WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ? ORDER BY DATEOFTRANSACTION DESC");

				ps.setString(1, accountNumber);
				ps.setString(2, regNo);
				
				rs = ps.executeQuery();

				while (rs.next()) {
					transactionList.add(new Transaction(rs.getString("TRANSACTIONID"), rs.getString("ACCOUNTNUMBER"),
							rs.getString("REGNO"), rs.getString("RECIEVEACCOUNT"), rs.getString("RECIEVEREGNO"),
							rs.getDate("DATEOFTRANSACTION"), rs.getDouble("AMOUNT"), rs.getString("CURRENCY"),
							rs.getDouble("BALANCE"), rs.getString("NOTE")));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				cleanUpResult(rs, ps);
			}
		
		return transactionList;
	}

	// Fills a single Admin object with data and returns it
	public static Admin getAdminInfo(String userID, Connection con) {
		Admin admin = new Admin("", "");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");

			ps.setString(1, userID);
			rs = ps.executeQuery();
			rs.next();
			admin.setUsername(rs.getString("ADMINID"));
			admin.setPassword(rs.getString("PASSWORD"));

		} catch (SQLException e) {

			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}

		return admin;
	}


	public static boolean createClient(String firstName, String lastName, String password, String CPR, String email,
			String mobile, String street, String bankerID, String postal, String country, Connection con)

			 {
		boolean status= false;
		PreparedStatement ps = null;
		try{
		ps = con.prepareStatement(
				"INSERT INTO \"DTUGRP16\".\"CLIENT\" (CLIENTID, PASSWORD, CPR, FIRST_NAME, LAST_NAME, EMAIL, MOBILE, STREET, BANKERID, POSTAL, COUNTRY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, generateClientID(con));
		ps.setString(2, password);
		ps.setString(3, CPR);
		ps.setString(4, firstName);
		ps.setString(5, lastName);
		ps.setString(6, email);
		ps.setString(7, mobile);
		ps.setString(8, street);
		ps.setString(9, bankerID);

		ps.setString(10, postal);

		ps.setString(11, country.toUpperCase());
		ps.executeUpdate();
		status = true;
		} catch(SQLException e){
			status = false;
			e.printStackTrace();
		}
		finally{
		
		cleanUpResult(null, ps);
		}
		return status;
	}

	public static boolean createAdmin(String username, String password, Connection con) {
		PreparedStatement ps = null;
		boolean status = false;
		try {
			ps = con
					.prepareStatement("INSERT INTO \"DTUGRP16\".\"ADMIN\" (ADMINID, password) VALUES(?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
			status = false;
		}finally{
			cleanUpResult(null, ps);
		}
		return status;
	}


	public static boolean createAccount(String accountName, String accountNumber, String regNo, String accountType,
			String clientID, double balance, String currency, Connection con) throws SQLException {
		
		PreparedStatement ps = null;
		try{
		ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"ACCOUNT\" "

				+ "(ACCOUNTNUMBER, REGNO, ACCOUNTNAME, ACCOUNTTYPE, CLIENTID, BALANCE, CURRENCY, INTEREST) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		BigDecimal bdBalance = new BigDecimal(Double.valueOf(balance));
		ps.setString(2, regNo);
		ps.setString(1, generateAccountNumber(con));
		ps.setString(3, accountName);
		ps.setString(4, accountType);
		ps.setString(5, clientID);
		ps.setBigDecimal(6, bdBalance);
		ps.setString(7, currency);
		ps.setDouble(8, 0);
		ps.executeUpdate();
		
		return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
			
		}finally{
			cleanUpResult(null,ps);
		}

		

	}

	public static String generateAccountNumber(Connection ds1) {
		String accountNumber = new String();

		int intID = 0;
		try {
			// Select the latest ID, and extract only the ID number as an
			// integer
			PreparedStatement ps = ds1.prepareStatement(
					"(SELECT INTEGER(ACCOUNTNUMBER) FROM \"DTUGRP16\". \"ACCOUNT\" ORDER BY ACCOUNTNUMBER DESC FETCH FIRST 1 ROWS ONLY)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				intID = rs.getInt(1);
			}

			if (intID > 0) {
				accountNumber = String.format("%010d", intID + 1);
			} else {
				accountNumber = "0000000001";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(accountNumber);

		return accountNumber;

	}

	// Returns all clients associated with a single banker
	public static ArrayList<Client> getClients(String bankerID, Connection con) {
		ArrayList<Client> clientList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE \"BANKERID\" = ?");

			ps.setString(1, bankerID);
			rs = ps.executeQuery();

			while (rs.next()) {
				Client client = setClientInfo(rs);
				clientList.add(client);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}
		return clientList;
	}

	// Returns the city associated with the postal and country
	public static String findCity(String postal, String country, Connection con) {

		String city = "Orgrimmar";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT CITY FROM \"DTUGRP16\".\"PLACE\" WHERE \"POSTAL\" = ? AND \"COUNTRY\" = ?");

			ps.setString(1, postal);
			ps.setString(2, country.toUpperCase());
			rs = ps.executeQuery();
			
			if(rs.next()){
				city = rs.getString("CITY");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}
		return city;
	}

	// Returns the interestRate associated with the given account type
	public static double findInterestRate(String accountType, Connection con) {
		double rate = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ACCOUNTTYPE\" WHERE \"ACCOUNTTYPE\" = ?");

			ps.setString(1, accountType);
			rs = ps.executeQuery();

			rs.next();

			rate = rs.getDouble("INTERESTRATE");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}
		return rate;
	}

	public static String generateClientID(Connection con) {
		String ID = null;
		int intID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			// Select the latest ID, and extract only the ID number as an
			// integer
			ps = con.prepareStatement("(SELECT INTEGER(SUBSTR(clientID, 1, 8)) "
					+ "FROM \"DTUGRP16\". \"CLIENT\" ORDER BY clientID DESC FETCH FIRST 1 ROWS ONLY)");
			rs = ps.executeQuery();
			if (rs.next()) {
				intID = rs.getInt(1);
			}

			if (intID > 0) {
				ID = String.format("%08d", intID + 1) + "C";
			} else {
				ID = "00000001C";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}

		return ID;
	}

	// Returns all admins in the database
	public static ArrayList<Admin> getAdminList(Connection con) {
		ArrayList<Admin> adminList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ADMIN\"");
			rs = ps.executeQuery();
			while (rs.next()) {
				adminList.add(new Admin(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs,ps);
		}

		return adminList;
	}

	public static boolean deleteAdmin(String adminID, Connection con) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"ADMIN\" WHERE \"ADMINID\"=?");
			ps.setString(1, adminID);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			cleanUpResult(null,ps);
		}

	}

	private static String generateBankerID(Connection con) {
		String ID = null;
		int intID = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Select the latest ID, and extract only the ID number as an
			// integer
			ps = con.prepareStatement("(SELECT INTEGER(SUBSTR(bankerID, 1, 6)) "
					+ "FROM \"DTUGRP16\". \"BANKER\" ORDER BY bankerID DESC FETCH FIRST 1 ROWS ONLY)");
			rs = ps.executeQuery();
			if (rs.next()) {
				intID = rs.getInt(1);
			}

			if (intID > 0) {
				ID = String.format("%06d", intID + 1) + "B";
			} else {
				ID = "000001B";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}

		return ID;
	}

	public static boolean createBanker(String firstName, String lastName, String password, String email, String telephone,
			String regno, Connection con) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"BANKER\" "
					+ "(BANKERID, PASSWORD, FIRSTNAME, LASTNAME, REGNO, EMAIL, MOBILE) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, generateBankerID(con));
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, regno);
			ps.setString(6, email);
			ps.setString(7, telephone);
			ps.executeUpdate();
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			cleanUpResult(null, ps);
		}

	}

	public static boolean deleteClient(String clientID, Connection con) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\"=?");
			ps.setString(1, clientID);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			cleanUpResult(null, ps);
		}

	}

	// Returns all clients in the database
	public static ArrayList<Client> getClientList(Connection con) {

		ArrayList<Client> clientList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"CLIENT\"");
			rs = ps.executeQuery();
			while (rs.next()) {
				Client client = setClientInfo(rs);
				clientList.add(client);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}

		return clientList;
	}

	private static Client setClientInfo(ResultSet rs) {
		Client client = new Client();
		try {
			client.setClientID(rs.getString("CLIENTID"));
			client.setFirstName(rs.getString("FIRST_NAME"));
			client.setLastName(rs.getString("LAST_NAME"));
			client.setBankerID(rs.getString("BANKERID"));
			client.setEmail(rs.getString("EMAIL"));
			client.setPhoneNo(rs.getString("MOBILE"));
			client.setCPR(rs.getString("CPR"));
			client.setCountry(rs.getString("COUNTRY"));
			client.setPostal(rs.getString("POSTAL"));
			client.setStreet(rs.getString("STREET"));
			return client;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	// Returns all bankers in the database
	public static ArrayList<Banker> getBankerList(Connection con) {

		ArrayList<Banker> bankerList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BANKER\"");
			rs = ps.executeQuery();
			while (rs.next()) {
				Banker banker = new Banker();
				banker.setBankerID(rs.getString("BANKERID"));
				banker.setFirstName(rs.getString("FIRSTNAME"));
				banker.setLastName(rs.getString("LASTNAME"));
				banker.setRegNo(rs.getString("REGNO"));
				banker.setEmail(rs.getString("EMAIL"));
				banker.setPhoneNo(rs.getString("MOBILE"));
				bankerList.add(banker);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}

		return bankerList;
	}

	public static boolean deleteBanker(String bankerID, Connection con) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"BANKER\" "
					+ "WHERE \"BANKERID\"=?");
			ps.setString(1, bankerID);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			cleanUpResult(null, ps);
		}

	}

	public static ArrayList<Client> searchClient(String search, Connection con) {
		ArrayList<Client> result = new ArrayList<>();
		ArrayList<String> terms = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (search.contains(" ")) {
				for (int i = 0; i < search.length(); i++) {
					if (search.charAt(i) == ' ') {
						terms.add(search.substring(0, i));
						terms.add(search.substring(i + 1));
						break;
					}
				}

				ps = con.prepareStatement(
						"SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE ((\"FIRST_NAME\" LIKE ?)"
						+ " AND (\"LAST_NAME\" LIKE ?)) OR (\"LAST_NAME\" LIKE ?)");

				ps.setString(1, "%" + terms.get(0) + "%");
				ps.setString(2, "%" + terms.get(1) + "%");
				ps.setString(3, "%" + search + "%");
				ps.executeQuery();
				rs = ps.getResultSet();
				while (rs.next()) {
					result.add(setClientInfo(rs));
				}
			} else {
				ps = con.prepareStatement(
						"SELECT * FROM \"DTUGRP16\".\"CLIENT\" WHERE (\"FIRST_NAME\" LIKE ?) OR (\"LAST_NAME\" LIKE ?)");
				ps.setString(1, "%" + search + "%");
				ps.setString(2, "%" + search + "%");
				ps.executeQuery();
				rs = ps.getResultSet();
				while (rs.next()) {
					result.add(setClientInfo(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}
		return result;
	}

	public static String generateTransactionID() {
		return UUID.randomUUID().toString();
	}


	public static boolean transaction(String sendAcc, String reciAcc, double amount, String sendReg, String reciReg,
			String currency, String message, String reciMessage, Connection con) {

		// Ensure that negative value transaction can't be executed

		boolean status = false;
		if(amount < 0 || sendAcc.equals("0000000000")){
			return false;
		}
		PreparedStatement oldBalances = null;
		ResultSet rsOldBalances = null;
		PreparedStatement subtract = null;
		PreparedStatement add = null;
		PreparedStatement check1 = null;
		ResultSet rsCheck1 = null;
		PreparedStatement check2 = null;
		ResultSet rsCheck2 = null;
		
		try {			
			// Make sure transaction is reversible in case of an error
			con.setAutoCommit(false);

			// Extract the used currencies, and the old balances before they are
			// changed
			PreparedStatement oldBalance = con.prepareStatement(
					"SELECT \"BALANCE\", \"CURRENCY\" FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\" = ?");
			
			oldBalance.setString(1, sendAcc);
			oldBalance.executeQuery();
			ResultSet rsOldSend = oldBalance.getResultSet();

			// Define variables for old balances
			rsOldSend.next();
			double oldBalanceSend = rsOldSend.getDouble("BALANCE");
			String currencySend = rsOldSend.getString("CURRENCY");

			oldBalance.setString(1, reciAcc);
			oldBalance.executeQuery();
			ResultSet rsOldReci = oldBalance.getResultSet();
			rsOldReci.next();
			double oldBalanceReci = rsOldReci.getDouble("BALANCE");
			String currencyReci = rsOldReci.getString("CURRENCY");

			// Define transaction amount converted to receiving account's
			// currency
			double reciAmount = convert(currencySend, currencyReci, amount, con);

			// Subtract amount from sending account
			subtract = con.prepareStatement(
					"UPDATE \"DTUGRP16\".\"ACCOUNT\" SET \"BALANCE\" = \"BALANCE\" - ? WHERE \"ACCOUNTNUMBER\" = ?");
			subtract.setBigDecimal(1, new BigDecimal(Double.valueOf(amount)));
			subtract.setString(2, sendAcc);
			subtract.executeUpdate();

			// Add amount to receiving account
			add = con.prepareStatement(
					"UPDATE \"DTUGRP16\".\"ACCOUNT\" SET \"BALANCE\" = \"BALANCE\" + ? WHERE \"ACCOUNTNUMBER\" = ?");
			add.setBigDecimal(1, new BigDecimal(Double.valueOf(amount)));
			add.setString(2, reciAcc);
			add.executeUpdate();

			// Create a statement used to extract the new balances
			check1 = con
					.prepareStatement("SELECT \"BALANCE\" FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\" = ?");
			check1.setString(1, sendAcc);
			check1.executeQuery();
			rsCheck1 = check1.getResultSet();

			// Define new balance for sender
			rsCheck1.next();
			double sendBalance = rsCheck1.getDouble("BALANCE");

			// Insert transaction for sender
			String transactionID = generateTransactionID();

			createTransaction(transactionID, sendAcc, reciAcc, -(amount), sendReg, reciReg, currency, message,
					sendBalance, con);


			check2 = con
					.prepareStatement("SELECT \"BALANCE\" FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\" = ?");
			check2.setString(1, reciAcc);
			check2.executeQuery();
			rsCheck2 = check2.getResultSet();

			// Define new balance for recipient
			rsCheck2.next();
			double reciBalance = rsCheck2.getDouble("BALANCE");

			// Insert transaction for recipient
			createTransaction(transactionID, reciAcc, sendAcc, reciAmount, reciReg, sendReg, currencyReci, reciMessage,
					reciBalance, con);

			// Check that no money has been lost or gained,
			// if so roll back all changes
			// Check that no money has been lost
			// Then either commit or roll back

			// The below check no longer works when a conversion happens
			// (sendBalance + reciBalance) == (oldBalanceSend + oldBalanceReci)
			if (checkTransaction(transactionID, 2, con)) {
				con.commit();

				status = true;
			} else {
				con.rollback();
				status = false;
			}
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			return status;
		} finally{
			cleanUpResult(rsOldBalances, oldBalances);
			cleanUpResult(null, subtract);
			cleanUpResult(null, add);
			cleanUpResult(rsCheck1, check1);
			cleanUpResult(rsCheck2, check2);
		}

	}

	public static boolean deposit(String accountNumber, String regNo, Connection con, double amount, String currency){
		PreparedStatement ps = null;
		boolean status = false;
		String message = null;
		double balance = 0;
		String transactionId = generateTransactionID();
		if(amount < 0){
			message = "Withdrawal";
		}else if(amount > 0){
			message = "Deposit";
		}
		try{
			con.setAutoCommit(false);
			
			String accountCurrency = getAccountCurrency(accountNumber, regNo, con);
			amount = convert(currency, accountCurrency, amount, con);
			
			ps = con.prepareStatement("UPDATE \"DTUGRP16\".\"ACCOUNT\" SET \"BALANCE\" = \"BALANCE\" + ?"
					+ " WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?");
			BigDecimal bd = new BigDecimal(Double.valueOf(amount));
			ps.setBigDecimal(1, bd);
			ps.setString(2, accountNumber);
			ps.setString(3, regNo);
			int rs = ps.executeUpdate();
			if(rs == 1){
				balance = getBalance(accountNumber, regNo, con);
				createTransaction(transactionId, accountNumber, accountNumber, amount, regNo, regNo, currency, message, balance, con);
			}
			if(checkTransaction(transactionId, 1, con)){
				con.commit();
				status = true;
			}else{
				con.rollback();
				status = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			status = false;
		}finally{
			cleanUpResult(null, ps);
		}
		return status;
	}
	
	public static double getBalance(String accountNumber, String regNo, Connection con){
		PreparedStatement ps = null;
		ResultSet rs = null;
		double balance = 0;
		try{
			ps = con
					.prepareStatement("SELECT \"BALANCE\" FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?");
			ps.setString(1, accountNumber);
			ps.setString(2, regNo);
			rs = ps.executeQuery();
			if(rs.next()){
				balance = rs.getDouble("BALANCE");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			cleanUpResult(rs, ps);
		}
		return balance;
	}
	
	public static boolean createTransaction(String transactionID, String acc1, String acc2, double amount, String reg1,
			String reg2, String currency, String message, double balance, Connection con) {
	
		PreparedStatement ps = null;
		try {

			// Inserts a transaction into the TRANSACTION table
			ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"TRANSACTION\" "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, transactionID);
			ps.setString(2, acc1);
			ps.setString(3, reg1);
			ps.setString(4, acc2);
			ps.setString(5, reg2);
			ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
			ps.setBigDecimal(7, new BigDecimal(Double.valueOf(amount)));
			ps.setString(8, currency);
			ps.setString(9, message);
			ps.setDouble(10, balance);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			cleanUpResult(null, ps);
		}
	}


	public static double convert(String fromCurrency, String toCurrency, double amount, Connection con) {
		double value = 0.0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Create PreparedStatement to retrieve exchange rates
			ps = con
					.prepareStatement("SELECT \"EXCHANGERATE\" FROM \"DTUGRP16\".\"CURRENCY\" WHERE \"CURRENCY\" = ?");
			ps.setString(1, fromCurrency);
			rs = ps.executeQuery();

			// Get exchange rate for first account
			//rs = ps.getResultSet();
			double rateFrom = 1;
			if(rs.next()){
				rateFrom = rs.getDouble("EXCHANGERATE");
			}
			 
			cleanUpResult(rs, null);
			ps.setString(1, toCurrency);
			rs = ps.executeQuery();

			// Get
			if(rs.next()){
				double rateTo = rs.getDouble("EXCHANGERATE");
				value = (amount / rateFrom) * rateTo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs, ps);
		}
	
		return value;
	}

	// Used to check if two transactions are placed in the db
	public static boolean checkTransaction(String transactionId, int count, Connection con) {

		Boolean status = false;
		int i = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"TRANSACTION\" WHERE \"TRANSACTIONID\" = ?");
			ps.setString(1, transactionId);
			rs = ps.executeQuery();
			
		while(rs.next()){
			if(transactionId.equals(rs.getString("TRANSACTIONID"))){
				i++;
			}
		}
		
		if(i==count){
			status = true;
		}else{
			status = false;
		}
			
		}catch (SQLException e){
			e.printStackTrace();
			status = false;
		} finally{
			cleanUpResult(rs, ps);
		}
		
		
		return status;
	}


	public static boolean editAccount(String accountName, String accountNumber, String regNo, String accountType,
			String clientID, double balance, String currency, Connection con) {
	PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
					"UPDATE \"DTUGRP16\".\"ACCOUNT\" SET \"ACCOUNTNAME\"=?, \"ACCOUNTTYPE\"=?, \"CLIENTID\"=?, \"CURRENCY\"=?"
					+ " WHERE \"ACCOUNTNUMBER\" = ? AND \"REGNO\" = ?");

			ps.setString(1, accountName);
			ps.setString(2, accountType);
			ps.setString(3, clientID);
			ps.setString(4, currency);
			ps.setString(5, accountNumber);
			ps.setString(6, regNo);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			cleanUpResult(null, ps);
		}
	}

	public static void insertExchangeRates(Connection con) throws IOException {
		String s = "http://api.fixer.io/latest";
		URL url = new URL(s);
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext()) {
			str += scan.nextLine();
		}

		scan.close();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"CURRENCY\" VALUES(?, ?)");
			JSONObject obj = new JSONObject(str).getJSONObject("rates");
			Iterator x = obj.keys();
			while (x.hasNext()) {
				String key = (String) x.next();
				String exchRate = obj.get(key).toString();

				ps.setString(1, key);
				ps.setBigDecimal(2, new BigDecimal(exchRate));
				ps.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(null, ps);
		}
	}

	public static void updateExchangeRates(Connection con) throws IOException {
		String s = "http://api.fixer.io/latest";
		URL url = new URL(s);

		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext()) {
			str += scan.nextLine();
		}
		scan.close();
		PreparedStatement ps = null;
		try {
			ps = con
					.prepareStatement("UPDATE \"DTUGRP16\".\"CURRENCY\" SET \"EXCHANGERATE\"=? WHERE \"CURRENCY\"=?");
			JSONObject obj = new JSONObject(str).getJSONObject("rates");
			Iterator x = obj.keys();
			while (x.hasNext()) {
				String key = (String) x.next();
				String exchRate = obj.get(key).toString();

				ps.setString(2, key);
				ps.setBigDecimal(1, new BigDecimal(exchRate));
				ps.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(null, ps);
		}

	}
	
	//Used as a 'batch' to calculate the daily interest and add it to the db.account 'Interest' attribute
	public static boolean calculateInterestRates(Connection con){
		Boolean status = false;
		PreparedStatement ps = null;
		try{			
			ps = con
					.prepareStatement("UPDATE \"DTUGRP16\".\"ACCOUNT\" SET \"DTUGRP16\".\"ACCOUNT\".\"INTEREST\" = "
							+ "\"DTUGRP16\".\"ACCOUNT\".\"INTEREST\" + "
							+ "(SELECT INTERESTRATE FROM \"DTUGRP16\".\"ACCOUNTTYPE\" WHERE \"DTUGRP16\".\"ACCOUNT\".\"ACCOUNTTYPE\" = \"DTUGRP16\".\"ACCOUNTTYPE\".\"ACCOUNTTYPE\")"
							+ "/365.25*\"DTUGRP16\".\"ACCOUNT\".\"BALANCE\"");
			ps.executeUpdate();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally{
			cleanUpResult(null, ps);
		}

		return status;
	}

	//Used as a 'batch' to add the 'Interest' db.account attribute to the balance
	public static boolean giveAnualInterest(Connection con){
		Boolean status = false;
		PreparedStatement ps = null;
		try{			
			ps = con
					.prepareStatement("UPDATE \"DTUGRP16\".\"ACCOUNT\" SET \"DTUGRP16\".\"ACCOUNT\".\"BALANCE\" = "
							+ "\"DTUGRP16\".\"ACCOUNT\".\"BALANCE\" + \"DTUGRP16\".\"ACCOUNT\".\"INTEREST\""
							+ ", \"DTUGRP16\".\"ACCOUNT\".\"INTEREST\" = 0");
			ps.executeUpdate();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally{
			cleanUpResult(null, ps);
		}

		return status;


	}

	public static boolean editClient(String clientID, String firstName, String lastName, String email, String mobile,
			String street, String bankerID, String postal, String country, Connection con) {
		PreparedStatement ps = null;
		try{
		ps = con.prepareStatement(
				"UPDATE \"DTUGRP16\".\"CLIENT\" SET (FIRST_NAME, LAST_NAME, EMAIL, MOBILE, STREET, BANKERID, POSTAL, COUNTRY) "
						+ "= (?,?,?,?,?,?,?,?) WHERE CLIENTID = ?");


		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, email);
		ps.setString(4, mobile);
		ps.setString(5, street);
		ps.setString(6, bankerID);
		ps.setString(7, postal);
		ps.setString(8, country.toUpperCase());
		ps.setString(9, clientID);
		ps.executeUpdate();
		return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
		cleanUpResult(null, ps);
		}
	}


	public static boolean clientEditClient(String clientID, String email, String mobile, String street, String postal,
			Connection con) {
		PreparedStatement ps = null;
		try{
		ps = con.prepareStatement(
				"UPDATE \"DTUGRP16\".\"CLIENT\" SET (EMAIL, MOBILE, STREET, POSTAL) = (?,?,?,?) WHERE CLIENTID = ?");


		ps.setString(1, email);
		ps.setString(2, mobile);
		ps.setString(3, street);
		ps.setString(4, postal);
		ps.setString(5, clientID);
		ps.executeUpdate();
		return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
		cleanUpResult(null, ps);
		}
	}

	public static boolean changeClientPassword(String clientID, String password, Connection con) throws SQLException {
		PreparedStatement ps = null;
		try{
			
		
		ps = con.prepareStatement(
			"UPDATE \"DTUGRP16\".\"CLIENT\" SET \"DTUGRP16\".\"CLIENT\".\"PASSWORD\" = ? WHERE \"CLIENTID\" = ?");

		ps.setString(1, BCrypt.hashpw(password, BCrypt.gensalt(14)));
		ps.setString(2, clientID);
		ps.executeUpdate();
		return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{

		cleanUpResult(null, ps);
		}
	}
	
	//Used as a "batch" to place transaction into the db for old transactions
	public static boolean backupTransactions(Connection con){
		Boolean status = false;
		PreparedStatement movePs = null;
		PreparedStatement deletePs = null;
		try{			
			movePs = con
					.prepareStatement("INSERT INTO \"DTUGRP16\".\"TRANSACTIONOLD\" SELECT * FROM \"DTUGRP16\".\"TRANSACTION\"");
			movePs.executeUpdate();
			
			//TODO - Check if the transaction are moved first
			deletePs = con
					.prepareStatement("DELETE FROM \"DTUGRP16\".\"TRANSACTION\"");
			deletePs.executeUpdate();
			status = true;
		}catch(Exception e){
			e.printStackTrace();
			status = false;
		} finally{
			cleanUpResult(null, movePs);
			cleanUpResult(null, deletePs);
		}
		
		return status;

	}


	// Used by bankers to delete a clients account given the balance is 0
	public static boolean deleteAccount(String regNo, String accountNumber, Connection con) {

		Boolean deleteStatus = false;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		try {			
			// Checking there isn't a balance or debt in account

			ps1 = con.prepareStatement("SELECT BALANCE FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"REGNO\" = ? and \"ACCOUNTNUMBER\" = ?");
			ps1.setString(1, regNo);

			ps1.setString(2, accountNumber);
			rs1 = ps1.executeQuery();
			rs1.next();
			double balance = rs1.getDouble("BALANCE");
			if(balance == 0){
				
				// Trying to delete account

				ps2 = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"REGNO\" = ? and \"ACCOUNTNUMBER\" = ?");
				ps2.setString(1, regNo);

				ps2.setString(2, accountNumber);
				ps2.executeUpdate();
				deleteStatus = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			cleanUpResult(rs1, ps1);
			cleanUpResult(null, ps2);
		}

		return deleteStatus;
	}

	public static boolean createAccountType(String accountType, Double interestRate, Connection con) {
		boolean createAccountTypeStatus = false;
		PreparedStatement ps = null;
		// parsing to big decimal for precision 
		BigDecimal interestRateBD = new BigDecimal(Double.valueOf(interestRate/100));
		
		try {
			// Inserting the account type and interest rate in the database
			ps = con.prepareStatement(
					"INSERT INTO \"DTUGRP16\".\"ACCOUNTTYPE\" VALUES(?, ?)");
			ps.setString(1, accountType);
			ps.setBigDecimal(2, interestRateBD);
			ps.executeUpdate();
			createAccountTypeStatus = true;

		} catch (SQLException e) {
			//SQL Error printing
			//e.printStackTrace();
		}finally{
			cleanUpResult(null, ps);
		}

		return createAccountTypeStatus;
	}
	
	//Get all the account types as a arrayList
	public static ArrayList<AccountType> getAccountTypes(Connection con){
		ArrayList<AccountType> accountTypes = new ArrayList<AccountType>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String accountType = null;
		BigDecimal interestRate = null;
		try{
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ACCOUNTTYPE\"");
			rs = ps.executeQuery();
			while(rs.next()){
				accountType = rs.getString("ACCOUNTTYPE");
				interestRate = rs.getBigDecimal("INTERESTRATE");
				accountTypes.add(new AccountType(accountType, interestRate));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			cleanUpResult(rs, ps);
		}
		
		return accountTypes;
	}
	
	public static boolean deleteAccountType(String accountType, Connection con){
		boolean status = false;
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"ACCOUNTTYPE\" WHERE \"ACCOUNTTYPE\" = ?");
			ps.setString(1, accountType);
			if(ps.executeUpdate() == 1){
				status = true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			status = false;
		}finally{
			cleanUpResult(null, ps);
		}
		return status;
	}
	
	//Used to return a single branch
	public static Branch getBranchInfo(String regNo, Connection con){
		if(regNo.length() != 4){
			return null;
		}
		Branch branch = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BRANCH\" WHERE \"REGNO\" = ?");
			ps.setString(1, regNo);
			rs = ps.executeQuery();
			if(rs.next()){
				String bankName = rs.getString("BANKNAME");
				String postal = rs.getString("POSTAL");
				String country = rs.getString("COUNTRY");
				String city = findCity(postal, country, con);
				String street = rs.getString("STREET");
				String phone = rs.getString("PHONE");
				branch = new Branch(regNo, bankName, postal, country, street, phone, city);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			cleanUpResult(rs,ps);
		}
		return branch;
	}
	
	//Used to return all branches
	public static ArrayList<Branch> getBranches(Connection con){
		ArrayList<Branch> branches = new ArrayList<Branch>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String regNo, bankName, postal, country, city, street, phone;
		try{
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"BRANCH\"");
			rs = ps.executeQuery();
			while(rs.next()){
				regNo = rs.getString("REGNO");
				bankName = rs.getString("BANKNAME");
				postal = rs.getString("POSTAL");
				country = rs.getString("COUNTRY");
				city = findCity(postal, country, con);
				street = rs.getString("STREET");
				phone = rs.getString("PHONE");
				branches.add(new Branch(regNo, bankName, postal, country, street, phone, city));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			cleanUpResult(rs,ps);
		}
		return branches;
	}
	
	//Used to create a branch and insert it into DB
	public static boolean createBranch(String regNo, String bankName, String postal, String country, String street, String phone, Connection con){
		boolean status = false;
		if(regNo.length() != 4){
			return false;
		}
		PreparedStatement ps = null;
		
		try{			
			ps = con.prepareStatement("INSERT INTO \"DTUGRP16\".\"BRANCH\" (REGNO, BANKNAME, POSTAL, COUNTRY, STREET, PHONE) VALUES (?, ?, ?, ?, ?, ?) ");
			ps.setString(1, regNo);
			ps.setString(2, bankName);
			ps.setString(3, postal);
			ps.setString(4, country);
			ps.setString(5, street);
			ps.setString(6, phone);
			if(ps.executeUpdate() == 1){
				status = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			status = false;
		}finally{
			cleanUpResult(null, ps);
		}
		
		return status;
	}
	
	//Used to delete a branch form DB
	public static boolean deleteBranch(String regNo, Connection con){
		boolean status = false;
		if(regNo.length() != 4){
			return false;
		}
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement("DELETE FROM \"DTUGRP16\".\"BRANCH\" WHERE \"REGNO\" = ?");
			ps.setString(1, regNo);
			if(ps.executeUpdate() == 1){
				status = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			status = false;
		}finally{
			cleanUpResult(null, ps);
		}
		
		return status;
	}
	
	//Used to check if there are one or more accounts open at a given regno
	public static boolean checkForOpenAccounts(String regNo, Connection con){
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean status = true;
		try{
			ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"ACCOUNT\" WHERE \"REGNO\" = ? FETCH FIRST 1 ROWS ONLY");
			ps.setString(1, regNo);
			rs = ps.executeQuery();
			status = rs.next();
		}catch(SQLException e){
			e.printStackTrace();
			status = true;
		}finally{
			cleanUpResult(rs, ps);
			
		}
		return status;
	}
	
	public static Banker getAdvisor(String clientId, Connection con){
		Banker banker = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = con.prepareStatement("SELECT BANKERID FROM \"DTUGRP16\".\"CLIENT\" WHERE \"CLIENTID\" = ?");
			ps.setString(1, clientId);
			rs = ps.executeQuery();
			if(rs.next()){
				banker = getBankerInfo(rs.getString("BANKERID"), con);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			cleanUpResult(rs, ps);
		}
		return banker;
	}

}
